package br.nom.strey.maicon.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	static final int COLOR_IDX_NORMAL = Color.rgb(0, 0, 0); // Black - 000000
	static final int COLOR_IDX_I = Color.rgb(0, 0, 205); // Medium Blue - 0000cd
	static final int COLOR_IDX_J = Color.rgb(34, 139, 34); // Forest Green -
															// 228b22
	static final int COLOR_IDX_K = Color.rgb(255, 215, 0); // Gold - ffd700

	static final float SIZE_IDX_ACTIVED = (float) 30;
	static final float SIZE_IDX_NORMAL = (float) 33;

	static Context ctx;

	static TextView txt_idx_i;
	static TextView txt_idx_j;
	static TextView txt_idx_k;

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

	static Boolean isSorting = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		ctx = getApplicationContext();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.bubble_name);
			case 1:
				return getString(R.string.insertion_name);
			case 2:
				return getString(R.string.selection_name);
			case 3:
				return getString(R.string.merge_name);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_sorter,
					container, false);

			final Integer tab = getArguments().getInt(ARG_SECTION_NUMBER);

			final Switch sw_automatic = (Switch) rootView
					.findViewById(R.id.sw_automatic);

			txt_1 = (TextView) rootView.findViewById(R.id.txt_sort_1);
			txt_2 = (TextView) rootView.findViewById(R.id.txt_sort_2);
			txt_3 = (TextView) rootView.findViewById(R.id.txt_sort_3);
			txt_4 = (TextView) rootView.findViewById(R.id.txt_sort_4);
			txt_5 = (TextView) rootView.findViewById(R.id.txt_sort_5);
			txt_6 = (TextView) rootView.findViewById(R.id.txt_sort_6);
			txt_7 = (TextView) rootView.findViewById(R.id.txt_sort_7);
			txt_8 = (TextView) rootView.findViewById(R.id.txt_sort_8);
			txt_9 = (TextView) rootView.findViewById(R.id.txt_sort_9);
			txt_10 = (TextView) rootView.findViewById(R.id.txt_sort_10);

			txt_idx_i = (TextView) rootView.findViewById(R.id.txt_idx1);
			txt_idx_j = (TextView) rootView.findViewById(R.id.txt_idx2);
			txt_idx_k = (TextView) rootView.findViewById(R.id.txt_idx3);

			txt_idx_i.setTextColor(COLOR_IDX_I);
			txt_idx_j.setTextColor(COLOR_IDX_J);

			bt_start = (Button) rootView.findViewById(R.id.bt_start);

			bt_start.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (!isSorting) {
						isSorting = true;
						switch (tab) {
						case 1:
							Toast.makeText(ctx, "case 0", Toast.LENGTH_SHORT)
									.show();
							if (sw_automatic.isChecked()) {
								Toast.makeText(ctx, "automatic",
										Toast.LENGTH_SHORT).show();
								bubble_sort_automatic();
								isSorting = false;
							} else {
								Toast.makeText(ctx, "manual",
										Toast.LENGTH_SHORT).show();
								bubble_sort_manual();
							}
							break;

						default:
							break;
						}

					} else {
						isSorting = false;
					}
				}
			});

			return rootView;

		}

		// Implementing Fisher–Yates shuffle
		static void shuffleArray(int[] ar) {
			Random rnd = new Random();
			for (int i = ar.length - 1; i >= 0; i--) {
				int index = rnd.nextInt(i + 1);
				// Simple swap
				int a = ar[index];
				ar[index] = ar[i];
				ar[i] = a;
			}
		}

		private static void bubble_sort_automatic() {
			Integer[] array = { Integer.valueOf(1), Integer.valueOf(2),
					Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5),
					Integer.valueOf(6), Integer.valueOf(6), Integer.valueOf(5),
					Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(2),
					Integer.valueOf(1) };
			List<Integer> solutionList = Arrays.asList(array);
			Collections.shuffle(solutionList);

			bt_start.setText(String.valueOf(R.string.stop));

			int aux = 0;
			Log.d("BUBBLE SORT", array.toString());

			for (int i = 1; i <= array.length; i++) {
				txt_idx_i.setText("i = " + i);
				for (int j = i; j <= array.length; j++) {
					txt_idx_j.setText("j = " + j);
					paintIndex(i, j, 0);
					if (array[i] > array[j]) {
						aux = array[i];
						array[i] = array[j];
						array[j] = aux;
						SystemClock.sleep(1000);
					}
				}
			}

		}

		private static void paintIndex(int i, int j, int k) {
			// TODO Auto-generated method stub
			switch (i) {
			case 1:
				txt_1.setTextColor(COLOR_IDX_I);
				txt_1.setTextSize(SIZE_IDX_ACTIVED);

				break;

			case 2:
				txt_1.setTextColor(COLOR_IDX_NORMAL);
				txt_1.setTextSize(SIZE_IDX_NORMAL);
				txt_2.setTextColor(COLOR_IDX_I);
				txt_2.setTextSize(SIZE_IDX_ACTIVED);

				break;

			default:
				break;
			}
		}

		private static void bubble_sort_manual() {
			Integer[] array = { Integer.valueOf(1), Integer.valueOf(2),
					Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5),
					Integer.valueOf(6), Integer.valueOf(6), Integer.valueOf(5),
					Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(2),
					Integer.valueOf(1) };
			List solutionList = Arrays.asList(array);
			Collections.shuffle(solutionList);

			int aux = 0;
			System.out.println("bubble sort");

			for (int i = 0; i < array.length; i++) {
				System.out.print(" i:" + i + " ");
				for (int j = i; j < array.length; j++) {
					if (array[i] > array[j]) {
						aux = array[i];
						array[i] = array[j];
						array[j] = aux;
					}
					System.out.print((j - i + 1) + ",");
				}
			}

		}

	}

}
