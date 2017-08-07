package com.example.arife.gyk_proje;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;

/**
 * Created by Arife on 7.08.2017.
 */

public class HelpListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HelpListActivity.this);

        alertDialog.setMessage("lütfen GPS'inizi açın liste yüklendikten sonra tekrar kapatabilirsiniz.");

        alertDialog.setPositiveButton("Aç", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
}
