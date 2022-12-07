package com.example.itemcheckout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.itemcheckout.placeholder.PlaceholderContent;

import java.util.ArrayList;

public class SQLiteOperations extends SQLiteOpenHelper{

        // creating a constant variables for our database.
        // below variable is for our database name.
        private static final String DB_NAME = "itemcheckoutdb";

        // below int is our database version
        private static final int DB_VERSION = 2;

        // below variable is for our table name.
        private static final String TABLE_NAME = "itemcheckout";

        // below variable is for our id column.
        private static final String ID_COL = "id";

        // below variable is for our course name column
        private static final String ITEM_COL = "item";

        // below variable id for our course duration column.
        private static final String COST_COL = "cost";

        // below variable for our course description column.
        private static final String DESCRIPTION_COL = "description";

        // below variable is for our course tracks column.
        private static final String STOCK_COL = "stock";

        // creating a constructor for our database handler.
        public SQLiteOperations(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


    // below method is for creating a database by running a sqlite query
        @Override
        public void onCreate(SQLiteDatabase db) {
            // on below line we are creating
            // an sqlite query and we are
            // setting our column names
            // along with their data types.
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_COL + " TEXT,"
                    + COST_COL + " TEXT,"
                    + DESCRIPTION_COL + " TEXT,"
                    + STOCK_COL + " TEXT)";

            // at last we are calling a exec sql
            // method to execute above sql query
            db.execSQL(query);
        }
        public int dbSize() {

            int size = 0;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            if (cursorCourses.moveToFirst()) {
                do {
                    size++;

                } while (cursorCourses.moveToNext());
                size++;
            }
            cursorCourses.close();

            return size;
        }

        // this method is use to add new course to our sqlite database.
        public void addNewItem(String itemName, String itemCost, String itemDescription, String itemStock, int itemID) {

            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.
            SQLiteDatabase db = this.getWritableDatabase();

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put(ITEM_COL, itemName);
            values.put(COST_COL, itemCost);
            values.put(DESCRIPTION_COL, itemDescription);
            values.put(STOCK_COL, itemStock);

            // after adding all values we are passing
            // content values to our table.
            db.insert(TABLE_NAME, null, values);

            // at last we are closing our
            // database after adding database.
            //db.close();

            /* Reenable db.close when past first screen, debugging purposes */
        }

    public ArrayList<ItemDetailHostActivity> readItems() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ItemDetailHostActivity> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new ItemDetailHostActivity(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getInt(0)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // this method is called to check if the table exists already.
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
