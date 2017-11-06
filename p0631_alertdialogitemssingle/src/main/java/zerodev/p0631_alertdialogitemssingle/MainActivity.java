package zerodev.p0631_alertdialogitemssingle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;
    String data[] = { "one","two","three","four" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id){
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setSingleChoiceItems(data,-1,myClickListener);
                break;
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.select_dialog_singlechoice,data);
                adb.setSingleChoiceItems(adapter,-1,myClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.adapter);
                adb.setSingleChoiceItems(cursor,-1,DB.COLUMN_TXT,myClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok, myClickListener);
        return adb.create();
    }

    Dialog.OnClickListener myClickListener = new Dialog.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog) dialog).getListView();
            if(which == Dialog.BUTTON_POSITIVE)
                Log.d(LOG_TAG,"pos = " + lv.getCheckedItemPosition());
            else
                Log.d(LOG_TAG,"which = " + which);
        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }
}
