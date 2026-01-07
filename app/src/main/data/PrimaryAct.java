import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AbsListView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import InventoryManagement.data.dbHelper;
import InventoryManagement.data.StockCursorAdapter;

public class PrimaryAct extends  AppCompatActivity  {
    private final static String LOG = PrimaryAct.class.getCanonicalName();
    dbHelper _dbHelper;
    StockCursorAdapter adapter;
    int lastItem = 0;

    @Override 
    protected void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        setContentView(R.layout.activity_main);
        _dbHelper = new dbHelper(this);

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
    dbHelper.insertItem(chocolates);

  }
}
