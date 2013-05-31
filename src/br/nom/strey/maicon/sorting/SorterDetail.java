package br.nom.strey.maicon.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SorterDetail extends Activity {

	static final String SORTER_TAG = "SORTER_DETAIL";

	static final int COLOR_IDX_NORMAL = Color.rgb(0, 0, 0); // Black - 000000
	// static final int COLOR_IDX_I = Color.rgb(0, 0, 205); // Medium Blue -
	// 0000cd
	static final int COLOR_IDX_I = Color.rgb(255, 69, 0); // Orange Red - ff4500
	static final int COLOR_IDX_J = Color.rgb(34, 139, 34); // Forest Green -
															// 228b22
	static final int COLOR_IDX_K = Color.rgb(255, 215, 0); // Gold - ffd700

	static final float SIZE_IDX_ACTIVED = (float) 1.2;
	static final float SIZE_IDX_NORMAL = (float) 0.8;

	static Integer[] primaryArray = { Integer.valueOf(1), Integer.valueOf(2),
			Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5),
			Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8),
			Integer.valueOf(9), Integer.valueOf(10) };

	static Context ctx;

	static TextView txt_idx_i;
	static TextView txt_idx_j;
	static TextView txt_idx_k;

	static TextView txt_title;

	static TextView txt_1;
	static TextView txt_2;
	static TextView txt_3;
	static TextView txt_4;
	static TextView txt_5;
	static TextView txt_6;
	static TextView txt_7;
	static TextView txt_8;
	static TextView txt_9;
	static TextView txt_10;
	static Button bt_start;

	static float normalTxtSize;
	static Boolean isSorting = false;

	static Integer sorter_type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sorter_detail);

		Bundle extras = getIntent().getExtras();
		sorter_type = extras.getInt(Sorters.SORTER_TYPE);

		ctx = getApplicationContext();

		// final Switch sw_automatic = (Switch) findViewById(R.id.sw_automatic);

		txt_title = (TextView) findViewById(R.id.txt_title);

		txt_1 = (TextView) findViewById(R.id.txt_sort_1);
		txt_2 = (TextView) findViewById(R.id.txt_sort_2);
		txt_3 = (TextView) findViewById(R.id.txt_sort_3);
		txt_4 = (TextView) findViewById(R.id.txt_sort_4);
		txt_5 = (TextView) findViewById(R.id.txt_sort_5);
		txt_6 = (TextView) findViewById(R.id.txt_sort_6);
		txt_7 = (TextView) findViewById(R.id.txt_sort_7);
		txt_8 = (TextView) findViewById(R.id.txt_sort_8);
		txt_9 = (TextView) findViewById(R.id.txt_sort_9);
		txt_10 = (TextView) findViewById(R.id.txt_sort_10);

		txt_idx_i = (TextView) findViewById(R.id.txt_idx1);
		txt_idx_j = (TextView) findViewById(R.id.txt_idx2);
		txt_idx_k = (TextView) findViewById(R.id.txt_idx3);

		txt_idx_i.setTextColor(COLOR_IDX_I);
		txt_idx_j.setBackgroundColor(COLOR_IDX_J);

		normalTxtSize = txt_1.getTextSize() * SIZE_IDX_NORMAL;

		bt_start = (Button) findViewById(R.id.bt_start);

		switch (sorter_type) {
		case 0: // bubble
			txt_title.setText(getString(R.string.bubble_name));
			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					bt_start.setText(ctx.getString(R.string.stop));
					// TODO Auto-generated method stub
					List<Integer> arraySorted = Arrays.asList(primaryArray);
					Collections.shuffle(arraySorted);

					Integer[] arrayToSort = (Integer[]) arraySorted.toArray();

					int aux = 0;
					Log.d("BUBBLE SORT", arrayToSort.toString());

					for (int i = 0; i < arrayToSort.length; i++) {
						txt_idx_i.setText("i = " + i);
						for (int j = i; j < arrayToSort.length; j++) {
							txt_idx_j.setText("j = " + j);
							paintIndex(i, j, 0);
							Log.d(SORTER_TAG, "i = " + i + " / j = " + j);
							if (arrayToSort[i] > arrayToSort[j]) {
								aux = arrayToSort[i];
								arrayToSort[i] = arrayToSort[j];
								arrayToSort[j] = aux;
								SystemClock.sleep(1000);
							}
						}
					}
					bt_start.setText(ctx.getString(R.string.start));
				}
			});

			break;

		case 1: // selection
			txt_title.setText(getString(R.string.selection_name));
			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt_start.setText(ctx.getString(R.string.stop));
					List<Integer> arraySorted = Arrays.asList(primaryArray);
					Collections.shuffle(arraySorted);

					Integer[] arrayToSort = (Integer[]) arraySorted.toArray();
					int aux, menor;

					for (int i = 0; i < arrayToSort.length - 1; i++) {

						menor = i;
						for (int j = i + 1; j < arrayToSort.length; j++) {
							Log.d(SORTER_TAG, "i = " + i + " / j = " + j);
							if (arrayToSort[menor] > arrayToSort[j]) {
								menor = j;
							}
						}
						if (menor != i) {
							aux = arrayToSort[menor];
							arrayToSort[menor] = arrayToSort[i];
							arrayToSort[i] = aux;
						}
					}
					bt_start.setText(ctx.getString(R.string.start));

				}
			});

			break;

		case 2: // insertion
			txt_title.setText(getString(R.string.insertion_name));
			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt_start.setText(ctx.getString(R.string.stop));
					List<Integer> arraySorted = Arrays.asList(primaryArray);
					Collections.shuffle(arraySorted);

					int key;
					Integer[] arrayToSort = (Integer[]) arraySorted.toArray();
					for (int j=1, i=0; j < arrayToSort.length; j++) {
						key = arrayToSort[j];
						i = j-1;
						while ((i>=0) && (arrayToSort[i] > key)) {
							Log.d(SORTER_TAG, "i = " + i + " / j = " + j);
							arrayToSort[i+1] = arrayToSort[i];
							i = i-1;
						}
						arrayToSort[i+1] = key;
					}
					bt_start.setText(ctx.getString(R.string.start));
				}
			});
			break;

		case 3: // merge
			txt_title.setText(getString(R.string.merge_name));
			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt_start.setText(ctx.getString(R.string.stop));
					List<Integer> arraySorted = Arrays.asList(primaryArray);
					Collections.shuffle(arraySorted);

					Integer[] arrayToSort = (Integer[]) arraySorted.toArray();

					bt_start.setText(ctx.getString(R.string.start));
				}
			});
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private static void paintIndex(int i, int j, int k) {
		switch (i) {
		case 0:
			txt_1.setTextColor(COLOR_IDX_I);
			txt_1.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 1:
			txt_1.setTextColor(COLOR_IDX_NORMAL);
			txt_1.setTextSize(normalTxtSize);
			txt_2.setTextColor(COLOR_IDX_I);
			txt_2.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 2:
			txt_2.setTextColor(COLOR_IDX_NORMAL);
			txt_2.setTextSize(normalTxtSize);
			txt_3.setTextColor(COLOR_IDX_I);
			txt_3.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		default:
			break;
		}
	}

	private static void bubble_sort() {
		List<Integer> arraySorted = Arrays.asList(primaryArray);
		Collections.shuffle(arraySorted);

		Integer[] arrayToSort = (Integer[]) arraySorted.toArray();

		bt_start.setText(ctx.getString(R.string.stop));

		int aux = 0;
		Log.d("BUBBLE SORT", arrayToSort.toString());

		for (int i = 0; i < arrayToSort.length; i++) {
			txt_idx_i.setText("i = " + i);
			for (int j = i; j < arrayToSort.length; j++) {
				txt_idx_j.setText("j = " + j);
				paintIndex(i, j, 0);
				Log.d(SORTER_TAG, "i = " + i + " / j = " + j);
				if (arrayToSort[i] > arrayToSort[j]) {
					aux = arrayToSort[i];
					arrayToSort[i] = arrayToSort[j];
					arrayToSort[j] = aux;
					SystemClock.sleep(1000);
				}
			}
		}

	}

}
