package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MTTActivity extends AppCompatActivity {
    private TextView firstline,secondline,tcount,mcount,wcount,acct,mclock,charcount,mspeed,maxchar,char_level,thislevel;
    private EditText input;
    private AdView mAdView;

    int Mcount,Tcount,Wcount,Ccount,level,time,points,charlevel;
    double t_value;
    DBHelper mydb;
    Cursor rs;
    String strArr[][] = {
            {"you are in first level ",
                    "type simple words and",
                    "get happy bhai log",
                    "it is soo simple to type ",
                    "okay then what the ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it "},
            {"Hello I am Milind Kumar Rohit ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it ",
                    "Hello I am Milind Kumar Rohit ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it "},
            {"level 3are in first level ",
                    "type simple words and",
                    "get happy  bhai log",
                    "it is soo simple to type ",
                    "okay then what the ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it "},
            {"level 4 I am Milind Kumar Rohit ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it ",
                    "Hello I am Milind Kumar Rohit ",
                    "And what's going here ",
                    "I dont knnow actually ",
                    "Okay the who the hell know it "}
    };
    String str[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initValue();
        final MyCount counter = new MyCount(time*1000,1000);
        final int strtype[] = new int[10];
        for(int it=0;it < 10;it++){
            strtype[it] = Color.BLACK;
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.restart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });
        FloatingActionButton home = (FloatingActionButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeActivity();
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tcount == 0)
                    counter.start();

            }
        });
        input.addTextChangedListener(new TextWatcher() {

            String mk, std,prv;

            //String str1[] = new String[10];


            int i = 0, j = 0, k = 0, len;
            String str1[] = str[k].split(" ");

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                len = str1.length;
                prv = mk;
                mk = "";
                mk += s;
                std = str1[i];

                if (mk.length() > 0 && mk.charAt(mk.length() - 1) != ' ') {
                    for (j = 0; j < mk.length(); j++) {
                        if (mk.length() <= std.length() && mk.charAt(j) != std.charAt(j))
                            break;
                    }
                    if (j == mk.length() && j <= std.length()) {
                        strtype[i] = Color.GREEN;

                    } else {
                        strtype[i] = Color.RED;

                    }
                } else if (mk.length() > 0 && mk.charAt(0) != ' ') {
                    Tcount++;

                    if (strtype[i] == Color.GREEN && mk.length() == std.length() + 1) {
                        Mcount++;
                        Ccount+=std.length();
                    } else {
                        strtype[i] = Color.RED;
                        Wcount++;
                    }
                    input.setText("");
                    i++;
                    j = 0;
                    if (i == len) {
                        k++;
                        str1 = str[k].split(" ");
                        len = str1.length;
                        firstline.setText(str[k]);
                        secondline.setText(str[k + 1]);
                        for (i = 0; i < 10; i++) {
                            strtype[i] = Color.BLACK;
                        }
                        i = 0;
                    }

                    tcount.setText(Integer.toString(Tcount));

                    mcount.setText(Integer.toString(Mcount));

                    wcount.setText(Integer.toString(Wcount));



                }else if(mk.length() > 0){
                    input.setText("");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }


            @Override
            public void afterTextChanged(Editable s) {

                String outstr = "";
                for (int it = 0; it < len; it++) {
                    outstr += "<font color='" + strtype[it] + "'>" + str1[it] + "</font>" + " ";
                }
                firstline.setText(Html.fromHtml(outstr));


            }
        });
        //input.removeTextChangedListener();
    }
    private void initValue(){
        mAdView = (AdView) findViewById(R.id.adViewmain);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        input = (EditText) findViewById(R.id.edit);
        firstline = (TextView)findViewById(R.id.firstline);
        secondline = (TextView) findViewById(R.id.secondline);
        tcount = (TextView)findViewById(R.id.Totalcount);
        mcount = (TextView)findViewById(R.id.rightcount);
        wcount  = (TextView)findViewById(R.id.wrongcount);
        acct = (TextView) findViewById(R.id.acc);
        mclock = (TextView) findViewById(R.id.mclock);
        maxchar = (TextView) findViewById(R.id.maxchar);
        char_level = (TextView) findViewById(R.id.level);
        charcount = (TextView) findViewById(R.id.charcount);
        mspeed = (TextView) findViewById(R.id.tspeed);
        thislevel = (TextView) findViewById(R.id.thislevelscore);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        level = extras.getInt("level");
        time = extras.getInt("time");
        mydb = new DBHelper(this);
        rs = mydb.getData(1);
        rs.moveToFirst();


        points = rs.getInt(rs.getColumnIndex(DBHelper.MPOINTS));
        setcharlevel(level + 1);
        maxchar.setText(Integer.toString(rs.getInt(rs.getColumnIndex(DBHelper.COLUMN_CHARCOUNT))));
        char_level.setText(Integer.toString(rs.getInt(rs.getColumnIndex(DBHelper.COLUMN_LEVEL))));
        thislevel.setText(Integer.toString(charlevel));

        str = strArr[level];

        firstline.setText(str[0]);
        secondline.setText(str[1]);
        Mcount = Tcount = Wcount=Ccount = 0;
        t_value = (double)time/(double)60;

    }
    public void setcharlevel(int level){
        switch (level){
            case 1:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL1));
                break;
            case 2:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL2));
                break;
            case 3:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL3));
                break;
            case 4:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL4));
                break;
            case 5:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL5));
                break;
            case 6:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL6));
                break;
            case 7:
                charlevel = rs.getInt(rs.getColumnIndex(DBHelper.CHAR_LEVEL7));
                break;
            default:
                break;



        }
    }
    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mclock.setText("0");
            input.setText("Time Over");
            disableEditText(input);
            Ccount =(int)(Ccount/t_value);
            charcount.setText(Integer.toString(Ccount));

            mspeed.setText(Integer.toString((int)(Mcount/t_value)));

            acct.setText(Double.toString((double) (Mcount) / (double) (Tcount)));

            if(Ccount > rs.getInt(rs.getColumnIndex(DBHelper.COLUMN_CHARCOUNT))){
                mydb.updatescore(1, Ccount,level + 1);
                maxchar.setText(Integer.toString(Ccount));
                char_level.setText(Integer.toString(level + 1));
            }
            if(Ccount > charlevel){
                points = points + ((level + 1)*(Ccount - charlevel ));
                mydb.updatelevel(1,points,Ccount,level + 1);
                thislevel.setText(Integer.toString(Ccount));

            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mclock.setText("" + millisUntilFinished/1000);

        }

    }
    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
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
            default:
                return super.onOptionsItemSelected(item);
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
    public void startActivity(){

        Intent intent = new Intent(this, MTTActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("time", time);
        startActivity(intent);
        finish();


    }
    public void homeActivity(){
        Intent intent = new Intent(this, Start_Activity.class);
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
        //startActivity(emailIntent);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MTTActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    private void logout(){
        mydb.logout(1,"0");

    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

