package aeontanvir.com.mobitourmate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import aeontanvir.com.mobitourmate.adapter.BaggageAdapter;
import aeontanvir.com.mobitourmate.db.DBBaggageManager;
import aeontanvir.com.mobitourmate.pojo.Baggage;

public class BaggageActivity extends AppCompatActivity {

    private int conTourId;

    ListView listViewBaggageItem;
    ArrayList<Baggage> baggageList;
    BaggageAdapter baggageAdapter;


    DBBaggageManager dbBaggageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baggage);
        Bundle bundle = getIntent().getExtras();
        conTourId = bundle.getInt("conTourId");


        dbBaggageManager = new DBBaggageManager(BaggageActivity.this);
        listViewBaggageItem = (ListView) findViewById(R.id.listViewBaggageItem);
        baggageList = new ArrayList<>();
        displayBaggageList();

    }

    private void displayBaggageList() {
        baggageList = dbBaggageManager.getAllBaggageByTourId(conTourId);
        if(baggageList != null) {
            Collections.reverse(baggageList);
            baggageAdapter = new BaggageAdapter(BaggageActivity.this, baggageList);
            listViewBaggageItem.setAdapter(baggageAdapter);
        }
    }


    public void addBaggageItem(View view) {
        final Dialog dialog = new Dialog(BaggageActivity.this);
        dialog.setTitle("Add Baggage Item");
        dialog.setContentView(R.layout.create_baggage_item_dialog_window);
        dialog.show();

        final EditText bageEdtItem = (EditText) dialog.findViewById(R.id.bageEdtItem);
        final EditText bageEdtNo = (EditText) dialog.findViewById(R.id.bageEdtNo);

        Button bageBtnItemAdd = (Button) dialog.findViewById(R.id.bageBtnItemAdd);
        Button bageBtnCancel = (Button) dialog.findViewById(R.id.bageBtnCancel);

        bageBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        bageBtnItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = String.valueOf(bageEdtItem.getText());
                String no = String.valueOf(bageEdtNo.getText());

                if(item.isEmpty()){
                    makeToast("Enter item name!");
                }else if(no.isEmpty()){
                    makeToast("Enter item no");
                }else{
                    Baggage baggage = new Baggage(conTourId, item, Integer.parseInt(no));

                    boolean test = dbBaggageManager.addBaggageItem(baggage);
                    if(test){
                        displayBaggageList();
                        makeToast("Add item successfully done!");
                        dialog.cancel();
                    }else {
                        makeToast("Information added failed!");
                    }

                }

            }
        });
    }

    public void makeToast(String messege){
        Toast.makeText(BaggageActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
