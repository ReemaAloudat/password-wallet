package com.beyond.passwordwallet;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public  class DBPasswords {

    //information of database
    private SQLiteDatabase sql;
    private static final int DATABASE_VERSION = 1;
    static  final String DB_Title="passwordWallet";
    private static final String DATABASE_NAME = "DBpasswords";
    public static final String TABLE_NAME = "passwords";
    public static final String ColPassword = "password";
    public static final String ColConfPass = "confPassword";

    static  final String CreateTable= "Create table IF NOT EXISTS " + TABLE_NAME + " (ID Integer PRIMARY KEY AUTOINCREMENT ,"+ ColPassword + " text ," + ColConfPass + " text );" ;



    static  class DatabaseHelperUser extends SQLiteOpenHelper
    {
        Context context;
        public DatabaseHelperUser(Context context) {
            super(context,DB_Title,null,DATABASE_VERSION);
            this.context =context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table is Created" , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("Drop table IF EXISTS" + TABLE_NAME);
            onCreate(db);
        }
    }
    public DBPasswords (Context context)
    {
        DBPasswords.DatabaseHelperUser db = new DBPasswords.DatabaseHelperUser(context);
        sql = db.getWritableDatabase();
    }

    public  long Insert (ContentValues values){
        long ID=  sql.insert(TABLE_NAME,"",values);
        return  ID;
    }
}




