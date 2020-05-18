package com.test.minicostcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemsDB {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "item_name";
    public static final String KEY_PRICE = "_PRICE";
    public static final String KEY_FREQ = "_freq";

    private final  String DATABASE_NAME = "ItemsDB";
    private final String DATABASE_TABLE = "ItemsTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public ItemsDB(Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_PRICE + " TEXT NOT NULL, " +
                    KEY_FREQ + " TEXT NOT NULL);";


            db.execSQL(sqlCode);
            
        }
    }

    public ItemsDB open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createItem(String name, String price, String freq)
    {
        ContentValues iv = new ContentValues();
        iv.put(KEY_NAME, name);
        iv.put(KEY_PRICE, price);
        iv.put(KEY_FREQ, freq);
        return ourDatabase.insert(DATABASE_TABLE, null, iv);
    }

    public String getData()
    {
        String [] columns = new String [] {KEY_ROWID, KEY_NAME, KEY_PRICE, KEY_FREQ};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iPrice = c.getColumnIndex(KEY_PRICE);
        int iFreq = c.getColumnIndex(KEY_FREQ);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +
                    c.getString(iPrice) +  " " + c.getString(iFreq) + "\n";
        }

        c.close();

        return result;

    }

    public long deleteEntry(String rowId)
    {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String []{rowId});
    }


}
