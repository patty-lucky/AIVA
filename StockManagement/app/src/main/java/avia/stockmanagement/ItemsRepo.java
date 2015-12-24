package avia.stockmanagement;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GT70 on 17-Dec-15.
 */
public class ItemsRepo {
    private DBHelper dbHelper;

    public ItemsRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public int insert(Items items)
    {

        // Open connection to write data
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Items.KEY_itemID , items.item_itemID);
        values.put(Items.KEY_name, items.item_name);
        values.put(Items.KEY_quantity,items.item_qt);
        values.put(Items.KEY_borrowDate,items.item_borrowDate);
        values.put(Items.KEY_returnDate,items.item_returnDate);
        values.put(Items.KEY_comment,items.item_comment);

        // Inserting Row
        long item_orderId = db.insert(Items.TABLE , null , values);

        db.close(); // Closing database connection !!!

        return (int) item_orderId;

    }

    public void delete(int item_orderId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Good practice to use parameter ? , instead of concatenate String :3

        db.delete(Items.TABLE,Items.KEY_orderID + "= ?", new String[]
                { String.valueOf(item_orderId)});

        db.close(); // Close database connection

    }

    public void update (Items items)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(Items.KEY_itemID , items.item_itemID);
        values.put(Items.KEY_name, items.item_name);
        values.put(Items.KEY_quantity,items.item_qt);
        values.put(Items.KEY_borrowDate,items.item_borrowDate);
        values.put(Items.KEY_returnDate,items.item_returnDate);
        values.put(Items.KEY_comment,items.item_comment);
        db.update(Items.TABLE, values, Items.KEY_orderID + "= ?", new String[]
                {
                        String.valueOf(items.item_orderID)
                });

        db.close(); // Closing database connection
    }


    public ArrayList<HashMap<String , String>> getItemList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "
                +Items.KEY_orderID +","
                +Items.KEY_name + ","
                +Items.KEY_quantity + ","
                +Items.KEY_borrowDate + ","
                +Items.KEY_returnDate + ","
                +Items.KEY_comment +
                " FROM " + Items.TABLE;

        ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list :3

        if(cursor.moveToFirst())
        {
           do
           {
               HashMap<String, String> item = new HashMap<String, String >();
               item.put("orderID",cursor.getString(cursor.getColumnIndex(Items.KEY_orderID) ));
               item.put("item ID",cursor.getString(cursor.getColumnIndex(Items.KEY_itemID) ));
               item.put("name",cursor.getString(cursor.getColumnIndex(Items.KEY_name) ));


           }while (cursor.moveToNext());


        }

        cursor.close();

        db.close();

        return itemList;
    }

    public Items getItemByorderId (int Id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "
                +Items.KEY_orderID + ", "
                +Items.KEY_itemID + ", "
                +Items.KEY_name + ", "
                +Items.KEY_quantity + ", "
                +Items.KEY_borrowDate + ", "
                +Items.KEY_returnDate+ ","
                +Items.KEY_comment +
                "FROM "+Items.TABLE + " WHERE " + Items.KEY_orderID + "=?";

        int iCount = 0 ;
        Items item = new Items();

        Cursor cursor = db.rawQuery(selectQuery , new String[]
                {String.valueOf(Id)});

        if (cursor.moveToFirst())
        {
            do {

                item.item_orderID = cursor.getInt(cursor.getColumnIndex(Items.KEY_orderID));
                item.item_itemID = cursor.getString(cursor.getColumnIndex(Items.KEY_itemID));
                item.item_name = cursor.getString(cursor.getColumnIndex(Items.KEY_name));
                item.item_qt = cursor.getInt(cursor.getColumnIndex(Items.KEY_quantity));
                item.item_borrowDate = cursor.getString(cursor.getColumnIndex(Items.KEY_borrowDate));
                item.item_returnDate = cursor.getString(cursor.getColumnIndex(Items.KEY_returnDate));
                item.item_comment = cursor.getString(cursor.getColumnIndex(Items.KEY_comment));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return item;

    }



}
