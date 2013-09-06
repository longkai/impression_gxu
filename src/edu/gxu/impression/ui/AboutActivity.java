/*
 * The MIT License (MIT)
 * Copyright (c) 2013 longkai(龙凯)
 * The software shall be used for good, not evil.
 */
package edu.gxu.impression.ui;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import com.github.longkai.android.app.BackGestureListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-9-5
 * @Mail im.longkai@gmail.com
 */
public class AboutActivity extends ActionBarActivity {

	private GestureDetectorCompat mCompat;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mCompat = new GestureDetectorCompat(this, new BackGestureListener(this));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mCompat.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
