package br.nom.strey.maicon.sorting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class Sorters extends Activity {

	Context ctx;

	static final String SORTER_TYPE = "sorter_type";
	static final String SORTER_AUTOMATIC = "sorter_automatic";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sorters);

		ctx = getBaseContext();

		TextView txt_bubble = (TextView) findViewById(R.id.txt_bubble);
		TextView txt_selection = (TextView) findViewById(R.id.txt_selection);
		TextView txt_insertion = (TextView) findViewById(R.id.txt_insertion);
		TextView txt_merge = (TextView) findViewById(R.id.txt_merge);

		final Switch sw_automatic = (Switch) findViewById(R.id.sw_automatic);

		txt_bubble.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 0);
				extras.putBoolean(SORTER_AUTOMATIC, sw_automatic.isChecked());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});

		txt_selection.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 1);
				extras.putBoolean(SORTER_AUTOMATIC, sw_automatic.isChecked());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});

		txt_insertion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 2);
				extras.putBoolean(SORTER_AUTOMATIC, sw_automatic.isChecked());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});

		txt_merge.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sw_automatic.setChecked(true);
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 3);
				extras.putBoolean(SORTER_AUTOMATIC, sw_automatic.isChecked());
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
		
		ImageView banner = (ImageView) findViewById(R.id.banner);
		
		banner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=br.nom.strey.maicon.seashell"));
				startActivity(intent);
			}
		});
		
        if (isConnected(getBaseContext())){ // se estiver conectado exibe anuncio do AdMob
            AdView adView = (AdView)findViewById(R.id.adView);
            // Carrega um anúncio genérico para testes que não gera monetização.
            adView.loadAd(new AdRequest().addTestDevice("A783C1868D1441A3CC47E7681566D639"));
            
            // carrega anúncio válido que gera monetização
            //adView.loadAd(new AdRequest());
        } 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sorters, menu);
		return true;
	}

	public static boolean isConnected(Context context) {

		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			final boolean isWifiConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_WIFI).isConnected();
			final boolean isMobileConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_MOBILE).isConnected();

			if (isWifiConnected) {

				return true;

			} else if (isMobileConnected) {

				return true;

			} else {

				Log.e("isConnected", "Status de conexão Wifi: "
						+ isWifiConnected);
				Log.e("isConnected", "Status de conexão 3G: "
						+ isMobileConnected);
				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
