package com.example.ashok_ray.security.Module_DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashok_Ray on 20-01-2019.
 */

public class CLS_SqliteHelper extends SQLiteOpenHelper {
    public static final String database_name="osa";
    String table="create"+ CLS_DbCoreValues.sqlstatic.table_name+"("+ CLS_DbCoreValues.sqlstatic.username+" text,"+ CLS_DbCoreValues.sqlstatic.email+" text,"+ CLS_DbCoreValues.sqlstatic.url+" text)";
    public static final int ver=1;
    public CLS_SqliteHelper(Context context) {
        super(context,database_name,null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

sqLiteDatabase.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addinfo(String username,String email,String url,SQLiteDatabase sqLiteDatabase){
        ContentValues sqlcontent=new ContentValues();
        sqlcontent.put(CLS_DbCoreValues.sqlstatic.username,username);
        sqlcontent.put(CLS_DbCoreValues.sqlstatic.email,email);
        sqlcontent.put(CLS_DbCoreValues.sqlstatic.url,url);
        sqLiteDatabase.insert(CLS_DbCoreValues.sqlstatic.table_name,null,sqlcontent);}
        public Cursor readinfo(SQLiteDatabase sqLiteDatabase){

            String[] projection={CLS_DbCoreValues.sqlstatic.username, CLS_DbCoreValues.sqlstatic.email, CLS_DbCoreValues.sqlstatic.url};
            Cursor cursor=  sqLiteDatabase.query(CLS_DbCoreValues.sqlstatic.table_name,projection,null,null,null,null,null);
    return  cursor;
    }
    public void updateinfo(String username,String email,SQLiteDatabase sqLiteDatabase){
            ContentValues contentValues=new ContentValues();
            contentValues.put(CLS_DbCoreValues.sqlstatic.username,username);
            String select= CLS_DbCoreValues.sqlstatic.email+"="+email;
            sqLiteDatabase.update(CLS_DbCoreValues.sqlstatic.table_name,contentValues,select,null);

    }
}
