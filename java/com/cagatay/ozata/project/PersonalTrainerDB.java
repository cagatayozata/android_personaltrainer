package com.cagatay.ozata.project;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class PersonalTrainerDB {

    public static final String TABLE_NAME="pt";
    public static final String FIELD_AGE = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EDUCATION = "email";
    public static final String FIELD_TYPE = "type";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+ FIELD_AGE +" number, "+FIELD_NAME+" text, "+ FIELD_EDUCATION +" text, "+FIELD_TYPE+" text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static List<PersonalTrainer> getAllContact(DBHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        List<PersonalTrainer> data=new ArrayList<>();
        PersonalTrainer pt = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String education = cursor.getString(2);

            pt= new PersonalTrainer(id, name,education );
            data.add(pt);
        }
        return data;
    }

    public static long insertContact(DBHelper db, int age, String name, String education){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_AGE, age);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_EDUCATION, education);

        long res = db.insert(TABLE_NAME,contentValues);
        return res;
    }
    public static boolean deleteContact(DBHelper db, int id){
        String where = FIELD_AGE +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }

}
