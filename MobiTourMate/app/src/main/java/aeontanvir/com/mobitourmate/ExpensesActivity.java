package aeontanvir.com.mobitourmate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import aeontanvir.com.mobitourmate.adapter.ExpenseAdapter;
import aeontanvir.com.mobitourmate.db.DBExpensesManager;
import aeontanvir.com.mobitourmate.db.DBTourManager;
import aeontanvir.com.mobitourmate.pojo.Expense;
import aeontanvir.com.mobitourmate.pojo.Tour;

public class ExpensesActivity extends AppCompatActivity {

    TextView expnTvEvent, expnTvBalance;

    ListView listViewExpenseEvent;
    ArrayList<Expense> expenseList;
    Expense expense;
    ExpenseAdapter expenseAdapter;

    private int conTourId;

    DBExpensesManager dbExpensesManager;
    DBTourManager dbTourManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        dbExpensesManager = new DBExpensesManager(ExpensesActivity.this);
        dbTourManager = new DBTourManager(ExpensesActivity.this);


        expnTvEvent = (TextView) findViewById(R.id.expnTvEvent);
        expnTvBalance = (TextView) findViewById(R.id.expnTvBalance);

        conTourUpdate();
        listViewExpenseEvent = (ListView) findViewById(R.id.listViewExpenseEvent);
        dbExpensesManager = new DBExpensesManager(ExpensesActivity.this);
        expenseList = new ArrayList<>();
        
        displayExpenseList();
    }

    private void displayExpenseList() {
        expenseList = dbExpensesManager.getAllExpenseByTourId(conTourId);


        if(expenseList != null) {
            Collections.reverse(expenseList);
            //makeToast(expenseList.get(1).getExpnName().toString());
            expenseAdapter = new ExpenseAdapter(ExpensesActivity.this, expenseList);
            listViewExpenseEvent.setAdapter(expenseAdapter);
        }

    }


    private void conTourUpdate() {
        Bundle bundle = getIntent().getExtras();
        conTourId = bundle.getInt("conTourId");
        Tour tour = dbTourManager.getTour(conTourId);

        float totalExp = dbExpensesManager.getSumOfAmountByTourId(conTourId);

        float balance = tour.getTourBudget() - totalExp;

        expnTvEvent.setText("Event : "+ tour.getTourDestination());
        expnTvBalance.setText("Balance : " + String.format("%.2f", balance));
    }




    public void openCreateExpenseDialogWindow(View view) {
        final Dialog dialog = new Dialog(ExpensesActivity.this);
        dialog.setTitle("Add Expense");
        dialog.setContentView(R.layout.create_expense_dialog_window);
        dialog.show();

        final EditText expnEdtName = (EditText) dialog.findViewById(R.id.expnEdtName);
        final EditText expnEdtAmount = (EditText) dialog.findViewById(R.id.expnEdtAmount);

        Button expnBtnAddExpense = (Button) dialog.findViewById(R.id.expnBtnAddExpense);
        Button expnBtnCancel = (Button) dialog.findViewById(R.id.expnBtnCancel);


        expnBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        expnBtnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy  HH:mm:ss");
                String currentDateandTime = sdf.format(new Date());

                String name = String.valueOf(expnEdtName.getText());
                String amount = String.valueOf(expnEdtAmount.getText());

                if(name.isEmpty()){
                    makeToast("Enter expense type!");
                }else if(amount.isEmpty()){
                    makeToast("Enter amount!");
                }else{
                    Expense expense = new Expense(conTourId, name, currentDateandTime, Float.parseFloat(amount));

                    boolean test = dbExpensesManager.addExpense(expense);
                    if(test){
                        displayExpenseList();
                        conTourUpdate();
                        makeToast("Add expense successfully done!");
                        dialog.cancel();
                    }else {
                        makeToast("Information added failed!");
                    }

                }

            }
        });



    }



    public void makeToast(String messege){
        Toast.makeText(ExpensesActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
