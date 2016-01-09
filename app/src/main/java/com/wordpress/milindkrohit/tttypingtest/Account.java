package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Account extends AppCompatActivity {
    DBHelper mydb;
    private AdView mAdView;
    private  TextView email,phone,changepassword,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mAdView = (AdView) findViewById(R.id.adView);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        changepassword = (TextView)findViewById(R.id.changepass);
        logout = (TextView)findViewById(R.id.logout);
        mydb = new DBHelper(this);
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        email.setText(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_EMAIL)));
        phone.setText(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_PHONE)));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.logout(1, "0");
                Intent intent = new Intent(getApplicationContext(), Start_Activity.class);
                startActivity(intent);
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", -1);
                Intent intent = new Intent(getApplicationContext(),log.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

    }
}
