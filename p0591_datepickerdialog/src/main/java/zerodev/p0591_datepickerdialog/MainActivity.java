package zerodev.p0591_datepickerdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int DIALOG_DATA = 2;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (TextView)findViewById(R.id.tvDate);
    }
    public void onclick(View v){
        showDialog(DIALOG_DATA);
    }

    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_DATA){
            DatePickerDialog tpd = new DatePickerDialog(this,myCallBack,myYear,myMonth,myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }
    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myYear = i;
            myMonth = i1;
            myDay = i2;
            tvDate.setText("Today is: " + myDay + "/" + myMonth + "/" + myYear);
        }
    };
}
