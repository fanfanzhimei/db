package com.zhi.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhi.domain.Person;
import com.zhi.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class DataBaseHelper {

    private SQLiteHelper sqLiteHelper;

    public DataBaseHelper(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    public void add(Person person) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        String sql = "insert into person(name, age, amount) values(?,?,?)";
        db.execSQL(sql, new Object[]{person.getName(), person.getAge() + "", person.getAmount() + ""});
    }

    public void delete(int personId) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        String sql = "delete from person where personId = ?";
        db.execSQL(sql, new Object[]{personId});
    }

    public void updata(Person person) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        String sql = "update person set name = ?,age=?,amount=? where personId = ?";
        db.execSQL(sql, new Object[]{person.getName(), person.getAge(),
                person.getAmount(), person.getPersonid()});
    }

    public Person find(int id) {
        Person person = null;
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        String sql = "select * from person where personid = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        if (cursor.moveToFirst()) {
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            person = new Person(personid, name, age, amount);
        }
        cursor.close();
        return person;
    }

    public List<Person> paging(int offset, int maxResult) {  // 分页查询
        List<Person> list = new ArrayList<Person>();
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        String sql = "select * from person order by personid asc limit ?, ?";
        Cursor cursor = db.rawQuery(sql, new String[]{offset + "", maxResult + ""});
        while (cursor.moveToNext()) {
            int personid = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            list.add(new Person(personid, name, age, amount));
        }
        cursor.close();
        return list;
    }

    public long getCount() {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        String sql = "select count(*) from person";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

    public void setPayment(Person person1, Person person2, int amount) {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        db.beginTransaction();

        ContentValues values1 = new ContentValues();
        values1.put("amount", (person1.getAmount() - amount) + "");

        ContentValues values2 = new ContentValues();
        values2.put("amount", (person2.getAmount() + amount) + "");

        db.update("person", values1, "personid = ?", new String[]{person1.getPersonid() + ""});
        db.update("person", values2, "personid = ?", new String[]{person2.getPersonid() + ""});

//        String sql = "update person set amount = ? where personid = ?";
//        db.execSQL(sql, new String[]{person1.getAmount() - amount+"", person1.getPersonid()+""});
//        db.execSQL(sql, new String[]{person2.getAmount() + amount + "", person2.getPersonid() + ""});

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
}
