package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.wordpress.milindkrohit.tttypingtest.utils.Const;

public class MTTActivity extends AppCompatActivity {
    private TextView firstline,secondline,tcount,mcount,wcount,acct,mclock,charcount,mspeed,maxchar,max_score,record_score,current_score,rank;
    private EditText input;
    private AdView mAdView;

    int Mcount,Tcount,Wcount,Ccount,level,time,points,Max_socre,Record_score,Rank,Current_score;
    double t_value;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
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
        sharedPreferences = getSharedPreferences(Const.mypreference, MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
                start_activity();
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
            String str1[];

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
                str1 = str[k].split(" ");

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
        max_score = (TextView) findViewById(R.id.max_score);
        record_score = (TextView) findViewById(R.id.record_score);
        rank = (TextView) findViewById(R.id.level_rank);
        charcount = (TextView) findViewById(R.id.charcount);
        mspeed = (TextView) findViewById(R.id.tspeed);
        current_score = (TextView) findViewById(R.id.current_score);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        level = extras.getInt("level");
        time = extras.getInt("time");
        if(sharedPreferences.getInt(Const.IS_LOGIN, 0)==1) {

            points = sharedPreferences.getInt(Const.RANK, 0);

        }
        str = strArr[level];
        firstline.setText(str[0]);
        secondline.setText(str[1]);
        Mcount = Tcount = Wcount=Ccount = 0;
        t_value = (double)time/(double)60;
        setStats(level + 1);


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
            Current_score = Ccount;
            current_score.setText(Integer.toString(Current_score));
            mspeed.setText(Integer.toString((int)(Mcount/t_value)));

            acct.setText(Double.toString((double) (Mcount) / (double) (Tcount)));
            if(sharedPreferences.getInt(Const.IS_LOGIN, 0)==1 ) {
                if (Current_score > Max_socre) {
                    int OverALllMaxScore = sharedPreferences.getInt(Const.MAX_SCORE,0) + (Current_score - Max_socre)*(level + 1);
                    editor.putInt(Const.MAX_SCORE,OverALllMaxScore);
                    updateMaxScore();
                    //TODO get rank from server
                    int L_rank = 0,G_rank = 0;
                    updateLRank(L_rank);
                    editor.putInt(Const.RANK,G_rank);
                    rank.setText(Integer.toString(L_rank));
                    editor.apply();
                }
                if (Current_score > Record_score) {
                    updateRecordScore();
                    //TODO update at server
                }
                /*if (Ccount > charlevel) {
                    points = points + ((level + 1) * (Ccount - charlevel));
                    mydb.updatelevel(1, points, Ccount, level + 1);
                    thislevel.setText(Integer.toString(Ccount));

                }*/
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mclock.setText("" + millisUntilFinished/1000);

        }

    }

    public void setStats(int level){
        switch (level){
            case 1:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_1_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_1_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_1_RSCORE, 0);
                break;
            case 2:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_2_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_2_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_2_RSCORE, 0);
                break;
            case 3:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_3_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_3_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_3_RSCORE, 0);
                break;
            case 4:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_4_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_4_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_4_RSCORE, 0);
                break;
            case 5:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_5_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_5_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_5_RSCORE, 0);
                break;
            case 6:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_6_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_6_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_6_RSCORE, 0);
                break;
            case 7:
                Max_socre = sharedPreferences.getInt(Const.LEVEL_7_SCORE, 0);
                Rank = sharedPreferences.getInt(Const.LEVEL_7_RANK, 0);
                Record_score = sharedPreferences.getInt(Const.LEVEL_7_RSCORE, 0);
                break;
            default:
                break;

        }
        max_score.setText(Integer.toString(Max_socre));
        record_score.setText(Integer.toString(Record_score));
        rank.setText(Integer.toString(Rank));

    }
    public void updateMaxScore(){
        switch (level + 1){
            case 1:
                editor.putInt(Const.LEVEL_1_SCORE, Current_score).apply();

                break;
            case 2:
                editor.putInt(Const.LEVEL_2_SCORE, Current_score).apply();
                break;
            case 3:
                editor.putInt(Const.LEVEL_3_SCORE, Current_score).apply();
                break;
            case 4:
                editor.putInt(Const.LEVEL_4_SCORE, Current_score).apply();
                break;
            case 5:
                editor.putInt(Const.LEVEL_5_SCORE, Current_score).apply();
                break;
            case 6:
                editor.putInt(Const.LEVEL_6_SCORE, Current_score).apply();
                break;
            case 7:
                editor.putInt(Const.LEVEL_7_SCORE, Current_score).apply();
                break;
            default:
                break;

        }
        max_score.setText(Integer.toString(Current_score));

    }
    public void updateLRank(int rank){
        switch (level + 1){
            case 1:
                editor.putInt(Const.LEVEL_1_RANK, rank).apply();

                break;
            case 2:
                editor.putInt(Const.LEVEL_2_RANK, rank).apply();
                break;
            case 3:
                editor.putInt(Const.LEVEL_3_RANK, rank).apply();
                break;
            case 4:
                editor.putInt(Const.LEVEL_4_RANK, rank).apply();
                break;
            case 5:
                editor.putInt(Const.LEVEL_5_RANK, rank).apply();
                break;
            case 6:
                editor.putInt(Const.LEVEL_6_RANK, rank).apply();
                break;
            case 7:
                editor.putInt(Const.LEVEL_7_RANK, rank).apply();
                break;
            default:
                break;

        }
        this.rank.setText(Integer.toString(rank));

    }
    public void updateRecordScore(){
        switch (level + 1){
            case 1:
                editor.putInt(Const.LEVEL_1_RSCORE, Current_score).apply();

                break;
            case 2:
                editor.putInt(Const.LEVEL_2_RSCORE, Current_score).apply();
                break;
            case 3:
                editor.putInt(Const.LEVEL_3_RSCORE, Current_score).apply();
                break;
            case 4:
                editor.putInt(Const.LEVEL_4_RSCORE, Current_score).apply();
                break;
            case 5:
                editor.putInt(Const.LEVEL_5_RSCORE, Current_score).apply();
                break;
            case 6:
                editor.putInt(Const.LEVEL_6_RSCORE, Current_score).apply();
                break;
            case 7:
                editor.putInt(Const.LEVEL_7_RSCORE, Current_score).apply();
                break;
            default:
                break;

        }
        record_score.setText(Integer.toString(Current_score));

    }
    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }


    public void start_activity(){

        Intent intent = new Intent(this, MTTActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("time", time);
        startActivity(intent);
        finish();


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

