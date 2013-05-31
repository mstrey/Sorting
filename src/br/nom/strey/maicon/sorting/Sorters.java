package br.nom.strey.maicon.sorting;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Sorters extends Activity {

	static final String SORTER_TYPE = "sorter_type";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sorters);

		TextView txt_bubble = (TextView) findViewById(R.id.txt_bubble);
		TextView txt_selection = (TextView) findViewById(R.id.txt_selection);
		TextView txt_insertion = (TextView) findViewById(R.id.txt_insertion);
		TextView txt_merge = (TextView) findViewById(R.id.txt_merge);

		txt_bubble.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 0);
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
				intent.putExtras(extras);
				startActivity(intent);
			}
		});

		txt_merge.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SorterDetail.class);
				Bundle extras = new Bundle();
				extras.putInt(SORTER_TYPE, 3);
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sorters, menu);
		return true;
	}

}
