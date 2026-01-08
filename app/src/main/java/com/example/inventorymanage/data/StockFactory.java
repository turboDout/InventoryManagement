package com.example.inventorymanage.data;

import android.provider.BaseColumns;


public class StockFactory {

    public StockFactory() {
    }

    public static final class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";

        public static final String _ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String AMOUNT = "amount";
        public static final String FACTORY_NAME = "factory_name";
        public static final String FACTORY_PHONE = "factory_phone";
        public static final String FACTORY_EMAIL = "factory_email";
        public static final String IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                StockFactory.StockEntry.TABLE_NAME + "(" +
                StockFactory.StockEntry._ID + " INTEGER PRIMARY KEY," +
                StockFactory.StockEntry.NAME + " TEXT (NO NULL)," +
                StockFactory.StockEntry.PRICE + " TEXT (NO NULL)," +
                StockFactory.StockEntry.AMOUNT + " INTEGER (NO NULL) DEFAULT 0," +
                StockFactory.StockEntry.FACTORY_NAME + " TEXT (NO NULL)," +
                StockFactory.StockEntry.FACTORY_PHONE + " TEXT (NO NULL)," +
                StockFactory.StockEntry.FACTORY_EMAIL + " TEXT (NO NULL)," +
                StockEntry.IMAGE + " TEXT (NO NULL)" + ");";
    }
}