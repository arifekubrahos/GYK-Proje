package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class CreateCurrentActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_create);

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


        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (contentText.getText().toString().equals("")) {
                    errorText.setText("İlan başlığını boş bırakmayınız!");
                }
                if (descriptionText.getText().toString().equals("")) {
                    errorText.setText("İlan açıklaması boş bırakmayınız!");
                }
                if (emailText.getText().toString().equals("") && phoneText.getText().toString().equals("") && addressText.getText().toString().equals("")) {
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

                    Intent in = new Intent(getApplicationContext(),CurrentActivity.class);
                    startActivity(in);
                    finish();
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
                    errorText.setText("İlan başlığını boş bırakmayınız!");
                }
                if (descriptionText.getText().toString().equals("")) {
                    errorText.setText("İlan açıklaması boş bırakmayınız!");
                }
                if (emailText.getText().toString().equals("") && phoneText.getText().toString().equals("") && addressText.getText().toString().equals("")) {
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

                    Intent in = new Intent(getApplicationContext(),CurrentActivity.class);
                    startActivity(in);
                    finish();
                }
                else{
                    errorText.setText("bilinmeyen bir hata oluştu tekrar deneyiniz");
                }
            }
        });



    }
}
