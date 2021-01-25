package com.startapp.id;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.Cover;
import com.startapp.sdk.ads.banner.Mrec;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.VideoListener;
import android.content.SharedPreferences;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;


public class MainActivity extends Activity { 

	Banner banner1;
	Banner banner2;
	Banner banner3;
	Banner banner4;
	Banner banner5;
	Banner banner6;
	Banner banner7;
	Banner banner8;
	Banner banner9;
	Banner banner10;
	Banner banner11;
	Banner banner12;
	Mrec mrec1;
	Mrec mrec2;
	Mrec mrec3;
	Mrec mrec4;
	Mrec mrec5;
	Mrec mrec6;
	Mrec mrec7;
	Mrec mrec8;
	Mrec mrec9;
	Mrec mrec10;
	Mrec mrec11;
	Cover cover1;
	Cover cover2; 

	private StartAppAd startAppAd = new StartAppAd(this);
	private TextView waktu;
	private int tDetik;
	private SharedPreferences sp;
	private int mDetik;
	private double mDetiks;
	Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		//StartApp ads banner
		banner1 = (Banner)findViewById(R.id.startAppBanner1);
		banner1.showBanner();
		banner2 = (Banner)findViewById(R.id.startAppBanner2);
		banner2.showBanner();
		banner3 = (Banner)findViewById(R.id.startAppBanner3);
		banner3.showBanner();
		banner4 = (Banner)findViewById(R.id.startAppBanner4);
		banner4.showBanner();
		banner5 = (Banner)findViewById(R.id.startAppBanner5);
		banner5.showBanner();
		banner6 = (Banner)findViewById(R.id.startAppBanner6);
		banner6.showBanner();
		banner7 = (Banner)findViewById(R.id.startAppBanner7);
		banner7.showBanner();
		banner8 = (Banner)findViewById(R.id.startAppBanner8);
		banner8.showBanner();
		banner9 = (Banner)findViewById(R.id.startAppBanner9);
		banner9.showBanner();
		banner10 = (Banner)findViewById(R.id.startAppBanner10);
		banner10.showBanner();
		banner11 = (Banner)findViewById(R.id.startAppBanner11);
		banner11.showBanner();
		banner12 = (Banner)findViewById(R.id.startAppBanner11);
		banner12.showBanner();

		//StartApp ads Mrec
		mrec1 = (Mrec)findViewById(R.id.startAppMrec1);
		mrec1.loadAd();
		mrec2 = (Mrec)findViewById(R.id.startAppMrec2);
		mrec2.loadAd();
		mrec3 = (Mrec)findViewById(R.id.startAppMrec3);
		mrec3.loadAd();
		mrec4 = (Mrec)findViewById(R.id.startAppMrec4);
		mrec4.loadAd();
		mrec5 = (Mrec)findViewById(R.id.startAppMrec5);
		mrec5.loadAd();
		mrec6 = (Mrec)findViewById(R.id.startAppMrec6);
		mrec6.loadAd();
		mrec7 = (Mrec)findViewById(R.id.startAppMrec7);
		mrec7.loadAd();
		mrec8 = (Mrec)findViewById(R.id.startAppMrec8);
		mrec8.loadAd();
		mrec9 = (Mrec)findViewById(R.id.startAppMrec9);
		mrec9.loadAd();
		mrec10 = (Mrec)findViewById(R.id.startAppMrec10);
		mrec10.loadAd();
		mrec11 = (Mrec)findViewById(R.id.startAppMrec11);
		mrec11.loadAd();

		//StartApp ads Cover
		cover1 = (Cover)findViewById(R.id.startAppCover1);
		cover1.loadAd();
		cover2 = (Cover)findViewById(R.id.startAppCover2);
		cover2.loadAd();


		StartAppSDK.setUserConsent(this,  
								   "pas",
								   System.currentTimeMillis(),                        
								   true);
		StartAppAd.disableSplash();

		sp = getSharedPreferences("data", Activity.MODE_PRIVATE);
		mDetiks = Double.parseDouble(sp.getString("time", ""));
		mDetiks = mDetiks + 1000;
		mDetik = (int) mDetiks;
		
		



		//timer
		waktu = (TextView)findViewById(R.id.timertext);
		new CountDownTimer(mDetik, 1000){
			public void onTick(long detik) {

				tDetik = (int) detik / 1000;

				if (tDetik < 10) {
					waktu.setText("00.0" + detik / 1000);
				} else {
					waktu.setText("00." + detik / 1000);
				}
			}
			public void onFinish() {

				waktu.setText("00.00");
				Toast.makeText(MainActivity.this, "Refresh Ads", Toast.LENGTH_LONG).show();
				new Handler().postDelayed(new Runnable(){
						public void run() {
							startActivity(new Intent(MainActivity.this, MainActivity.class));
							finish();
						}
					}, 1000);
			}
		}.start();


    }

	//OnBackpressed
	@Override
	public void onBackPressed() {
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		finishAffinity();
	}

	public void showIner(View view) {
		final StartAppAd triosihn = new StartAppAd(this);
		triosihn.showAd(new AdDisplayListener(){

				@Override
				public void adHidden(Ad p1) {
				}

				@Override
				public void adDisplayed(Ad p1) {
				}

				@Override
				public void adClicked(Ad p1) {
				}

				@Override
				public void adNotDisplayed(Ad p1) {
					Toast.makeText(MainActivity.this, "Iner Cannot loaded!", Toast.LENGTH_LONG).show();
				}


			});
	}

	public void showRewards(View view) {
		startAppAd.showAd(this);
		startAppAd.setVideoListener(new VideoListener() {
				@Override
				public void onVideoCompleted() {
					// Grant user with the reward
				}
			});
	}
	public void settings(View view){
		Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finishAffinity();
	}
}

