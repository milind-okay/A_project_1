package com.wordpress.milindkrohit.tttypingtest;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class mLoginActivity extends Activity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    private Button log;
    private AdView mAdView;
    TextView phone;
    TextView pass;
    String phoneData,passData,email;

    int id_To_Update = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_login);
        mAdView = (AdView) findViewById(R.id.adView);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        pass = (TextView) findViewById(R.id.password);
        phone = (TextView) findViewById(R.id.email);
        log = (Button)findViewById(R.id.email_sign_in_button);
        mydb = new DBHelper(this);
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        phoneData = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_PHONE));
        passData = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_PASS));
        email = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_EMAIL));
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pass.getText().toString().equals(passData) && (phone.getText().toString().equals(phoneData) || phone.getText().toString().equals(email))){
                    mydb.logout(id_To_Update,"1");
                    Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(mLoginActivity.this, "Email Id or Password wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mtt, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);


    }
}