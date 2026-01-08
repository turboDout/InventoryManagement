package com.example.inventorymanage;
import com.example.inventorymanage.data.StockFactory;
import com.example.inventorymanage.data.dbHelper;
import com.example.inventorymanage.StockCursorAdapter;
import com.example.inventorymanage.data.StockItem;

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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends  AppCompatActivity {
    private dbHelper DbHelper;
    private Context c;
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
    Boolean itemHasChanged = false;
    private static final int IMAGE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            getSupportActionBar().setHomeAsEnabled(true);
//        }


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


        Dbhelper = new dbHelper(c);
        itemId = getIntent().getLongExtra("itemId", 0);


        if (itemId == 0) {
            setTitle(getString(R.string.edit_activity_title_new_item));
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

        imageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openImageSelect();
                itemHasChanged = true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }
    @Override //DISCARD AND CLOSE ACTIVITY
    public void onBackPressed() {
        if (!ItemHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        // Show dialog that there are unsaved changes
        showUnsavedChanges(discardButtonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    private void showUnsavedChanges(
            DialogInterface.OnClickListener discardButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_msg);
        builder.setPositiveButton(R.string.discard, discardButtonListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void openImageSelect() {
        Intent intent;
        if(Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Select a picture"),PICK_IMAGE_REQUEST);
    }
    public void tryToOpenImageSelector() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
        openImageSelect();
    }
    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    private void addOneToAmount() {
        String previousValueS = amountEdit.getText().toString();
        int previousValue;
        if (previousValueS.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueS);
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
                previousValue = Integer.parseInt(previousValueS);
                quantityEdit.setText(String.valueOf(previousValue - 1));
            }
        }
    @SuppressLint("Range")
    private void addValuesToEditItem(long itemId) {
        Cursor cursor = DbHelper.readItem(itemId);
        cursor.moveToFirst();
        nameEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.NAME)));
        priceEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.PRICE)));
        amountEdit.setText(cursor.getString(cursor.getColumnIndex(StockFactory.StockEntry.AMOUNT)));
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

    private boolean addItems() {
        boolean isGood = true;
        if (!checkIfValueSet(nameEdit, "name")) {
            isGood = false;
        }
        if (!checkIfValueSet(priceEdit, "price")) {
            isGood= false;
        }
        if (!checkIfValueSet(amountEdit, "amount")) {
            isGood = false;
        }
        if (!checkIfValueSet(factoryNameEdit, "factory name")) {
            isGood = false;
        }
        if (!checkIfValueSet(factoryPhoneEdit, "factory phone")) {
            isGood = false;
        }
        if (!checkIfValueSet(factoryEmailEdit, "factory email")) {
            isGood = false;
        }
        if (actualUri == null && itemId == 0) {
            isGood = false;
            imageBtn.setError("Missing image");
        }
        if (!isGood) {
            return false;
        }

        if (itemId == 0) {
            StockItem item = new StockItem(
                    nameEdit.getText().toString().trim(),
                    priceEdit.getText().toString().trim(),
                    Integer.parseInt(amountEdit.getText().toString().trim()),
                    factoryNameEdit.getText().toString().trim(),
                    factoryPhoneEdit.getText().toString().trim(),
                    factoryEmailEdit.getText().toString().trim(),
                    actualUri.toString());
            DbHelper.insertItem(item);
        } else {
            int quantity = Integer.parseInt(amountEdit.getText().toString().trim());
            DbHelper.updateItem(itemId, amount);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] results) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (results.length > 0
                        && results[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageSelect();
                    // permission was granted
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int result, Intent data){
        //check that the request code matches the proper intent

        if(requestCode == IMAGE_REQUEST && result == Activity.RESULT_OK) {
            //uri to the document will be contained in the return intent and provided
            //to this method as a parameter. pull that uri using resultData.getData()

            if(data != null) {
                actualUri = data.getData();
                imageView.setImageURI(actualUri);
                imageView.invalidate();
            }
        }
    }
}
