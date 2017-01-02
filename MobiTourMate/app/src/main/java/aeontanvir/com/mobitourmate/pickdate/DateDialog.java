package aeontanvir.com.mobitourmate.pickdate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by aeon on 24 Nov, 2016.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText eventEdtToDate;

    public DateDialog() {
    }

    public DateDialog(View view){
        eventEdtToDate = (EditText)view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+"-"+month+"-"+year;
        eventEdtToDate.setText(date);

    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return  new DatePickerDialog(getActivity(), this, year, month, day);
    }


}
