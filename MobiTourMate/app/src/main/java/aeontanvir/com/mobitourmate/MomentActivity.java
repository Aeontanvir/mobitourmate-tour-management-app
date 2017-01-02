package aeontanvir.com.mobitourmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import aeontanvir.com.mobitourmate.adapter.PhotoAdapter;
import aeontanvir.com.mobitourmate.db.DBPhotoManager;
import aeontanvir.com.mobitourmate.pojo.Photo;

public class MomentActivity extends AppCompatActivity {
    private int conTourId;

    static final int REQUEST_IMAGE_CAPTURE = 180;


    DBPhotoManager dbPhotoManager;

    ListView listViewPhotoItem;
    ArrayList<Photo> photoList;
    PhotoAdapter photoAdapter;

    ImageView tempImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
        Bundle bundle = getIntent().getExtras();
        conTourId = bundle.getInt("conTourId");

        dbPhotoManager = new DBPhotoManager(MomentActivity.this);
        listViewPhotoItem = (ListView) findViewById(R.id.listViewPhotoItem);
        photoList = new ArrayList<>();

        displayPhotoList();

    }

    private void displayPhotoList() {
        photoList = dbPhotoManager.getAllPhotoByTourId(conTourId);
        if(photoList != null) {
            Collections.reverse(photoList);
            photoAdapter = new PhotoAdapter(MomentActivity.this, photoList);
            listViewPhotoItem.setAdapter(photoAdapter);
        }
    }

    public void takeMomentPhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            saveImage(imageBitmap);
        }
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/mobitourmate_images");
        myDir.mkdirs();
        String n = String.valueOf(System.currentTimeMillis());
        String fname = "mtm-"+ n +".jpg";

        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy  HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        boolean test = dbPhotoManager.addPhoto(new Photo(conTourId, fname, currentDateandTime));
        if(test){
            makeToast("Take photo done!");
            displayPhotoList();
        }else {
            makeToast("Information added failed!");
        }


    }


    public void makeToast(String messege){
        Toast.makeText(MomentActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
