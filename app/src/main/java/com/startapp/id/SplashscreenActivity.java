package com.startapp.id;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.startapp.sdk.adsbase.StartAppAd;
import android.widget.ProgressBar;

public class SplashscreenActivity extends Activity { 

	TextView splashTextView1;
	TextView link;
	ProgressBar prog;
	int progress = 0;
	Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
		splashTextView1 = (TextView)findViewById(R.id.splashTextView1);
		prog = (ProgressBar)findViewById(R.id.splashscreenProgressBar1);
		link = (TextView)findViewById(R.id.splashscreenTextView2);
		splashTextView1.setText(String.format(getString(R.string.app_name)));
		link.setText(String.format(getString(R.string.myblog)));
		_Linkify(link, "#2196F3");
		progressbar();
		StartAppAd.disableSplash ();



    }

	public void _Linkify (final TextView _text, final String _color) {
		_text.setClickable(true);
		android.text.util.Linkify.addLinks(_text, android.text.util.Linkify.ALL);
		_text.setLinkTextColor(Color.parseColor("#" + _color.replace("#", "")));
		_text.setLinksClickable(true);
	}
	private void progressbar() {
		new Thread(new Runnable(){

				@Override
				public void run() {
					while (progress < 100) {
						progress += 10;

						handler.post(new Runnable(){

								@Override
								public void run() {
									prog.setProgress(progress);
									if (progress == 100) {
										prog.setProgress(progress);
										//Toast.makeText(getApplicationContext(), "Progress Finish!", Toast.LENGTH_SHORT).show();
										startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
										finishAffinity();
									}
								}
							});
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}


			}).start();
	}
}

