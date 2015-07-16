package menu.saryal.example.com.menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MenuDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION =12;
    static final String DATABASE_NAME = "menu.db";

    public MenuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MENU_TABLE = "CREATE TABLE " + MenuContract.MenuEntry.TABLE_NAME + " (" +
                MenuContract.MenuEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MenuContract.MenuEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_RATING + " REAL NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_COST + " REAL NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_IMAGE + " TEXT NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_TOTAL_COST + " REAL NOT NULL, " +
                MenuContract.MenuEntry.COLUMN_NAME_TOTAL_ORDER + " INTEGER NOT NULL " +
                " )";
        sqLiteDatabase.execSQL(SQL_CREATE_MENU_TABLE);

        final String SQL_CREATE_ALL_MENU_TABLE = "CREATE TABLE " + MenuContract.MenuDB.TABLE_NAME + " (" +
                MenuContract.MenuDB.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MenuContract.MenuDB.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                MenuContract.MenuDB.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                MenuContract.MenuDB.COLUMN_NAME_COST + " REAL NOT NULL, " +
                MenuContract.MenuDB.COLUMN_NAME_IMAGE + " TEXT NOT NULL, " +
                MenuContract.MenuDB.COLUMN_NAME_TOTAL_COST + " REAL NOT NULL, " +
                MenuContract.MenuDB.COLUMN_NAME_TOTAL_ORDER + " INTEGER NOT NULL " +
                " )";
        sqLiteDatabase.execSQL(SQL_CREATE_ALL_MENU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MenuContract.MenuEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addData(menuDescription item){

        long newRowId;
        ContentValues values = new ContentValues();
        values.put(MenuContract.MenuEntry.COLUMN_NAME_TOTAL_COST, item.getTotalCost());
        values.put(MenuContract.MenuEntry.COLUMN_NAME_TOTAL_ORDER, item.getTotalOrder());
        values.put(MenuContract.MenuEntry.COLUMN_NAME_RATING, item.getRatinng());
        menuDescription returnItem = findByTitle(item.getTitle());
        SQLiteDatabase database = this.getWritableDatabase();
        if (returnItem == null){
            values.put(MenuContract.MenuEntry.COLUMN_NAME_TITLE, item.getTitle());
            values.put(MenuContract.MenuEntry.COLUMN_NAME_DESCRIPTION, item.getDescription());
            values.put(MenuContract.MenuEntry.COLUMN_NAME_COST, item.getCost());
            values.put(MenuContract.MenuEntry.COLUMN_NAME_IMAGE, item.getImage());
            newRowId = database.insert(
                    MenuContract.MenuEntry.TABLE_NAME,
                    null,
                    values);
        }else{
            String selection = MenuContract.MenuEntry.COLUMN_NAME_TITLE + " = ?";
            String[] data = {item.getTitle()};
            newRowId = database.update(
                    MenuContract.MenuEntry.TABLE_NAME,
                    values,
                    selection,
                    data);
        }
        database.close();
        return newRowId;
    }

    public long addDataToALLMenu(menuDescription item){

        ContentValues values = new ContentValues();
        values.put(MenuContract.MenuDB.COLUMN_NAME_TITLE, item.getTitle());
        values.put(MenuContract.MenuDB.COLUMN_NAME_DESCRIPTION, item.getDescription());
        values.put(MenuContract.MenuDB.COLUMN_NAME_COST, item.getCost());
        values.put(MenuContract.MenuDB.COLUMN_NAME_IMAGE, item.getImage());
        values.put(MenuContract.MenuDB.COLUMN_NAME_TOTAL_COST, item.getTotalCost());
        values.put(MenuContract.MenuDB.COLUMN_NAME_TOTAL_ORDER, item.getTotalOrder());

        SQLiteDatabase db = this.getWritableDatabase();

        long newRowId;
        newRowId = db.insert(
                MenuContract.MenuDB.TABLE_NAME,
                null,
                values);
        db.close();
        return newRowId;
    }

    public ArrayList<menuDescription> readData(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<menuDescription> data = new ArrayList<menuDescription>();

        String[] projection = {
                MenuContract.MenuEntry.COLUMN_NAME_ENTRY_ID,
                MenuContract.MenuEntry.COLUMN_NAME_TITLE,
                MenuContract.MenuEntry.COLUMN_NAME_DESCRIPTION,
                MenuContract.MenuEntry.COLUMN_NAME_COST,
                MenuContract.MenuEntry.COLUMN_NAME_IMAGE,
                MenuContract.MenuEntry.COLUMN_NAME_RATING,
                MenuContract.MenuEntry.COLUMN_NAME_TOTAL_COST,
                MenuContract.MenuEntry.COLUMN_NAME_TOTAL_ORDER
        };

        String sortOrder =
                MenuContract.MenuEntry.COLUMN_NAME_TITLE + " ASC";

        Cursor c = db.query(
                MenuContract.MenuEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        while(c.moveToNext()){
            menuDescription item = new menuDescription(c.getString(1),c.getString(2),c.getDouble(3),c.getString(4),c.getDouble(6),c.getInt(7));
            item.setRatinng(c.getDouble(5));
            data.add(item);
        };
        c.close();
        db.close();
        return data;
    }

    public ArrayList<menuDescription> provideAllDataInMenu(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<menuDescription> data = new ArrayList<menuDescription>();

        String[] projection = {
                MenuContract.MenuDB.COLUMN_NAME_ENTRY_ID,
                MenuContract.MenuDB.COLUMN_NAME_TITLE,
                MenuContract.MenuDB.COLUMN_NAME_DESCRIPTION,
                MenuContract.MenuDB.COLUMN_NAME_COST,
                MenuContract.MenuDB.COLUMN_NAME_IMAGE,
                MenuContract.MenuDB.COLUMN_NAME_TOTAL_COST,
                MenuContract.MenuDB.COLUMN_NAME_TOTAL_ORDER
        };

        String sortOrder =
                MenuContract.MenuDB.COLUMN_NAME_TITLE + " ASC";

        Cursor c = db.query(
                MenuContract.MenuDB.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        while(c.moveToNext()){
            menuDescription item = new menuDescription(c.getString(1),c.getString(2),c.getDouble(3),c.getString(4),c.getDouble(5),c.getInt(6));
            data.add(item);
        };
        c.close();
        db.close();
        return data;
    }

    public boolean deleteItem(String itemName) {

        boolean result = false;
        String query = "Select * FROM " + MenuContract.MenuEntry.TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToNext()) {
            String[] value = {itemName};
            db.delete(MenuContract.MenuEntry.TABLE_NAME, MenuContract.MenuEntry.COLUMN_NAME_TITLE + " = ?" ,value);
            c.close();
            result = true;
        }
        c.close();
        db.close();
        return result;
    }

    public void clearDatabase() {

        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(MenuContract.MenuEntry.TABLE_NAME, null, null);
        db.delete(MenuContract.MenuDB.TABLE_NAME, null, null);
        db.close();
    }

    public menuDescription findByTitle(String title){
        String query = "Select * FROM " + MenuContract.MenuEntry.TABLE_NAME + " WHERE " + MenuContract.MenuEntry.COLUMN_NAME_TITLE + " =  \"" + title + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        menuDescription item ;

        if (c.moveToFirst()) {
            c.moveToFirst();
            item = new menuDescription(c.getString(1),c.getString(2),c.getDouble(4),c.getString(5),c.getDouble(6),c.getInt(7));
            item.setRatinng(c.getDouble(3));
            c.close();
        } else {
            item = null;
        }
        db.close();
        return item;
    }
}

