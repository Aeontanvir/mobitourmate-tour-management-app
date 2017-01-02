package aeontanvir.com.mobitourmate;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import aeontanvir.com.mobitourmate.db.DBLoginManager;
import aeontanvir.com.mobitourmate.pojo.User;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "Message";
    private static final int CAMERA_REQUEST = 1888;


    TextView regEdtFullname, regEdtUsername, regEdtPassword, regEdtPhoneNo, regEdtAddress;
    String photoSource = "";
    ImageView profilePhoto;
    Bitmap photo;



    DBLoginManager dbLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbLoginManager = new DBLoginManager(RegisterActivity.this);

        regEdtFullname = (TextView) findViewById(R.id.regEdtFullname);
        regEdtUsername = (TextView) findViewById(R.id.regEdtUsername);
        regEdtPassword = (TextView) findViewById(R.id.regEdtPassword);
        regEdtPhoneNo = (TextView) findViewById(R.id.regEdtPhoneNo);
        regEdtAddress = (TextView) findViewById(R.id.regEdtAddress);
        profilePhoto = (ImageView) findViewById(R.id.profilePhoto);



    }


    public void takePhoto(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            profilePhoto.setImageBitmap(photo);
        }
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+"/mtm");
        // Create imageDir
        File mypath=new File(directory,"profile_mtm.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.d(TAG, "Ok123");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath()+"/profile_mtm.jpg";
    }



    public void submitUserInfo(View view) {


        if(String.valueOf(regEdtFullname.getText()).equals("")){
            makeToast("Enter your fullname!");
        }else if(String.valueOf(regEdtUsername.getText()).equals("")){
            makeToast("Enter a username!");
        }else if(String.valueOf(regEdtPassword.getText()).equals("")){
            makeToast("Enter a password!");
        }else if(String.valueOf(regEdtPhoneNo.getText()).equals("")){
            makeToast("Enter your phone no!");
        }else if(String.valueOf(regEdtAddress.getText()).equals("")){
            makeToast("Enter your address!");
        }else{
            User user = new User();
            user.setFullname(String.valueOf(regEdtFullname.getText()));
            user.setUsername(String.valueOf(regEdtUsername.getText()));
            user.setPassword(String.valueOf(regEdtPassword.getText()));
            user.setContactno(String.valueOf(regEdtPhoneNo.getText()));
            user.setAddress(String.valueOf(regEdtAddress.getText()));

            boolean test = dbLoginManager.addUser(user);

            if(test){
                makeToast("Information successfully added!");
                Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }else {
                makeToast("Information added failed!");
            }


        }


    }

    public void makeToast(String messege){
        Toast.makeText(RegisterActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
