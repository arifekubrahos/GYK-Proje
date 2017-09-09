package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

public class IlanFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private FloatingActionButton CurrentButton;

    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private List<Announcements> currentList =new ArrayList<Announcements>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("AAA","oncreateView");
        View view = inflater.inflate(R.layout.activity_ilan_recycler, container, false);


        CurrentButton = view.findViewById(R.id.CurrentButton);
        CurrentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),IlanCreateActivity.class);
                startActivity(i);
            }
        });

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Sabitler");
        currentList.add(new Announcements());

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView =  view.findViewById(R.id.currentRecyclerView);
        //düz bir şekilde sıralanması için
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //adapterumüz listemizi burada atıyoruz
        adapter = new IlanAdapter(currentList);
        recyclerView.setAdapter(adapter);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    currentList.add(ds.getValue(Announcements.class));

                    Announcements announcements = ds.getValue(Announcements.class);
                    String userId = announcements.getuId();
                    if(userId.equals(mUser.getUid())){
                        CurrentButton.setEnabled(false);
                        CurrentButton.setVisibility(View.GONE);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_recycler);

        cToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(cToolbar);


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
        adapter = new IlanAdapter(currentList);
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
                    Intent i = new Intent(getApplicationContext(),IlanCreateActivity.class);
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
    }*/
}
