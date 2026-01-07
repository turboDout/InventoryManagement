import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StockCursorAdapter extends CursorAdapter {
    private final Activity activity;

    public StockCursorAdapter(Activity context, Cursor c) {
        super(context, c, 0);
        this.activity = context;
    }
    // @Override
    // public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    //     return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    // }
}
