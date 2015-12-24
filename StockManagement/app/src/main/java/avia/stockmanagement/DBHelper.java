package avia.stockmanagement;

import android.content.ClipData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.*;

import java.text.SimpleDateFormat;

/**
 * Created by GT70 on 17-Dec-15.
 */
public class DBHelper extends SQLiteOpenHelper {

    /*
       Version number to upgrade database version each time if you
       Add , Edit table , the version number need to change <3
    */
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "item.db";

    public DBHelper(Context context)
    {
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        // Every necessary tables will create here !!

        // Let me add the date first :3

        /*
        *** I will implement this part later after I got most structure correct :3 ***
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
        */

        String CREATE_TABLE_ITEMLIST = "CREATE TABLE" + Items.TABLE + "("
                + Items.KEY_orderID + "INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Items.KEY_itemID + "TEXT ,"
                +Items.KEY_name + "TEXT ,"
                +Items.KEY_quantity + "INTEGER ,"
                +Items.KEY_borrowDate + "INTEGER ,"
                +Items.KEY_returnDate + "INTEGER ,"
                +Items.KEY_comment + "TEXT )";

        db.execSQL((CREATE_TABLE_ITEMLIST));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed , all data will be gone ! ( will fix this part later )

        db.execSQL("DROP TABLE IF EXISTS "+ Items.TABLE);

        // Create tables again

        onCreate(db);

    }
}
