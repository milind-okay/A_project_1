package com.wordpress.milindkrohit.tttypingtest;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.wordpress.milindkrohit.tttypingtest.utils.Const;

public class scoreboard extends AppCompatActivity {
    private AdView mAdView;
    private TextView points,maxscore,maxscorelevel,scorelevel1,scorelevel2,scorelevel3,scorelevel4
            ,scorelevel5,scorelevel6,scorelevel7;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        sharedPreferences = getSharedPreferences(Const.mypreference, MODE_PRIVATE);
        //editor = sharedPreferences.edit();
        init_val();
        set_score();

    }
    private void init_val(){
        mAdView = (AdView) findViewById(R.id.adView_scoreboard);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        points = (TextView)findViewById(R.id.mpoints);
        maxscore = (TextView)findViewById(R.id.mmaxchar);

        maxscorelevel = (TextView)findViewById(R.id.mmaxscorelevel);
        scorelevel1 = (TextView)findViewById(R.id.level1_score);
        scorelevel2 = (TextView)findViewById(R.id.level2_score);
        scorelevel3 = (TextView)findViewById(R.id.level3_score);
        scorelevel4 = (TextView)findViewById(R.id.level4_score);
        scorelevel5 = (TextView)findViewById(R.id.level5_score);
        scorelevel6 = (TextView)findViewById(R.id.level6_score);
        scorelevel7 = (TextView)findViewById(R.id.level7_score);
    }
    private void set_score(){

        points.setText(Integer.toString(sharedPreferences.getInt(Const.RANK, 0)));
        maxscore.setText(sharedPreferences.getInt(Const.MAX_SCORE, 0));
        scorelevel1.setText(sharedPreferences.getInt(Const.LEVEL_1_RANK, 0));
        scorelevel2.setText(sharedPreferences.getInt(Const.LEVEL_2_RANK, 0));
        scorelevel3.setText(sharedPreferences.getInt(Const.LEVEL_3_RANK, 0));
        scorelevel4.setText(sharedPreferences.getInt(Const.LEVEL_4_RANK, 0));
        scorelevel5.setText(sharedPreferences.getInt(Const.LEVEL_5_RANK, 0));
        scorelevel6.setText(sharedPreferences.getInt(Const.LEVEL_6_RANK, 0));
        scorelevel7.setText(sharedPreferences.getInt(Const.LEVEL_7_RANK, 0));

    }
}
