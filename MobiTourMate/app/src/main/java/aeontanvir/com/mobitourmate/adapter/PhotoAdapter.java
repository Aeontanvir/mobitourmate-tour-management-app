package aeontanvir.com.mobitourmate.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import aeontanvir.com.mobitourmate.R;
import aeontanvir.com.mobitourmate.pojo.Photo;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class PhotoAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Photo> photoList;

    public PhotoAdapter(Context context, ArrayList<Photo> photoList) {
        super(context, R.layout.photo_row, photoList);
        this.context = context;
        this.photoList = photoList;



    }

    private static class ViewHolder{
        TextView tvPhotoTime;
        ImageView ivPhoto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoAdapter.ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photo_row, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvPhotoTime = (TextView) convertView.findViewById(R.id.tvPhotoTime);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (PhotoAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvPhotoTime.setText(photoList.get(position).getPhotoTime());


        String imagePath = Environment.getExternalStorageDirectory().toString() + "/mobitourmate_images/"+photoList.get(position).getPhotoName();

        Uri uri = Uri.fromFile(new File(imagePath));

        Picasso.with(context).load(uri).into(viewHolder.ivPhoto);


        return convertView;
    }
}
