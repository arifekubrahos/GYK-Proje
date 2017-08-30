package com.example.arife.gyk_proje;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;

/**
 * Created by Arife on 26.08.2017.
 */

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseUser mUser;

    private TextView nameText;
    private TextView mailText;
    private Button goFaceButton;
    private Button listButton;
    private Button signOutButton;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imageView = (ImageView) findViewById(R.id.imageView);
        nameText = (TextView) findViewById(R.id.nameText);
        mailText = (TextView) findViewById(R.id.mailText);
        signOutButton = (Button) findViewById(R.id.signOutButton);

        //burası kişi profiline gitme olucak ona sonra bakıcaz
        goFaceButton = (Button) findViewById(R.id.goFaceButton);
        listButton = (Button) findViewById(R.id.listButton);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser != null){
            nameText.setText(mUser.getDisplayName());
            mailText.setText(mUser.getEmail());

            new DownloadImage().execute(String.valueOf(mUser.getPhotoUrl()));
        }
        else{
            Toast.makeText(getApplicationContext(),"Bilgiler yüklenirken hata!",Toast.LENGTH_LONG).show();
        }
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent i = new Intent(getApplicationContext(), FacebookActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urldisplay = strings[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}