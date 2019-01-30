package com.cagatay.ozata.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME="personaltrainer";
    private static int  DATABASE_VERSION = 1;

    SQLiteDatabase db;

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE OPERATIONS","Connection Provided");
    }

    public void open(){
        if(db == null)
            db= getWritableDatabase();//Wirtable and Readable mode

    }
    public void close(){
        if(db == null)
            db.close();//Wirtable and Readable mode
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(PersonalTrainerDB.CREATE_TABLE_SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
        Log.d("DATABASE OPERATIONS","OnCreate, table created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL(PersonalTrainerDB.DROP_TABLE_SQL);

        }catch (SQLException e){
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase);
        Log.d("DATABASE OPERATIONS","onUpgrade,  table dropped, old version "+oldVersion+" new version "+newVersion);
    }
    public Cursor getAllRecords(String tableName, String[] columns   ){
        Cursor cursor = db.query(tableName, columns, null, null, null, null, null );
        Log.d("DATABASE OPERATIONS", "GET THE RECORDS");
        return cursor;
    }
    public Cursor getSomeRecords( String tableName, String[] columns,String where ){
        Cursor cursor = db.query(tableName, columns, where, null, null, null, null);
        Log.d("DATABASE OPERATIONS", "GET ALL RECORDS WITH WHERE CLAUSE");
        return cursor;
    }
    public long insert( String tableName, ContentValues contentValues){
        Log.d("DATABASE OPERATIONS", "INSERT DONE");
        return db.insert(tableName, null, contentValues);
    }
    public boolean update( String tableName, ContentValues contentValues, String whereCondition){
        Log.d("DATABASE OPERATIONS", "UPDATE DONE");
        return db.update(tableName,contentValues,whereCondition,null)>0;
    }
    public boolean delete(  String tableName, String whereCondition){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        return db.delete(tableName, whereCondition, null)>0;
    }
}
