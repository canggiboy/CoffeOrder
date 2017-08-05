package ikhsan.com.coffeorder.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ikhsan.com.coffeorder.data.CoffeeContract.coffeeEntry;
import ikhsan.com.coffeorder.model.DatabaseModel;

/**
 * Created by ikhsan on 15/03/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "coffee_order.db";
    private static final int DATABASE_VERSION = 12;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_COFFEE_TABLE = "CREATE TABLE " + coffeeEntry.TABLE_NAME + "("
                + coffeeEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + coffeeEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

        String SQL_CREATE_MENU_TABLE = "CREATE TABLE " + coffeeEntry.TABLE_MENU + "("
                + coffeeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + coffeeEntry.COLUMN_MENU_NAME + " TEXT NOT NULL, "
                + coffeeEntry.COLUMN_MENU_PRICE + " TEXT NOT NULL, "
                + coffeeEntry.COLUMN_MENU_TYPE + " INTEGER NOT NULL);";

        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + coffeeEntry.TABLE_USER + "("
                + coffeeEntry._USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + coffeeEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + coffeeEntry.COLUMN_USER_TABLE + " TEXT NOT NULL, "
                + coffeeEntry.COLUMN_USER_OFOOD + " TEXT, "
                + coffeeEntry.COLUMN_USER_TOFOOD + " INTEGER DEFAULT 0, "
                + coffeeEntry.COLUMN_USER_ODRINK + " TEXT, "
                + coffeeEntry.COLUMN_USER_TODRINK + " INTEGER DEFAULT 0, "
                + coffeeEntry.COLUMN_USER_TOTAL + " TEXT NOT NULL DEFAULT '0');";

        db.execSQL(SQL_CREATE_COFFEE_TABLE);
        db.execSQL(SQL_CREATE_MENU_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
       db.execSQL("DROP TABLE IF EXISTS "+coffeeEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+coffeeEntry.TABLE_MENU);

        onCreate(db);
    }

    public ArrayList<DatabaseModel> getDataFromDB(){
        ArrayList<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+coffeeEntry.TABLE_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        try {

            if (cursor.moveToFirst()){
                do {
                    DatabaseModel model = new DatabaseModel();
                    model.setMenu(cursor.getString(1));
                    model.setPrice(cursor.getString(2));

                    modelList.add(model);
                }while (cursor.moveToNext());
            }


            Log.d("menu data", modelList.toString());

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        return modelList;
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

    //method untuk mencari username dan password dari database
    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select username, password from " + coffeeEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "tidak ditemukan";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
}
