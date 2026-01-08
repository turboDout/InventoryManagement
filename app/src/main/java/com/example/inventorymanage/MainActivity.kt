package com.example.inventorymanage;

import com.example.inventorymanage.DetailsActivity;
import com.example.inventorymanage.data.StockFactory;
import com.example.inventorymanage.StockCursorAdapter;
import com.example.inventorymanage.data.StockItem;
import com.example.inventorymanage.data.dbHelper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;


public class MainActivity extends ComponentActivity {

    private final static String LOG = MainActivity.class.getCanonicalName();
    dbHelper DbHelper;
    StockCursorAdapter adapter;
    int lastItem = 0;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper = new dbHelper(this);

        final ActionButton fab = (ActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = DbHelper.readStock();

        adapter = new StockCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(c, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        DbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                adapter.swapCursor(DbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        StockItem chocolates = new StockItem(
                "Chocolates",
                "$3",
                12,
                "Nestle",
                "+1 999 999 9999",
                "customer@nestle.com",
                "android.resource://com.example.inventorymanage/drawable/");
        DbHelper.insertItem(chocolates);

        StockItem hotFries = new StockItem(
                "Hot Fries",
                "$4",
                20,
                "Hotfries",
                "+1 999 999 9999",
                "customer@hotfries.com",
                "android.resource://com.example.inventorymanage/drawable/");
        DbHelper.insertItem(hotFries);

        StockItem potatoes = new StockItem(
                "Potatoes",
                "$5",
                120,
                "Potato Company",
                "+1 999 999 9999",
                "customer@potatocompany.com",
                "android.resource://com.example.inventorymanage/drawable/");
        DbHelper.insertItem(potatoes);

        StockItem soda = new StockItem(
                "soda",
                "$3",
                200,
                "Soda Company",
                "+1 999 999 9999",
                "customer@sodacompany.com",
                "android.resource://com.example.inventorymanage/drawable/soda");
        DbHelper.insertItem(soda);

        StockItem fruits = new StockItem(
                "Fruit",
                "$5",
                12,
                "Fruit Company",
                "+1 999 999 9999",
                "customer@fruitcompany.com",
                "android.resource://com.example.inventorymanage/drawable/fruits");
        DbHelper.insertItem(fruits);

        StockItem gummies = new StockItem(
                "Gummies",
                "$3",
                62,
                "Gummy Company",
                "+1 999 999 9999",
                "customer@gummycompany.com",
                "android.resource://com.example.inventorymanage/drawable/gummies");
        DbHelper.insertItem(gummies);

        StockItem chips = new StockItem(
                "Chips",
                "$5",
                200,
                "Chip Company",
                "+1 999 999 9999",
                "customer@chipscompany.com",
                "android.resource://com.example.inventorymanage/drawable/chips");
        DbHelper.insertItem(chips);
}