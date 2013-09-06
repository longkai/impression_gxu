/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package edu.gxu.impression.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import edu.gxu.impression.app.App;
import edu.gxu.impression.util.Constants;

public class HelloActivity extends FragmentActivity {

	private static final String IMAGE_SOURCE = "src";
	private static final String LAST_IMAGE = "last_image";

	private App mApp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApp = App.getApp();
		SharedPreferences prefs = mApp.getmPrefs();
		boolean b = getIntent().getBooleanExtra(Constants.RUN_WELCOME_AGAIN, false);
		int runTimes = prefs.getInt(Constants.RUN_TIMES, 0);
		if (runTimes > Constants.MAX_RUN_TIEMS && !b) {
			startActivity(new Intent(this, CardActivity.class));
		} else {
			setContentView(com.github.longkai.android.R.layout.pager);
			ViewPager gallery = (ViewPager) findViewById(com.github.longkai.android.R.id.pager);
			gallery.setAdapter(new GalleryPagerAdapter(getSupportFragmentManager()));
			prefs.edit().putInt(Constants.RUN_TIMES, runTimes + 1).commit();
		}
//		setContentView(com.github.longkai.android.R.layout.pager);
//		ViewPager gallery = (ViewPager) findViewById(com.github.longkai.android.R.id.pager);
//		gallery.setAdapter(new GalleryPagerAdapter(getSupportFragmentManager()));
//		prefs.edit().putBoolean(Constants.RUN_TIMES, false).commit();
	}

	private static class GalleryPagerAdapter extends FragmentStatePagerAdapter {

		private Fragment[] images;

		private static int[] srcs = {
			R.drawable.hello1,
			R.drawable.hello2,
			R.drawable.hello3,
		};

		public GalleryPagerAdapter(FragmentManager fm) {
			super(fm);
			images = new Fragment[srcs.length];

			for (int i = 0; i < srcs.length; i++) {
				images[i] = new ImageFragment();
				Bundle arg = new Bundle();
				arg.putInt(IMAGE_SOURCE, srcs[i]);
				if (i == srcs.length - 1) {
					arg.putBoolean(LAST_IMAGE, true);
				}
				images[i].setArguments(arg);
			}
		}

		@Override
		public Fragment getItem(int position) {
			return images[position];
		}

		@Override
		public int getCount() {
			return srcs.length;
		}

	}

	public static class ImageFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			Bundle arguments = getArguments();
			int src = arguments.getInt(IMAGE_SOURCE);
			View view = inflater.inflate(R.layout.hello, container, false);
			ImageView img = (ImageView) view.findViewById(R.id.image);
			img.setImageResource(src);
			View button = view.findViewById(android.R.id.button1);
			if (arguments.getBoolean(LAST_IMAGE)) {
				view.findViewById(R.id.indicator).setVisibility(View.INVISIBLE);
				button.setVisibility(View.VISIBLE);
				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(getActivity(), CardActivity.class));
					}
				});
			} else {
				button.setVisibility(View.GONE);
			}
			return view;
		}
	}
}
