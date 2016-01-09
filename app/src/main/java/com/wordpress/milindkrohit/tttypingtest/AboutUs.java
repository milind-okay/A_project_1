package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AboutUs extends AppCompatActivity {
    private AdView mAdView;
    ImageButton rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mAdView = (AdView) findViewById(R.id.adView);
        //uncomment it when publishing
        //AdRequest adRequest = new AdRequest.Builder().build();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        rate = (ImageButton) findViewById(R.id.rateus);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "https://play.google.com/store/apps/details?id=ws.crandell.newspaperpuzzles";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));

            }
        });
    }
}
