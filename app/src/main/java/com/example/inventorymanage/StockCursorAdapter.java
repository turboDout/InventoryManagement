package com.example.inventorymanage;

import android.database.Cursor;
import android.widget.CursorAdapter;


//do work here
public class StockCursorAdapter extends CursorAdapter {
    private final MainActivity activity;

    public StockCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.activity = context;
    }
    // @Override
    // public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    //     return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    // }
}
