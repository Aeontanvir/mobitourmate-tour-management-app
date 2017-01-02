package aeontanvir.com.mobitourmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import aeontanvir.com.mobitourmate.adapter.TourAdapter;
import aeontanvir.com.mobitourmate.db.DBLoginManager;
import aeontanvir.com.mobitourmate.db.DBTourManager;
import aeontanvir.com.mobitourmate.pojo.Tour;
import aeontanvir.com.mobitourmate.pojo.User;
import aeontanvir.com.mobitourmate.preference.Preference;

public class HomeActivity extends AppCompatActivity {

    DBLoginManager dbLoginManager;

    Preference preference;

    public static final String TAG = "Message";

    //public static final int EVENT_CREATE_REQUEST_CODE = 1800;

    ListView listViewTourEvent;
    ArrayList<Tour> tourList;
    Tour tour;
    TourAdapter tourAdapter;
    DBTourManager dbTourManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbLoginManager = new DBLoginManager(HomeActivity.this);
        preference = new Preference(this);

        checkLoginState();

        // listView
        listViewTourEvent = (ListView) findViewById(R.id.listViewTourEvent);
        dbTourManager = new DBTourManager(HomeActivity.this);
        tourList = new ArrayList<>();
        displayTourList();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        displayTourList();
    }

    private void displayTourList() {

        tourList = dbTourManager.getAllTour();
        if(tourList != null) {
            Collections.reverse(tourList);

            tourAdapter = new TourAdapter(HomeActivity.this, tourList);

            listViewTourEvent.setAdapter(tourAdapter);

            listViewTourEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(HomeActivity.this, EventHomeActivity.class);
                    i.putExtra("conTourId", tourList.get(position).getTourId());
                    i.putExtra("conTourName", String.valueOf(tourList.get(position).getTourDestination()));
                    i.putExtra("conTourBudget", String.valueOf(tourList.get(position).getTourBudget()));
                    startActivity(i);
                }
            });
        }

    }

    private void checkLoginState() {

        if(isUserEmpty()){
            Intent i = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(i);
            finish();
        }else {

            if (!isLogedIn()) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }

    }

    private boolean isLogedIn() {
        String test = preference.getUserData();
        if(test.equals("in")){
            return true;
        }
        return false;
    }

    private boolean isUserEmpty() {
        ArrayList<User> userArrayList = dbLoginManager.getAllUser();

        if(userArrayList == null){
            return true;
        }
        return false;
    }

    public void createTravelEvent(View view) {
        Intent intent=new Intent(HomeActivity.this,EventCreateActivity.class);
        startActivity(intent);
    }




    public void makeToast(String messege){
        Toast.makeText(HomeActivity.this, messege, Toast.LENGTH_SHORT).show();
    }


}
