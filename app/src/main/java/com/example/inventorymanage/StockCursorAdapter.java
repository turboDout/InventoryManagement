package com.example.inventorymanage;
import com.example.inventorymanage.data.dbHelper;
import com.example.inventorymanage.data.StockItem;
import com.example.inventorymanage.data.StockFactory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;




public class StockCursorAdapter extends CursorAdapter {
    private final Context context;

    public StockCursorAdapter(Context c, Cursor cursor) {
        super(c, cursor, 0);
        this.context = c;
    }
     @Override
     public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
     }

     public void bindView(View v, final Context context, final Cursor cursor) {
        TextView nameTextView = (TextView) v.findViewById(R.id.product_name);
        TextView amountTextView = (TextView)v.findViewById(R.id.amount);
        TextView priceTextView = (TextView)v.findViewById(R.id.price);
        ImageView sale = (ImageView)v.findViewById(R.id.sale);
        ImageView image = (ImageView)v.findViewById(R.id.image_view);

        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.NAME));

     }
}
