package zerodev.p0631_alertdialogitemssingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private static final String DB_NAME = "mydb";
    private static final String DB_TABLE = "mytab";
    private static final int DB_VERSION = 1;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TXT = "txt";

    private static final String TABLE_CREATE = "create table " + DB_TABLE + "(" + COLUMN_ID + " integer primary key, " + COLUMN_TXT + " " +
            "text" + ");";

    private final Context mCtx;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx){
        mCtx = ctx;
    }
    public void open(){
        mDBHelper = new DBHelper(mCtx,DB_NAME,null,DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    public void close(){
        if(mDBHelper!=null) mDBHelper.close();
    }
    public Cursor getAllData(){
        return mDB.query(DB_TABLE,null,null,null,null,null,null);
    }

    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(TABLE_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 0; i < 5; i++) {
                cv.put(COLUMN_ID,i);
                cv.put(COLUMN_TXT,"sometext " + i);
                db.insert(DB_TABLE,null,cv);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
    }
}
