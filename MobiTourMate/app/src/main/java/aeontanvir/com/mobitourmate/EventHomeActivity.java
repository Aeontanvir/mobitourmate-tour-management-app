package aeontanvir.com.mobitourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventHomeActivity extends AppCompatActivity {

    TextView homeTvEvent, homeTvBudget;

    private int conTourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        homeTvEvent = (TextView) findViewById(R.id.homeTvEvent);
        homeTvBudget = (TextView) findViewById(R.id.homeTvBudget);



        conTourUpdate();
    }

    private void conTourUpdate() {
        Bundle bundle = getIntent().getExtras();
        conTourId = bundle.getInt("conTourId");
        String conTourName = bundle.getString("conTourName");
        String conTourBudget = bundle.getString("conTourBudget");
        homeTvEvent.setText("Event : "+conTourName);
        homeTvBudget.setText("Event : "+conTourBudget);
    }

    public void goEventExpenseActivity(View view) {
        Intent i = new Intent(EventHomeActivity.this, ExpensesActivity.class);
        i.putExtra("conTourId", conTourId);
        startActivity(i);
    }


    public void makeToast(String messege){
        Toast.makeText(EventHomeActivity.this, messege, Toast.LENGTH_SHORT).show();
    }

    public void goEventBaggageActivity(View view) {
        Intent i = new Intent(EventHomeActivity.this, BaggageActivity.class);
        i.putExtra("conTourId", conTourId);
        startActivity(i);
    }

    public void goMomentCapture(View view) {
        Intent i = new Intent(EventHomeActivity.this, MomentActivity.class);
        i.putExtra("conTourId", conTourId);
        startActivity(i);
    }
}
