package com.zhi.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/13.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    public SQLiteHelper(Context context) {
        super(context, "zhi.db", null, 6); // 第2个参数 数据库的名字，第3个参数用默认的CusorFactory,第3个参数数据库版本号
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE person(personid integer primary key autoincrement, name varchar(20), age integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "ALTER TABLE person add amount integer";
        db.execSQL(sql);
    }
}
