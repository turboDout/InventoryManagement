package com.example.inventorymanage;
import com.example.inventorymanage.data;
import com.example.inventorymanage.data.dbHelper;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {
    private dbHelper Dbhelper;
    private static final String LOG = DetailsActivity.class.getCanonicalName();
    private static final int REQUEST_READ_STORAGE = 1;

    EditText nameEdit;
    EditText priceEdit;
    EditText amountEdit;
    EditText factoryNameEdit;
    EditText factoryPhoneEdit;
    EditText factoryEmailEdit;
    long itemId;
    ImageButton decreaseAmount;
    ImageButton increaseAmount;
    Button imageBtn;
    ImageView imageView;
    Uri actualUri;
    Boolean ItemHasChanged = false;
    private static final int IMAGE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        setContentView(R.layout.activity_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsEnabled(true);
        }


        //perform edits
        nameEdit = (EditText) findViewById(R.id.product_name_edit);
        priceEdit = (EditText) findViewById(R.id.price_edit);
        amountEdit = (EditText) findViewById(R.id.amount_edit);
        factoryNameEdit = (EditText) findViewById(R.id.factory_name_edit);
        factoryPhoneEdit = (EditText) findViewById(R.id.factory_phone_edit);
        factoryEmailEdit = (EditText) findViewById(R.id.factory_email_edit);
        decreaseAmountEdit = (ImageButton) findViewById(R.id.decrease_amount);
        increaseAmountEdit = (ImageButton) findViewById(R.id.increase_amount);
        imageBtn = (Button) findViewById(R.id.select_image);
        imageView = (ImageView) findViewById(R.id.image_view);


        Dbhelper = new dbHelper(this);
        itemId = getIntent().getLongExtra("itemId", 0);


        if (itemId == 0) {
            setTitle(getString(R.string.ediot_activity_title_new_item));
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_item));
            addValuesToEditItem(itemId);
        }


        increaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneToAmount();
                itemHasChanged = true;
            }
        });

        decreaseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractOneFromAmount();
                itemHasChanged = true;
            }
        });
    }

    private void addOneToAmount() {
        String previousValueS = amountEdit.getText().toString();
        int previousValue;
        if (previousValueS.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Intenger.parseInt(previousValueS);
        }
    }
        private void subtractOneFromAmount() {
            String previousValueS = amountEdit.getText().toString();
            int previousValue;
            if (previousValueS.isEmpty()) {
                return;
            } else if (previousValueS.equals("0")) {
                return;
            } else {
                previousValue = Intenger.parseInt(previousValueS);
                quantityEdit.setText(String.valueOf(previousValue - 1));
            }
        }
    private void addValuesToEditItem(long itemId) {
        Cursor cursor = DbHelper.readItem(itemId);
        cursor.moveToFirst();
        nameEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.NAME)));
        priceEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.PRICE)));
        amountEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.Amount)));
        factoryNameEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.FACTORY_NAME)));
        factoryPhoneEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.FACTORY_PHONE)));
        factoryEmailEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.FACTORY_EMAIL)));
        imageView.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.IMAGE))));
        nameEdit.setEnabled(false);
        priceEdit.setEnabled(false);
        factoryNameEdit.setEnabled(false);
        factoryPhoneEdit.setEnabled(false);
        factoryEmailEdit.setEnabled(false);
        imageBtn.setEnabled(false);
    }
}
