
package com.example.inventorymanage.data;


import com.example.inventorymanage.StockCursorAdapter;
import com.example.inventorymanage.data.StockItem;
import com.example.inventorymanage.data.StockFactory;

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


import com.example.inventorymanage.data.dbHelper;
import com.example.inventorymanage.data.StockItem;
import com.example.inventorymanage.data.StockFactory;

public class PrimaryAct extends  ComponentActivity  {
    private final static String LOG = PrimaryAct.class.getCanonicalName();
    dbHelper DbHelper;
    StockCursorAdapter adapter;
    int lastItem = 0;

    @Override 
    protected void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        setContentView(R.layout.activity_main);
        DbHelper = new dbHelper(this);

    } 

  private void dummyData() {
    StockItem chocolates = new StockItem(
        "Chocolate",
        "$3",
        25,
        "Nestle",
        "+1 000 000 000",
        "Nestle@nestle.com"
        // picture goes here ""
    );
    DbHelper.insertItem(chocolates);

  }
}
