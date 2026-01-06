import android.provider.BaseColumns;

/**
 * Created by lara on 2/10/16.
 */
public class StockFactory {

    public StockContract() {
    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String QUANTITY = "quantity";
        public static final String SUPPLIER_NAME = "supplier_name";
        public static final String SUPPLIER_PHONE = "supplier_phone";
        public static final String SUPPLIER_EMAIL = "supplier_email";
        public static final String IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                StockFactory.StockEntry.TABLE_NAME + "(" +
                StockFactory.StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StockFactory.StockEntry.NAME + " TEXT NOT NULL," +
                StockFactory.StockEntry.PRICE + " TEXT NOT NULL," +
                StockFactory.StockEntry.QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                StockFactory.StockEntry.SUPPLIER_NAME + " TEXT NOT NULL," +
                StockFactory.StockEntry.SUPPLIER_PHONE + " TEXT NOT NULL," +
                StockFactory.StockEntry.SUPPLIER_EMAIL + " TEXT NOT NULL," +
                StockEntry.IMAGE + " TEXT NOT NULL" + ");";
    }
}