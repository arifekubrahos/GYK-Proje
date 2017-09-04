package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar cToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        cToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(cToolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter= new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Default(),"Günlük");
        adapter.addFragment(new IlanFragment(),"İlanlar");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
                Intent i = new Intent(getApplicationContext(),UserProfileActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.search:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
