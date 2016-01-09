package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class Start_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner level_spinner,time_spinner;
    private TextView log,user,addnew,mpoints;
    private AdView mAdView;
    int level_no,time_t,num_row;

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb = new DBHelper(this);
        /*mAdView = (AdView) findViewById(R.id.adViewstart);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);*/
        num_row = mydb.numberOfRows();
       // initvalue();
        level_spinner = (Spinner) findViewById(R.id.level);
        time_spinner = (Spinner) findViewById(R.id.time);
        log = (TextView)findViewById(R.id.log);
        user = (TextView)findViewById(R.id.username);
        addnew = (TextView)findViewById(R.id.addnew);
        mpoints = (TextView)findViewById(R.id.points);
        level_no = 1;
        time_t = 60;

        level_spinner.setOnItemSelectedListener(this);
        time_spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence>  levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.level_str, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>  timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_spinner.setAdapter(levelAdapter);
        time_spinner.setAdapter(timeAdapter);
        set_log();
        if(num_row > 0) {
            setPoints();
        }

        /*ArrayList array_list = mydb.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), Reg_user.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(log.getText().toString().equals(R.string.welcome)) {
                    mctivity();
                }else{
                    Toast.makeText(Start_Activity.this, "Sign Up/Sign In first to Start", Toast.LENGTH_SHORT).show();
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_fun();

            }
        });

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_user();

            }
        });




    }
    public void initvalue(){

        level_spinner = (Spinner) findViewById(R.id.level);
        time_spinner = (Spinner) findViewById(R.id.time);
        log = (TextView)findViewById(R.id.log);
        user = (TextView)findViewById(R.id.username);
        addnew = (TextView)findViewById(R.id.addnew);
        mpoints = (TextView)findViewById(R.id.points);
        level_no = 1;
        time_t = 60;

        level_spinner.setOnItemSelectedListener(this);
        time_spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence>  levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.level_str, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>  timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);

        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_spinner.setAdapter(levelAdapter);
        time_spinner.setAdapter(timeAdapter);
        set_log();
        if(num_row > 0) {
            setPoints();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mtt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.rate_us:
                rate_us();
                return true;
            case R.id.help:
                showHelp();
                return true;
            case R.id.action_contact:
                sendEmail();
                return true;
            case R.id.action_about:
                Intent about = new Intent(this, AboutUs.class);
                startActivity(about);
                return true;

            case R.id.log:
                logout();
                return true;
            case R.id.scoreboard:
                Intent scoreboard = new Intent(this, scoreboard.class);
                startActivity(scoreboard);
                return true;
            case R.id.account:
                if(log.getText().toString().equals(R.string.welcome)) {
                    account();
                }else{
                    Toast.makeText(Start_Activity.this, "Sign Up/Sign In first to Start", Toast.LENGTH_SHORT).show();
                }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void mctivity(){
        Intent intent = new Intent(this, MTTActivity.class);
        intent.putExtra("level", level_no);
        intent.putExtra("time", time_t);
        startActivity(intent);
    }

    private void sendEmail(){

        String info = "okay ",emailAdd;
        emailAdd = "milind0359@gmail.com";
        Log.i("Send email", "");
        String emailaddress[] = {emailAdd};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plane/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SudokuSolverAdvanced : state your subject here");

        emailIntent.putExtra(Intent.EXTRA_TEXT, info);


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Start_Activity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void rate_us() {
        String str ="https://play.google.com/store/apps/details?id=ws.crandell.newspaperpuzzles";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }
    private void showHelp() {
        String str ="https://www.milindkrohit.wordpress.com";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }
    private void reg_user(){
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", 0);
        Intent intent = new Intent(getApplicationContext(),log.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
    private void login_user(){
        Intent login = new Intent(this, mLoginActivity.class);
        startActivity(login);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.level)
        {
            level_no = position;
        }
        else if(spinner.getId() == R.id.time)
        {
            time_t = (position + 1)*30;
        }


    }
    public void account(){
        Intent login = new Intent(this, Account.class);
        startActivity(login);
    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void logout(){
        mydb.logout(1,"0");
        log.setText("Sign In");
        user.setText("");
        Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
        startActivity(intent);

    }
    private void set_log(){
        if(num_row > 0){
            Cursor rs = mydb.getData(1);
            rs.moveToFirst();
            if(rs.getInt(rs.getColumnIndex(DBHelper.ISSIGNIN)) == 1){
                log.setClickable(false);
                log.setText(R.string.welcome);
                log.setTextColor(Color.WHITE);
                user.setText(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_NAME)));
                //addnew.setText("Add New");
            }else {
                log.setText(R.string.signin);
                addnew.setText(R.string.action_log);
            }
            if (!rs.isClosed())
            {
                rs.close();
            }
        }else {
            log.setText(R.string.signup);
               }
    }
    public void log_fun(){
        if(num_row > 0){
            Cursor rs = mydb.getData(1);
            rs.moveToFirst();
            if(!(rs.getInt(rs.getColumnIndex(DBHelper.ISSIGNIN)) == 1)){
                login_user();
            }
            if (!rs.isClosed())
            {
                rs.close();
            }
        }else {
            reg_user();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
      // setPoints();
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
    private void setPoints(){
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        mpoints.setText(rs.getString(rs.getColumnIndex(DBHelper.MPOINTS)));
        if (!rs.isClosed())
        {
            rs.close();
        }
    }


}
