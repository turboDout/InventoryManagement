package com.example.inventorymanage.data;

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
        db.execSQL(StockFactory.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
    public void insertItem(StockItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(StockFactory.StockEntry.NAME, item.getProductName());
        value.put(StockFactory.StockEntry.PRICE, item.getPrice());
        value.put(StockFactory.StockEntry.QUANTITY, item.getQuantity());
        value.put(StockFactory.StockEntry.SUPPLIER_NAME, item.getSupplierName());
        value.put(StockFactory.StockEntry.SUPPLIER_PHONE, item.getSupplierPhone());
        value.put(StockFactory.StockEntry.SUPPLIER_EMAIL, item.getSupplierEmail());
        value.put(StockFactory.StockEntry.IMAGE, item.getImage());
        long id = db.insert(StockFactory.StockEntry.TABLE_NAME, null, value);
    }

        public Cursor readFactory() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                StockFactory.StockEntry._ID,
                StockFactory.StockEntry.NAME,
                StockFactory.StockEntry.PRICE,
                StockFactory.StockEntry.QUANTITY,
                StockFactory.StockEntry.SUPPLIER_NAME,
                StockFactory.StockEntry.SUPPLIER_PHONE,
                StockFactory.StockEntry.SUPPLIER_EMAIL,
                StockFactory.StockEntry.IMAGE
        };
        Cursor cursor = db.query(
                StockFactory.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                StockFactory.StockEntry._ID,
                StockFactory.StockEntry.NAME,
                StockFactory.StockEntry.PRICE,
                StockFactory.StockEntry.QUANTITY,
                StockFactory.StockEntry.SUPPLIER_NAME,
                StockFactory.StockEntry.SUPPLIER_PHONE,
                StockFactory.StockEntry.SUPPLIER_EMAIL,
                StockFactory.StockEntry.IMAGE
        };
        String selection = StockFactory.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                StockFactory.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

      public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(StockFactory.StockEntry.QUANTITY, quantity);
        String selection = StockFactory.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(StockFactory.StockEntry.TABLE_NAME,
                value, selection, selectionArgs);
    }

     public void sellAItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues value = new ContentValues();
        values.put(StockFactory.StockEntry.QUANTITY, newQuantity);
        String selection = StockFactory.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(StockFactory.StockEntry.TABLE_NAME,
                value, selection, selectionArgs);
    }
}
