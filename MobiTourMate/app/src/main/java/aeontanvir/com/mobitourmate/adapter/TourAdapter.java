package aeontanvir.com.mobitourmate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.R;
import aeontanvir.com.mobitourmate.pojo.Tour;

/**
 * Created by aeon on 24 Nov, 2016.
 */

public class TourAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Tour> tourList;

    public TourAdapter(Context context, ArrayList<Tour> tourList) {
        super(context, R.layout.event_row, tourList );
        this.context = context;
        this.tourList = tourList;
    }

    private static class ViewHolder{
        TextView tvEventRowName, tvEventRowBudget, tvEventRowStart, tvEventRowEnd;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TourAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_row, parent, false);

            viewHolder = new TourAdapter.ViewHolder();
            viewHolder.tvEventRowName = (TextView) convertView.findViewById(R.id.tvEventRowName);
            viewHolder.tvEventRowBudget = (TextView) convertView.findViewById(R.id.tvEventRowBudget);
            viewHolder.tvEventRowStart = (TextView) convertView.findViewById(R.id.tvEventRowStart);
            viewHolder.tvEventRowEnd = (TextView) convertView.findViewById(R.id.tvEventRowEnd);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvEventRowName.setText(tourList.get(position).getTourDestination());
        viewHolder.tvEventRowBudget.setText("Est Budget : "+String.valueOf(tourList.get(position).getTourBudget())+ " TK");
        viewHolder.tvEventRowStart.setText(tourList.get(position).getTourStartDate());
        viewHolder.tvEventRowEnd.setText(tourList.get(position).getTourEndDate());


        return convertView;
    }
}


