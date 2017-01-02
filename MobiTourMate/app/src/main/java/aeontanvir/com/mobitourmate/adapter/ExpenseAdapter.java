package aeontanvir.com.mobitourmate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.R;
import aeontanvir.com.mobitourmate.pojo.Expense;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class ExpenseAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Expense> expenseList;

    public ExpenseAdapter(Context context, ArrayList<Expense> expenseList) {
        super(context, R.layout.expense_row, expenseList);
        this.context = context;
        this.expenseList = expenseList;
    }

    private static class ViewHolder{
        TextView expnTvDate, expnTvName, expnTvAmount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpenseAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense_row, parent, false);

            viewHolder = new ExpenseAdapter.ViewHolder();
            viewHolder.expnTvDate = (TextView) convertView.findViewById(R.id.expnTvDate);
            viewHolder.expnTvName = (TextView) convertView.findViewById(R.id.expnTvName);
            viewHolder.expnTvAmount = (TextView) convertView.findViewById(R.id.expnTvAmount);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.expnTvDate.setText(expenseList.get(position).getExpnTime());
        viewHolder.expnTvName.setText(expenseList.get(position).getExpnName());
        viewHolder.expnTvAmount.setText(String.valueOf(expenseList.get(position).getExpnAmount()));


        return convertView;
    }
}
