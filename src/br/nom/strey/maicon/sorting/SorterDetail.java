package br.nom.strey.maicon.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class SorterDetail extends Activity {

	static final String SORTER_TAG = "SORTER_DETAIL";

	// Black - 000000
	static final int COLOR_IDX_NORMAL = Color.rgb(0, 0, 0);
	// white - ffffff
	static final int COLOR_IDX_BACKGROUND_NORMAL = Color.rgb(255, 255, 255);
	// Orange Red - ff4500
	static final int COLOR_IDX_I = Color.rgb(255, 69, 0);
	// Forest Green - 228b22
	static final int COLOR_IDX_BACKGROUND_J = Color.rgb(34, 139, 34);
	// Gold - ffd700
	static final int COLOR_IDX_K = Color.rgb(255, 215, 0);

	static final float SIZE_IDX_ACTIVED = (float) 1.4;
	static final float SIZE_IDX_NORMAL = (float) 0.6;

	static final Integer[] PRIMARY_ARRAY = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	static Integer[] arrayToSort = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	static Context ctx;

	static TextView txt_idx_i;
	static TextView txt_idx_j;
	static TextView txt_idx_k;

	static int idx_1;
	static int idx_2;
	static int idx_3;
	static int aux;
	static int lowest;
	static int key;

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
	static Button bt_next;

	static float normalTxtSize;
	static SpannableString spanString;
	static Boolean isSorting = false;
	static Boolean isWhiling = false;

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

		ImageView banner = (ImageView) findViewById(R.id.banner_detail);

		banner.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id=br.nom.strey.maicon.seashell"));
				startActivity(intent);
			}
		});

		if (isConnected(getBaseContext())) { // se estiver conectado exibe
												// anuncio do AdMob
			AdView adView = (AdView) findViewById(R.id.adView_detail);
			if (Sorters.DEBUG) {
				// Carrega um anúncio genérico para testes que não gera
				// monetização.
				adView.loadAd(new AdRequest()
						.addTestDevice("A783C1868D1441A3CC47E7681566D639"));

			} else {
				// carrega anúncio válido que gera monetização
				adView.loadAd(new AdRequest());

			}
		}

		txt_idx_i.setTextColor(COLOR_IDX_I);
		txt_idx_j.setBackgroundColor(COLOR_IDX_BACKGROUND_J);

		normalTxtSize = txt_1.getTextSize() * SIZE_IDX_NORMAL;

		bt_start = (Button) findViewById(R.id.bt_start);
		bt_next = (Button) findViewById(R.id.bt_next);

		resetIndexes();
		isSorting = false;

		switch (sorter_type) {
		case 0: // bubble
			txt_title.setText(getString(R.string.bubble_name));
			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					if (isSorting) {
						stopSorter();
					} else {
						startSorter();
						updateValuesBars(arrayToSort);

						idx_1 = 0;
						idx_2 = idx_1 + 1;
						aux = 0;

						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);
						paintIndex(idx_1, idx_2, -1, -1);

						if (arrayToSort[idx_1] > arrayToSort[idx_2]) {
							aux = arrayToSort[idx_1];
							arrayToSort[idx_1] = arrayToSort[idx_2];
							arrayToSort[idx_2] = aux;
						}
						updateValuesBars(arrayToSort);
						printArray(arrayToSort);
					}
				}

			});

			bt_next.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (idx_1 == arrayToSort.length - 2) {
						stopSorter();

						Toast.makeText(ctx,
								ctx.getString(R.string.array_sorted),
								Toast.LENGTH_SHORT).show();
					} else {
						if (idx_2 == arrayToSort.length - 1) {
							Log.d(SORTER_TAG, "fim J, reinicia I");
							idx_2 = ++idx_1 + 1;
						} else {
							idx_2++;
						}

						if (arrayToSort[idx_1] > arrayToSort[idx_2]) {
							aux = arrayToSort[idx_1];
							arrayToSort[idx_1] = arrayToSort[idx_2];
							arrayToSort[idx_2] = aux;
						}

						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);

						resetIndexes();
						paintIndex(idx_1, idx_2, -1, -1);
						printArray(arrayToSort);
						updateValuesBars(arrayToSort);
					}
				}
			});

			break;

		case 1: // selection
			txt_title.setText(getString(R.string.selection_name));
			txt_idx_k.setText(ctx.getString(R.string.lowest) + "(<) = 0");
			txt_idx_k.setVisibility(View.VISIBLE);

			bt_start.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (isSorting) {
						stopSorter();
					} else {
						startSorter();
						updateValuesBars(arrayToSort);

						idx_1 = 0;
						idx_2 = idx_1 + 1;
						aux = 0;
						lowest = idx_1;
						spanString = new SpannableString(ctx
								.getString(R.string.lowest) + "(<) = " + lowest);
						// spanString.setSpan(new UnderlineSpan(), 0,
						// spanString.length(), 0);
						txt_idx_k.setText(spanString);

						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);

						Log.d(SORTER_TAG, "i = " + idx_1 + " / j = " + idx_2
								+ " / menor = " + lowest);

						if (arrayToSort[lowest] > arrayToSort[idx_2]) {
							lowest = idx_2;
							spanString = new SpannableString(ctx
									.getString(R.string.lowest)
									+ "(<) = "
									+ lowest);
							// spanString.setSpan(new UnderlineSpan(), 0,
							// spanString.length(), 0);
							txt_idx_k.setText(spanString);
						}

						if (lowest != idx_1) {
							aux = arrayToSort[lowest];
							arrayToSort[lowest] = arrayToSort[idx_1];
							arrayToSort[idx_1] = aux;
							lowest = idx_1;
						}
						updateValuesBars(arrayToSort);
						paintIndex(idx_1, idx_2, -1, lowest);
						printArray(arrayToSort);
					}
				}
			});

			bt_next.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (idx_1 == arrayToSort.length - 2) {
						stopSorter();

						Toast.makeText(ctx,
								ctx.getString(R.string.array_sorted),
								Toast.LENGTH_SHORT).show();
					} else {
						if (idx_2 == arrayToSort.length - 1) {
							Log.d(SORTER_TAG, "fim J, reinicia I");
							idx_2 = ++idx_1 + 1;
							lowest = idx_1;
							spanString = new SpannableString(ctx
									.getString(R.string.lowest)
									+ "(<) = "
									+ lowest);
							// spanString.setSpan(new UnderlineSpan(),
							// 0,spanString.length(), 0);
							txt_idx_k.setText(spanString);
						} else {
							idx_2++;
						}

						if (arrayToSort[lowest] > arrayToSort[idx_2]) {
							lowest = idx_2;
							spanString = new SpannableString(ctx
									.getString(R.string.lowest)
									+ "(<) = "
									+ lowest);
							// spanString.setSpan(new UnderlineSpan(),
							// 0,spanString.length(), 0);
							txt_idx_k.setText(spanString);
						}

						if (idx_2 == arrayToSort.length - 1) {
							Log.d(SORTER_TAG, "fim J, verifica menor");
							if (lowest != idx_1) {
								Log.d(SORTER_TAG, "troca menor");
								aux = arrayToSort[lowest];
								arrayToSort[lowest] = arrayToSort[idx_1];
								arrayToSort[idx_1] = aux;
								lowest = idx_1;
							}
						}

						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);

						resetIndexes();
						printArray(arrayToSort);
						updateValuesBars(arrayToSort);
						paintIndex(idx_1, idx_2, -1, lowest);
					}
				}
			});

			break;

		case 2: // insertion
			txt_title.setText(getString(R.string.insertion_name));
			txt_idx_k.setText(ctx.getString(R.string.key) + "(<) = " + key);
			txt_idx_k.setVisibility(View.VISIBLE);

			bt_start.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (isSorting) {
						stopSorter();
					} else {
						startSorter();
						updateValuesBars(arrayToSort);

						aux = 0;

						idx_2 = 1;
						// idx_i = 0;
						key = arrayToSort[idx_2];
						idx_1 = idx_2 - 1;

						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);
						spanString = new SpannableString(ctx
								.getString(R.string.key) + " = " + key);
						// spanString.setSpan(new UnderlineSpan(),
						// 0,spanString.length(), 0);
						txt_idx_k.setText(spanString);

						paintIndex(idx_1, idx_2, -1,
								getIndexArray(arrayToSort, key));

						isWhiling = ((idx_1 >= 0) && (arrayToSort[idx_1] > key));
						if (isWhiling) {
							arrayToSort[idx_1 + 1] = arrayToSort[idx_1];
							arrayToSort[idx_1] = key;
							idx_1 = idx_1 - 1;

						} else {
							arrayToSort[idx_1 + 1] = key;
						}
						updateValuesBars(arrayToSort);
						printArray(arrayToSort);
					}
				}

			});

			bt_next.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if (idx_2 < arrayToSort.length) {
						if (!isWhiling) {
							Log.d(SORTER_TAG, "fim J, reinicia I");
							key = arrayToSort[idx_2];
							idx_1 = idx_2 - 1;

							spanString = new SpannableString(ctx
									.getString(R.string.key) + " = " + key);
							// spanString.setSpan(new UnderlineSpan(),
							// 0,spanString.length(), 0);
							txt_idx_k.setText(spanString);

						}

						isWhiling = ((idx_1 >= 0) && (arrayToSort[idx_1] > key));

						if (isWhiling) {
							arrayToSort[idx_1 + 1] = arrayToSort[idx_1];
							idx_1 = idx_1 - 1;
						} else {
							arrayToSort[idx_1 + 1] = key;
							idx_2++;
						}
						txt_idx_i.setText("i = " + idx_1);
						txt_idx_j.setText("j = " + idx_2);
						spanString = new SpannableString(ctx
								.getString(R.string.key) + " = " + key);
						// spanString.setSpan(new UnderlineSpan(),
						// 0,spanString.length(), 0);
						txt_idx_k.setText(spanString);

						resetIndexes();
						paintIndex(idx_1, idx_2, -1,
								getIndexArray(arrayToSort, key));
						printArray(arrayToSort);
						updateValuesBars(arrayToSort);
					} else {
						stopSorter();

						Toast.makeText(ctx,
								ctx.getString(R.string.array_sorted),
								Toast.LENGTH_SHORT).show();
					}
				}
			});

			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void paintIndex(int i, int j, int k, int l) {
		String indexes = "";
		if (i >= 0) {
			indexes = " i = " + i;
		}
		if (j >= 0) {
			indexes += ", j = " + j;
		}
		if (l >= 0) {
			indexes += ", l = " + l;
		}

		paintIndex_i(i);
		paintIndex_j(j);
		paintIndex_lowest(l);

		Log.d(SORTER_TAG, indexes);

	}

	private static void paintIndex_i(int i) {
		i++;

		switch (i) {
		case 1:
			txt_1.setTextColor(COLOR_IDX_I);
			txt_1.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 2:
			txt_2.setTextColor(COLOR_IDX_I);
			txt_2.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 3:
			txt_3.setTextColor(COLOR_IDX_I);
			txt_3.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 4:
			txt_4.setTextColor(COLOR_IDX_I);
			txt_4.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 5:
			txt_5.setTextColor(COLOR_IDX_I);
			txt_5.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 6:
			txt_6.setTextColor(COLOR_IDX_I);
			txt_6.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 7:
			txt_7.setTextColor(COLOR_IDX_I);
			txt_7.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 8:
			txt_8.setTextColor(COLOR_IDX_I);
			txt_8.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 9:
			txt_9.setTextColor(COLOR_IDX_I);
			txt_9.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		case 10:
			txt_10.setTextColor(COLOR_IDX_I);
			txt_10.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);

			break;

		default:
			break;
		}
	}

	private static void paintIndex_lowest(int l) {
		l++;
		Log.d(SORTER_TAG, "lowest:" + l);
		switch (l) {
		case 1:
			txt_1.setText(txt_1.getText().toString() + "<");
			break;

		case 2:
			txt_2.setText(txt_2.getText().toString() + "<");
			break;

		case 3:
			txt_3.setText(txt_3.getText().toString() + "<");
			break;

		case 4:
			txt_4.setText(txt_4.getText().toString() + "<");
			break;

		case 5:
			txt_5.setText(txt_5.getText().toString() + "<");
			break;

		case 6:
			txt_6.setText(txt_6.getText().toString() + "<");
			break;

		case 7:
			txt_7.setText(txt_7.getText().toString() + "<");
			break;

		case 8:
			txt_8.setText(txt_8.getText().toString() + "<");
			break;

		case 9:
			txt_9.setText(txt_9.getText().toString() + "<");
			break;

		case 10:
			txt_10.setText(txt_10.getText().toString() + "<");
			break;

		default:
			break;
		}
	}

	private static void paintIndex_j(int j) {
		j++;

		switch (j) {
		case 1:
			txt_1.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_1.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 2:
			txt_2.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_2.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 3:
			txt_3.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_3.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 4:
			txt_4.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_4.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 5:
			txt_5.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_5.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 6:
			txt_6.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_6.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 7:
			txt_7.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_7.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 8:
			txt_8.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_8.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 9:
			txt_9.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_9.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		case 10:
			txt_10.setBackgroundColor(COLOR_IDX_BACKGROUND_J);
			txt_10.setTextSize(normalTxtSize * SIZE_IDX_ACTIVED);
			break;

		default:
			break;
		}
	}

	@SuppressLint("NewApi")
	private static void resetIndexes() {
		txt_1.setTextColor(COLOR_IDX_NORMAL);
		txt_1.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_1.setTextSize(normalTxtSize);
		txt_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(1), 0);
		txt_1.setText(ctx.getString(R.string._1));

		txt_2.setTextColor(COLOR_IDX_NORMAL);
		txt_2.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_2.setTextSize(normalTxtSize);
		txt_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(2), 0);
		txt_2.setText(ctx.getString(R.string._2));

		txt_3.setTextColor(COLOR_IDX_NORMAL);
		txt_3.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_3.setTextSize(normalTxtSize);
		txt_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(3), 0);
		txt_3.setText(ctx.getString(R.string._3));

		txt_4.setTextColor(COLOR_IDX_NORMAL);
		txt_4.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_4.setTextSize(normalTxtSize);
		txt_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(4), 0);
		txt_4.setText(ctx.getString(R.string._4));

		txt_5.setTextColor(COLOR_IDX_NORMAL);
		txt_5.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_5.setTextSize(normalTxtSize);
		txt_5.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(5), 0);
		txt_5.setText(ctx.getString(R.string._5));

		txt_6.setTextColor(COLOR_IDX_NORMAL);
		txt_6.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_6.setTextSize(normalTxtSize);
		txt_6.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(6), 0);
		txt_6.setText(ctx.getString(R.string._6));

		txt_7.setTextColor(COLOR_IDX_NORMAL);
		txt_7.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_7.setTextSize(normalTxtSize);
		txt_7.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(7), 0);
		txt_7.setText(ctx.getString(R.string._7));

		txt_8.setTextColor(COLOR_IDX_NORMAL);
		txt_8.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_8.setTextSize(normalTxtSize);
		txt_8.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(8), 0);
		txt_8.setText(ctx.getString(R.string._8));

		txt_9.setTextColor(COLOR_IDX_NORMAL);
		txt_9.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_9.setTextSize(normalTxtSize);
		txt_9.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(9), 0);
		txt_9.setText(ctx.getString(R.string._9));

		txt_10.setTextColor(COLOR_IDX_NORMAL);
		txt_10.setBackgroundColor(COLOR_IDX_BACKGROUND_NORMAL);
		txt_10.setTextSize(normalTxtSize);
		txt_10.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBarInt(10), 0);
		txt_10.setText(ctx.getString(R.string._10));

	}

	private static void updateValuesBars(Integer[] arrayToUpdate) {
		txt_1.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[0]), 0);
		txt_1.setText(arrayToUpdate[0].toString());

		txt_2.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[1]), 0);
		txt_2.setText(arrayToUpdate[1].toString());

		txt_3.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[2]), 0);
		txt_3.setText(arrayToUpdate[2].toString());

		txt_4.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[3]), 0);
		txt_4.setText(arrayToUpdate[3].toString());

		txt_5.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[4]), 0);
		txt_5.setText(arrayToUpdate[4].toString());

		txt_6.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[5]), 0);
		txt_6.setText(arrayToUpdate[5].toString());

		txt_7.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[6]), 0);
		txt_7.setText(arrayToUpdate[6].toString());

		txt_8.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[7]), 0);
		txt_8.setText(arrayToUpdate[7].toString());

		txt_9.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[8]), 0);
		txt_9.setText(arrayToUpdate[8].toString());

		txt_10.setCompoundDrawablesWithIntrinsicBounds(0, 0,
				getBarInt(arrayToUpdate[9]), 0);
		txt_10.setText(arrayToUpdate[9].toString());

	}

	private static int getBarInt(int idx) {
		switch (idx) {
		case 1:
			return R.drawable.bar_1;
		case 2:
			return R.drawable.bar_2;
		case 3:
			return R.drawable.bar_3;
		case 4:
			return R.drawable.bar_4;
		case 5:
			return R.drawable.bar_5;
		case 6:
			return R.drawable.bar_6;
		case 7:
			return R.drawable.bar_7;
		case 8:
			return R.drawable.bar_8;
		case 9:
			return R.drawable.bar_9;
		case 10:
			return R.drawable.bar_10;
		default:
			return 0;
		}

	}

	private static void printArray(Integer[] arrayToPrint) {
		String array = "[" + arrayToPrint[0] + ", ";
		array += arrayToPrint[1] + ", ";
		array += arrayToPrint[2] + ", ";
		array += arrayToPrint[3] + ", ";
		array += arrayToPrint[4] + ", ";
		array += arrayToPrint[5] + ", ";
		array += arrayToPrint[6] + ", ";
		array += arrayToPrint[7] + ", ";
		array += arrayToPrint[8] + ", ";
		array += arrayToPrint[9] + "]";
		Log.d(SORTER_TAG, array);
	}

	private static void stopSorter() {
		isSorting = false;
		bt_next.setEnabled(false);
		bt_start.setText(ctx.getString(R.string.start));
		idx_1 = 0;
		idx_2 = 0;
		idx_3 = 0;
		aux = 0;
		key = 0;

		// printArray(PRIMARY_ARRAY);
		// arrayToSort = PRIMARY_ARRAY;
		// printArray(arrayToSort);
		// updateValuesBars(PRIMARY_ARRAY);
		resetIndexes();
	}

	private static void startSorter() {
		isSorting = true;

		bt_start.setText(ctx.getString(R.string.stop));
		bt_next.setEnabled(true);

		List<Integer> arraySorted = Arrays.asList(PRIMARY_ARRAY);
		Collections.shuffle(arraySorted);
		arrayToSort = (Integer[]) arraySorted.toArray();
		printArray(arrayToSort);
	}

	private int getIndexArray(Integer[] arrayToSort, int key) {
		int index = 0;

		for (int i = 0; i < arrayToSort.length; i++) {
			if (key == arrayToSort[i]) {
				index = i;
			}
		}

		return index;
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("IDX_1", idx_1);
		outState.putInt("IDX_2", idx_2);
		outState.putInt("IDX_3", idx_3);
		outState.putInt("LOWEST", lowest);
		outState.putInt("KEY", key);
		outState.putBoolean("ISSORTING", isSorting);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		idx_1 = savedInstanceState.getInt("IDX_1");
		idx_2 = savedInstanceState.getInt("IDX_2");
		idx_3 = savedInstanceState.getInt("IDX_3");
		lowest = savedInstanceState.getInt("LOWEST");
		key = savedInstanceState.getInt("KEY");
		isSorting = savedInstanceState.getBoolean("ISSORTING");

		if (isSorting) {
			updateValuesBars(arrayToSort);
			txt_idx_i.setText("i = " + idx_1);
			txt_idx_j.setText("j = " + idx_2);

			bt_next.setEnabled(true);
			bt_start.setText(ctx.getString(R.string.stop));

			switch (sorter_type) {
			case 0:
				paintIndex(idx_1, idx_2, -1, -1);

				break;

			case 1:
				paintIndex(idx_1, idx_2, -1, lowest);
				txt_idx_k.setText(ctx.getString(R.string.lowest) + " = "
						+ lowest);

				break;

			case 2:
				paintIndex(idx_1, idx_2, -1, getIndexArray(arrayToSort, key));
				txt_idx_k.setText(ctx.getString(R.string.key) + " = " + key);

				break;

			default:
				break;
			}
		}

	}
}
