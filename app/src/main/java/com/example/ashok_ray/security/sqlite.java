package com.example.ashok_ray.security;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashok_Ray on 20-01-2019.
 */

public class sqlite extends SQLiteOpenHelper {
    public static final String database_name="osa";
    String table="create"+sqldata.sqlstatic.table_name+"("+sqldata.sqlstatic.username+" text,"+sqldata.sqlstatic.email+" text,"+sqldata.sqlstatic.url+" text)";
    public static final int ver=1;
    public sqlite(Context context) {
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
        sqlcontent.put(sqldata.sqlstatic.username,username);
        sqlcontent.put(sqldata.sqlstatic.email,email);
        sqlcontent.put(sqldata.sqlstatic.url,url);
        sqLiteDatabase.insert(sqldata.sqlstatic.table_name,null,sqlcontent);}
        public Cursor readinfo(SQLiteDatabase sqLiteDatabase){

            String[] projection={sqldata.sqlstatic.username,sqldata.sqlstatic.email,sqldata.sqlstatic.url};
            Cursor cursor=  sqLiteDatabase.query(sqldata.sqlstatic.table_name,projection,null,null,null,null,null);
    return  cursor;
    }
    public void updateinfo(String username,String email,SQLiteDatabase sqLiteDatabase){
            ContentValues contentValues=new ContentValues();
            contentValues.put(sqldata.sqlstatic.username,username);
            String select=sqldata.sqlstatic.email+"="+email;
            sqLiteDatabase.update(sqldata.sqlstatic.table_name,contentValues,select,null);

    }
}
