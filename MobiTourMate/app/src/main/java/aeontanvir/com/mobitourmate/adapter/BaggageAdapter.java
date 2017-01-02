package aeontanvir.com.mobitourmate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.R;
import aeontanvir.com.mobitourmate.pojo.Baggage;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class BaggageAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Baggage> baggageList;

    public BaggageAdapter(Context context, ArrayList<Baggage> baggageList) {
        super(context, R.layout.baggage_row, baggageList);
        this.context = context;
        this.baggageList = baggageList;



    }

    private static class ViewHolder{
        TextView bageTvName, bageTvNo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaggageAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.baggage_row, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.bageTvName = (TextView) convertView.findViewById(R.id.bageTvName);
            viewHolder.bageTvNo = (TextView) convertView.findViewById(R.id.bageTvNo);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (BaggageAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.bageTvName.setText(baggageList.get(position).getBageName());
        viewHolder.bageTvNo.setText(String.valueOf(baggageList.get(position).getBageNo()));


        return convertView;
    }
}
