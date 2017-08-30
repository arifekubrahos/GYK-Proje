package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arife on 29.08.2017.
 */

public class CurrentActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar cToolbar;
    private Toolbar bToolbar;
    private FloatingActionButton newCurrentButton;

    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private List<Announcements> currentList =new ArrayList<Announcements>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_current);

        cToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(cToolbar);

        bToolbar = (Toolbar) findViewById(R.id.tool_bar_bottom);


        newCurrentButton = (FloatingActionButton) findViewById(R.id.newCurrentButton);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Sabitler");
        currentList.add(new Announcements());

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Announcements announcements = ds.getValue(Announcements.class);
                    String userId = announcements.getuId();
                    if(userId == mUser.getUid()){
                        newCurrentButton.setEnabled(false);
                        newCurrentButton.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.currentRecyclerView);
        //düz bir şekilde sıralanması için
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //adapterumüz listemizi burada atıyoruz
        adapter = new RecyclerCurrentAdapter(currentList);
        recyclerView.setAdapter(adapter);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    currentList.add(ds.getValue(Announcements.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(newCurrentButton.isEnabled()){
            newCurrentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),CreateCurrentActivity.class);
                    startActivity(i);

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_up,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.profile_page:
               break;
           case R.id.search:
               break;
       }

        return super.onOptionsItemSelected(item);
    }
}
