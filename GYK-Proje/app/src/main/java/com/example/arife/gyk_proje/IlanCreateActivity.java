package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Arife on 27.08.2017.
 */

public class IlanCreateActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    private EditText contentText;
    private EditText descriptionText;
    private EditText emailText;
    private EditText phoneText;
    private EditText addressText;
    private TextView errorText;

    private Button helpButton;
    private Button noHelpButton;
    private TextInputLayout errorConText, errorDescText;

    private Toolbar btoolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_create);

        btoolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(btoolbar);

        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Sabitler");
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        contentText = (EditText) findViewById(R.id.contentText);
        descriptionText = (EditText) findViewById(R.id.descriptionText);
        emailText = (EditText) findViewById(R.id.emailText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        addressText = (EditText) findViewById(R.id.addressText);
        errorText = (TextView) findViewById(R.id.errorText);

        helpButton = (Button) findViewById(R.id.helpButton);
        noHelpButton = (Button) findViewById(R.id.noHelpButton);

        errorConText = (TextInputLayout) findViewById(R.id.errorConText);
        errorDescText = (TextInputLayout) findViewById(R.id.errorDescText);

        errorText.setVisibility(View.INVISIBLE);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (contentText.getText().toString().equals("")) {
                    errorConText.setError("Lütfen başlık giriniz!");
                }
                if (descriptionText.getText().toString().equals("")) {
                    errorDescText.setError("Lütfen açıklama giriniz!");
                }
                if (emailText.getText().toString().equals("") && phoneText.getText().toString().equals("") && addressText.getText().toString().equals("")) {
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Lütfen iletişim alanlarından en az birini doldurunuz!");
                }
                if (!contentText.getText().toString().equals("") && !descriptionText.getText().toString().equals("") && (!emailText.getText().toString().equals("") || !phoneText.getText().toString().equals("") || !addressText.getText().toString().equals(""))) {
                    Announcements announcements = new Announcements();
                    announcements.setuId(mUser.getUid());
                    announcements.setName(mUser.getDisplayName());
                    announcements.setPhotoUrl(mUser.getPhotoUrl().toString());
                    announcements.setContent(contentText.getText().toString());
                    announcements.setDescription(descriptionText.getText().toString());
                    announcements.setEmail(emailText.getText().toString());
                    announcements.setPhone(phoneText.getText().toString());
                    announcements.setAddress(addressText.getText().toString());
                    announcements.setTemplate(true);

                    mDatabaseReference.push().setValue(announcements);

                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }
                else{
                    errorText.setText("bilinmeyen bir hata oluştu tekrar deneyiniz");
                }
            }
        });

        noHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (contentText.getText().toString().equals("")) {
                    errorConText.setError("Lütfen başlık giriniz!");
                }
                if (descriptionText.getText().toString().equals("")) {
                    errorDescText.setError("Lütfen açıklama giriniz!");
                }
                if (emailText.getText().toString().equals("") && phoneText.getText().toString().equals("") && addressText.getText().toString().equals("")) {
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Lütfen iletişim alanlarından en az birini doldurunuz!");
                }
                if (!contentText.getText().toString().equals("") && !descriptionText.getText().toString().equals("") && (!emailText.getText().toString().equals("") || !phoneText.getText().toString().equals("") || !addressText.getText().toString().equals(""))) {
                    Announcements announcements = new Announcements();
                    announcements.setuId(mUser.getUid());
                    announcements.setName(mUser.getDisplayName());
                    announcements.setPhotoUrl(mUser.getPhotoUrl().toString());
                    announcements.setContent(contentText.getText().toString());
                    announcements.setDescription(descriptionText.getText().toString());
                    announcements.setEmail(emailText.getText().toString());
                    announcements.setPhone(phoneText.getText().toString());
                    announcements.setAddress(addressText.getText().toString());
                    announcements.setTemplate(false);

                    mDatabaseReference.push().setValue(announcements);

                    Intent in = new Intent(getApplicationContext(),IlanFragment.class);
                    startActivity(in);
                }
                else{
                    errorText.setText("bilinmeyen bir hata oluştu tekrar deneyiniz");
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
