package edu.gxu.impression.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import com.github.longkai.android.app.BackGestureListener;
import edu.gxu.impression.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class ListItemsActivity extends ActionBarActivity {

	private GestureDetectorCompat mDetector;

	private int mIndex;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.github.longkai.android.R.layout.fragment_container);

		Fragment fragment;

		mIndex = getIntent().getIntExtra(Constants.TITLE, 0);
		switch (mIndex) {
			case R.string.i_wanna_say:
				fragment = new IWannaSayFragment();
				break;
//			case R.string.fruits:
//				fragment = new FrutisFragment();
//				break;
			default:
				throw new RuntimeException("no this item!");
		}

		fragment = new IWannaSayFragment();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(com.github.longkai.android.R.id.fragment_container, fragment)
				.commit();
		setTitle(mIndex);

		mDetector = new GestureDetectorCompat(this, new BackGestureListener(this));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			case R.id.quit:
				// simply back to home screen, not really quit
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem share = menu.findItem(R.id.share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
		provider.setShareIntent(Constants.makeAShare(this, getString(mIndex), ""));
		return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	public static class IWannaSayFragment extends ListFragment {

		private BaseAdapter mAdapter;
		private int[] icons = {
				R.drawable.p1,
				R.drawable.p2,
				R.drawable.p3,
				R.drawable.p4,
				R.drawable.p5,
				R.drawable.p6,
				R.drawable.p7,
				R.drawable.p8,
				R.drawable.p9,
				R.drawable.p10,
				R.drawable.p11,
				R.drawable.p12,
				R.drawable.p13,
				R.drawable.p14,
				R.drawable.p15,
		};
		private String[] names;
		private String[] words;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			names = getResources().getStringArray(R.array.i_wanna_people);
			words = getResources().getStringArray(R.array.words);
			mAdapter = new SimpleAdapter(getActivity(), makeData(), R.layout.list_row,
					new String[]{Constants.CONTENT, Constants.IMAGE, Constants.TITLE},
					new int[]{android.R.id.content, android.R.id.icon, android.R.id.title});
		}

		private List<Map<String, ?>> makeData() {
			List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
			for (int i = 0; i < icons.length; i++) {
				Map<String, Object> word = new HashMap<String, Object>();
				word.put(Constants.TITLE, names[i]);
				word.put(Constants.IMAGE, icons[i]);
				word.put(Constants.CONTENT, words[i]);
				list.add(word);
			}
			return list;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setListAdapter(mAdapter);
		}
	}


}