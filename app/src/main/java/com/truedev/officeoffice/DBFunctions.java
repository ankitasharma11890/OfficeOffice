package com.truedev.officeoffice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.truedev.officeoffice.Database.DailyTaskDB;

import java.util.ArrayList;

/**
 * Created by dipanshugarg on 19/5/16.
 */
public class DBFunctions {

    public long insertTask(ArrayList<String> data, String date) {
        long success = 0;
        SQLiteDatabase db = ApplicationController.getTasksDB(true);
        db.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i= 0; i<data.size();i++) {
            values.put(DailyTaskDB.Task,data.get(i));
            values.put(DailyTaskDB.Date,date);
            success = db.insert(DailyTaskDB.TABLE_TASK, null,values);
        }
        db.close();
        return success;
    }

    public void getAllRecords(String date) {
        SQLiteDatabase sqLiteDatabase = ApplicationController.getTasksDB(false);

        Cursor cursor = sqLiteDatabase.query(DailyTaskDB.TABLE_TASK,new String[]{DailyTaskDB.Task,DailyTaskDB.Date}, DailyTaskDB.Date+" =? ",
                new String[]{String.valueOf(date)},null,null,null);

        if(cursor!=null) {
            cursor.moveToFirst();
            do{
                Log.e("Dipanshu DataBase:",  cursor.getString(cursor.getColumnIndex(DailyTaskDB.Task))+
                        cursor.getString(cursor.getColumnIndex(DailyTaskDB.Date)) );
            }
            while (cursor.moveToNext());
        }

    }

}
