import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public final static String DB_name = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG = dbHelper.class.getCanonicalName();
    
    public dbHelper(Context context) {
        super(context, DB_name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StockContract.StockEntry.CREATE_TABLE_STOCK);
    }
 
    
    public void insertItem(StockItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(StockContract.StockEntry.COLUMN_NAME, item.getProductName());
        value.put(StockContract.StockEntry.COLUMN_PRICE, item.getPrice());
        value.put(StockContract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        value.put(StockContract.StockEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        value.put(StockContract.StockEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        value.put(StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        value.put(StockContract.StockEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(StockContract.StockEntry.TABLE_NAME, null, value);
    }
}
