package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Arife on 8.09.2017.
 */

public class PostActivity extends AppCompatActivity {
    private FirebaseUser mUser;
    private String uId;
    private DatabaseReference dbRef;

    private TextView ilanText;
    private TextView ilanDescText;
    private TextView ilanMailText;
    private TextView ilanPhoneText;
    private TextView ilanAdresText;
    private CardView cardViewIlan;

    private Button deleteIlanButton;
    private Button createIlanButton;

    private Toolbar btoolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_post_list);

        btoolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(btoolbar);

        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //günlüklere liste koyabiliriz eğer count tutacaksak o zaman iki layouta ihtiyacımız olur
        //geri tuşu koyucaz buraya !
        ilanText = (TextView) findViewById(R.id.ilanText);
        ilanDescText = (TextView) findViewById(R.id.ilanDescText);
        ilanMailText = (TextView) findViewById(R.id.ilanMailText);
        ilanPhoneText = (TextView) findViewById(R.id.ilanPhoneText);
        ilanAdresText = (TextView) findViewById(R.id.ilanAddressText);

        deleteIlanButton = (Button) findViewById(R.id.deleteIlanButton);
        createIlanButton = (Button) findViewById(R.id.createIlan);
        createIlanButton.setVisibility(View.GONE);

        cardViewIlan = (CardView) findViewById(R.id.cardViewIlan);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        uId = mUser.getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Sabitler");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Announcements a= ds.getValue(Announcements.class);
                    //String key= ds.getKey();

                    if(a.getuId().equals(uId)){
                        ilanText.setText(a.getContent());
                        ilanDescText.setText(a.getDescription());
                        ilanMailText.setText(a.getEmail());
                        ilanPhoneText.setText(a.getPhone());
                        ilanAdresText.setText(a.getAddress());
                        final String key= ds.getKey();
                        deleteIlanButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dbRef.child(key).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                        if(databaseError==null){
                                            Toast.makeText(getApplicationContext(),"İlan Silindi",Toast.LENGTH_SHORT).show();
                                            ilanText.setText("");
                                            ilanDescText.setText("");
                                            ilanMailText.setText("");
                                            ilanPhoneText.setText("");
                                            ilanAdresText.setText("");
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Hata oluştu tekrar deneyiniz: "+databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                                //dbRef.child(key).removeValue();
                            }
                        });
                    }




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
