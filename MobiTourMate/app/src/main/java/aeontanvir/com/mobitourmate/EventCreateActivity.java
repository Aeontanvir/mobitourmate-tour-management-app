package aeontanvir.com.mobitourmate;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import aeontanvir.com.mobitourmate.db.DBTourManager;
import aeontanvir.com.mobitourmate.pickdate.DateDialog;
import aeontanvir.com.mobitourmate.pojo.Tour;

public class EventCreateActivity extends AppCompatActivity {

    EditText eventEdtDestination, eventEdtBudget, eventEdtFromDate, eventEdtToDate;

    DBTourManager dbTourManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        dbTourManager = new DBTourManager(EventCreateActivity.this);

        eventEdtDestination = (EditText) findViewById(R.id.eventEdtDestination);
        eventEdtBudget = (EditText) findViewById(R.id.eventEdtBudget);
        eventEdtFromDate = (EditText) findViewById(R.id.eventEdtFromDate);
        eventEdtToDate = (EditText) findViewById(R.id.eventEdtToDate);
    }

    @Override
    public void onStart(){
        super.onStart();

        eventEdtToDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        eventEdtFromDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }

    public void createNewEvent(View view) {
        String name = String.valueOf(eventEdtDestination.getText());
        String budget = String.valueOf(eventEdtBudget.getText());
        String startDate = String.valueOf(eventEdtFromDate.getText());
        String endDate = String.valueOf(eventEdtToDate.getText());

        if(name.equals("")){
            makeToast("Enter event destination");
        }else if(budget.equals("")){
            makeToast("Enter event budget");
        }else if(startDate.equals("")){
            makeToast("Enter event start date");
        }else if(endDate.equals("")){
            makeToast("Enter event end date");
        }else{
            Tour tour = new Tour(name, Float.parseFloat(budget), startDate, endDate);

            boolean test = dbTourManager.addTour(tour);
            if(test){
                makeToast("Create event successfully done!");
                finish();
            }else {
                makeToast("Information added failed!");
            }


        }

    }

    public void makeToast(String messege){
        Toast.makeText(EventCreateActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
