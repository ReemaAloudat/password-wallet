package com.beyond.passwordwallet;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBManger extends SQLiteOpenHelper {

    final static String DATABASE_NAME="walletpassword.db";
    final static int DATABASE_VERSION=4;
    final static String TABLE_NAME="wallet";
    final static String COLUMN_ID="id";
    final static String COLUMN_TITLE="title";
    final static String COLUMN_ACCOUNT="account";
    final static String COLUMN_PASSWORD="passwordnumber";


    public DBManger(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        System.out.println("create.... done**************");
        String q="create table "+TABLE_NAME +
                "("+ COLUMN_TITLE + " text primary key,"+
                COLUMN_ACCOUNT + " text ,"+
                COLUMN_PASSWORD + " text);";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String q="drop table if exists "+ TABLE_NAME + ";";
        sqLiteDatabase.execSQL(q);
        onCreate(sqLiteDatabase);

    }


    public void deleteTabel()
    {
        String q="drop table "+ TABLE_NAME + ";";
        SQLiteDatabase myDB=getWritableDatabase();
        myDB.execSQL(q);

    }

    void addInfo(InfoAccount info )
    {
        String q1="insert into "+ TABLE_NAME+ "("+ COLUMN_TITLE +","+ COLUMN_ACCOUNT+","+
                COLUMN_PASSWORD + ") values ("+"\""+info.getTitle()+"\""+","
                +"\""+info.getAccount()+ "\", "+ "\""+info.getPassword()+"\");";
        SQLiteDatabase myDB=getWritableDatabase();
        myDB.execSQL(q1);


    }


    InfoAccount searchByTitle(String title)
    {
        InfoAccount i=new InfoAccount();
        String q="select * from "+TABLE_NAME +" where "+ COLUMN_TITLE+ "=\""+title+"\";";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery(q, null);
        if(c.moveToFirst())
        {// if there is data, moveToFirst() moves cursor to first row  and returns true , if there is no data it returns false
            String t=c.getString(c.getColumnIndex(COLUMN_TITLE));
            i.setTitle(t);
            String a=c.getString(c.getColumnIndex(COLUMN_ACCOUNT));
            i.setAccount(a);
            String p=c.getString(c.getColumnIndex(COLUMN_PASSWORD));
            i.setPassword(p);

        }
        return i;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    InfoAccount searchById(int f)
    {
        InfoAccount p=new InfoAccount();
        String q="select * from "+TABLE_NAME +" where "+ COLUMN_ID+ "="+f+";";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery(q, null);
        if(c.moveToFirst()){// if there is data, moveToFirst() moves cursor to first row  and returns true , if there is no data it returns false
            String n=c.getString(1);
            p.setTitle(n);
            String g1=c.getString(c.getColumnIndex(COLUMN_ACCOUNT));
            p.setAccount(g1);
            String i=c.getString(c.getColumnIndex(COLUMN_PASSWORD));
            p.setPassword(i);

        }// if
        return p;
    }


    void deleteAll(){
        String myQuery="delete from "+TABLE_NAME+ ";";
        SQLiteDatabase db2=getWritableDatabase();// use getWritableDatabase(); with delete,update,insert into queries
        db2.execSQL(myQuery);
    }

    ArrayList<InfoAccount> getAll(){
        InfoAccount i=new InfoAccount();
        ArrayList<InfoAccount>namesList=new ArrayList<InfoAccount>();

        String q="select * from "+TABLE_NAME +";";
        SQLiteDatabase db1=getReadableDatabase();// use getReadableDatabase() with select query
        Cursor c=db1.rawQuery(q, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String t=c.getString(c.getColumnIndex(COLUMN_TITLE));
            i.setTitle(t);
            String a=c.getString(c.getColumnIndex(COLUMN_ACCOUNT));
            i.setTitle(a);
            String p=c.getString(c.getColumnIndex(COLUMN_PASSWORD));
            i.setTitle(p);

            namesList.add(i);
            c.moveToNext();

        }
        return namesList;
    }

    ArrayList<String> getTitle(){

        ArrayList<String>namesList=new ArrayList<String>();

        String q="select "+COLUMN_TITLE+" from "+TABLE_NAME +";";
        SQLiteDatabase db1=getReadableDatabase();// use getReadableDatabase() with select query
        Cursor c=db1.rawQuery(q, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String t=c.getString(c.getColumnIndex(COLUMN_TITLE));
            namesList.add(t);
            c.moveToNext();

        }
        return namesList;
    }

    public boolean containsKey(String primaryKey) {
        String countQuery = "SELECT "+ COLUMN_TITLE +" FROM " + TABLE_NAME + " where " + COLUMN_TITLE +"=\""+primaryKey+"\"" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count>0;
    }

    public void deleteInfo(String title)
    {

        String q ="delete from "+TABLE_NAME+" where "+COLUMN_TITLE +" =\""+title+"\";";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);


    }

}
