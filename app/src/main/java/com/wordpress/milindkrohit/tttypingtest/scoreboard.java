package com.wordpress.milindkrohit.tttypingtest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class scoreboard extends AppCompatActivity {
    private AdView mAdView;
    DBHelper mydb;
    private TextView points,maxscore,maxscorelevel,scorelevel1,scorelevel2,scorelevel3,scorelevel4
            ,scorelevel5,scorelevel6,scorelevel7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        init_val();
        mydb = new DBHelper(this);
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
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        points.setText(Integer.toString(rs.getInt(rs.getColumnIndex(DBHelper.MPOINTS))));
        maxscore.setText(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_CHARCOUNT)));
        maxscorelevel.setText(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_LEVEL)));
        scorelevel1.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL1)));
        scorelevel2.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL2)));
        scorelevel3.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL3)));
        scorelevel4.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL4)));
        scorelevel5.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL5)));
        scorelevel6.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL6)));
        scorelevel7.setText(rs.getString(rs.getColumnIndex(DBHelper.CHAR_LEVEL7)));

    }
}
