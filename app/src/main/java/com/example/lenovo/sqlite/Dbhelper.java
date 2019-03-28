package com.example.lenovo.sqlite;

import android.accessibilityservice.GestureDescription;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    String table_name="Students";
    String mName="Name";
    String mAge="Age";
    String mGender="Gender";
    String mCity="City";
    String mMobile="Mobile";
    public Dbhelper(Context context, String name) {
        super(context, name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name + " (Name TEXT, Age TEXT, Gender TEXT, City TEXT, Mobile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insert(String name, String age, String gender,String city,String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mName,name);
        contentValues.put(mAge,age);
        contentValues.put(mGender,gender);
        contentValues.put(mCity,city);
        contentValues.put(mMobile,mobile);
        long result = db.insert(table_name,null,contentValues);
        if (result == - 1){
            return false;
        }else {return true;}
    }

    public Cursor getData(){
    SQLiteDatabase db =  this.getWritableDatabase();
    Cursor result = db.rawQuery("SELECT * FROM "+table_name,null);
    return result;


    }
    public boolean modify(String name, String age, String gender,String city,String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mName,name);
        contentValues.put(mAge,age);
        contentValues.put(mGender,gender);
        contentValues.put(mCity,city);
        contentValues.put(mMobile,mobile);
        String where    = "Name='" +name+"'";
        long result = db.update(table_name,contentValues,where,null);
        if(result==-1){
         return false;
        }else{
            return true;
        }
    }

public boolean delete(String name,String gender){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    String where    = "Name='" +name+"'AND Gender= '"+ gender+"'";
    long result = db.delete(table_name,where,null);
    if(result==-1){
        return false;
    }else{
        return true;
    }
}

}
