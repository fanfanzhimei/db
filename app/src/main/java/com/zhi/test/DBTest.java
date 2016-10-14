package com.zhi.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.zhi.domain.Person;
import com.zhi.service.DataBaseHelper;
import com.zhi.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class DBTest extends AndroidTestCase {
    private final String TAG = this.getClass().getSimpleName();

    public void test() {
        SQLiteHelper sqLiteOpenHelper = new SQLiteHelper(this.getContext());
        sqLiteOpenHelper.getWritableDatabase();  // 创建或更新数据库，可以点该方法看具体实现
    }

    public void testAdd() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("liuxin", 1, 0));
        persons.add(new Person("zhangyisan", 2, 0));
        persons.add(new Person("yangzi", 3, 0));
        persons.add(new Person("yangmi", 4, 0));
        persons.add(new Person("zhaofeiyan", 5, 0));
        persons.add(new Person("zhaohede", 6, 0));
        persons.add(new Person("zhaoliyin", 7, 0));
        persons.add(new Person("zhaowenzhuo", 8, 0));
        persons.add(new Person("liudehua", 9, 0));
        persons.add(new Person("renxianqi", 10, 0));

        for (Person person : persons) {
            db.add(person);
        }
    }

    public void testDelete() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        db.delete(11);
    }

    public void testUpdata() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        Person person  = new Person(2, "赵本山", 100, 0);
        db.updata(person);
    }

    public void testFind() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        Person person = db.find(2);
        Log.e(TAG, person.toString());
    }

    public void testPaging() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        List<Person> persons = db.paging(2, 4);
        for (Person person : persons) {
            Log.e(TAG, person.toString());
        }
    }

    public void testCount() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        long count = db.getCount();
        Log.e(TAG, count + "");
    }

    public void testPayment() {
        DataBaseHelper db = new DataBaseHelper(this.getContext());
        Person person1 = db.find(21);
        person1.setAmount(100);
        Person person2 = db.find(22);
        person2.setAmount(50);

        db.updata(person1);
        db.updata(person2);

        db.setPayment(person1, person2, 20);
    }
}
