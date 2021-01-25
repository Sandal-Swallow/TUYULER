package com.startapp.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class SettingsActivity extends Activity {
	private SharedPreferences sp;
	private EditText idAds;
	private EditText timer;
	private Button btn1;
	TextView html;
	TextView a1;
	TextView a2;
	String dev = "200502811";
	String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		idAds = (EditText)findViewById(R.id.edit1);
		timer = (EditText)findViewById(R.id.edit2);
		btn1 = (Button)findViewById(R.id.btn1);
		html = (TextView)findViewById(R.id.dataTextView1);
		a1 = (TextView)findViewById(R.id.dataTextView2);
		a2 = (TextView)findViewById(R.id.dataTextView3);
		html.setText(Html.fromHtml(String.format(getString(R.string.info))));
		a1.setText(String.format(getString(R.string.info1)));
		a2.setText(String.format(getString(R.string.myblog)));
		timer.setText(String.format(getString(R.string.timer)));
		_Linkify(a2, "#2196F3");
		//SP = data
		sp = getSharedPreferences("data", Activity.MODE_PRIVATE);
		Data();
		//ucapan();

    }

	public void Data() {
		if (!sp.getString("id", "").equals("")) {
			idAds.setText(sp.getString("id", ""));
			timer.setText(sp.getString("time", ""));
		}


	}
	public void Simpan(View view) {
		if (idAds.getText().toString().equals("")) {
			showMessage("ID Iklan tidak boleh kosong!");
		} else {
			//cek jika panjang ID iklan kurang 7
			if (idAds.getText().toString().length() < 7) {
				showMessage("ID Iklan Failed!");
			} else {
				sp.edit().putString("id", idAds.getText().toString()).commit();
				if (!timer.getText().toString().equals("")) {
					//cek apakah timer mengandung 000 
					if (!String.valueOf((long)(Double.parseDouble(timer.getText().toString()
																  ))).contains("000")) {
						if (Double.parseDouble(timer.getText().toString()) < 10) {
							showMessage("Timer minimal 10 detik!");
						} else {
							if (Double.parseDouble(timer.getText().toString()) > 60) {
								showMessage("Timer maksimal 60 detik! ");
							} else {
								sp.edit().putString("time", timer.getText().toString().concat("000")).commit();
								//showMessage(sp.getString("time", ""));
								AlertDialog.Builder builder = new AlertDialog.Builder(this);
								builder.setTitle("Success!")
									.setMessage("Data berhasil di simpan\nID Iklan : ".concat(idAds.getText().toString()).
												concat("\nTimer    : ").concat(timer.getText().toString()).concat(" detik\nSilakan Klik \"START\" untuk memulai nuyulnya!"))
									.setCancelable(false)
									.setNegativeButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											//clicked
										}
									});
								AlertDialog alert = builder.create();
								alert.show();
								}
						}
						
					} else {
						if (Double.parseDouble(timer.getText().toString()) < 10000) {
							showMessage("Timer minimal 10 detik!");
						} else {
							if (Double.parseDouble(timer.getText().toString()) > 60000) {
								showMessage("Timer maksimal 60 detik! ");
							} else {
								sp.edit().putString("time", timer.getText().toString()).commit();
								//showMessage(sp.getString("time", ""));
								str = timer.getText().toString().replace("000", "");
								AlertDialog.Builder builder = new AlertDialog.Builder(this);
								builder.setTitle("Success!")
									.setMessage("Data berhasil di simpan\nID Iklan : ".concat(idAds.getText().toString()).
									concat("\nTimer    : ").concat(str).concat(" detik\nSilakan Klik \"START\" untuk memulai nuyulnya!"))
									.setCancelable(false)
									.setNegativeButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											//clicked
										}
									});
								AlertDialog alert = builder.create();
								alert.show();
							}
						}

					}
					
						
					
				}
				else{
					showMessage("Timer tidak boleh kosong!");
				}
			}
		}

	}

	

	public void Start(View view) {
		if(!(sp.getString("id","").equals("") || sp.getString("time", "").equals(""))){
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finishAffinity();
		}
		else{
			showMessage("Klik \"SIMPAN\" kemudian Klik \"START\"");
		}

		//custom alert
	}

	public void _Linkify(final TextView _text, final String _color) {
		_text.setClickable(true);
		android.text.util.Linkify.addLinks(_text, android.text.util.Linkify.ALL);
		_text.setLinkTextColor(Color.parseColor("#" + _color.replace("#", "")));
		_text.setLinksClickable(true);
	}
	
	private void showMessage(String _str) {
		Toast ToastTop=Toast.makeText(getApplicationContext(), _str, Toast.LENGTH_SHORT);
		ToastTop.setGravity(Gravity.CENTER, 0, 0);
		ToastTop.show();
	}

}

