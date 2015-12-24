package avia.stockmanagement;

/**
 * Created by Luck on 1-Dec-15.
 */
public class Items {

    // Labels Table Name

    public static final String TABLE = "Item List";

    // Labels Table Columns Names

    public static final String KEY_orderID = "Order ID";
    public static final String KEY_itemID = "Item ID";
    public static final String KEY_name = "Name";
    /* Should it change to int ? :3 */
    public static final String KEY_quantity = "Quantity";

    public static final String KEY_comment = "Comment";
    public static final String KEY_borrowDate = "Borrow Date";
    public static final String KEY_returnDate = "Return Date";


    // Property help us to keep data

    public int item_orderID;
    public String item_itemID;
    public String item_name;
    public int item_qt;
    public String item_comment;
    public String item_borrowDate;
    public String item_returnDate;


}
