package edu.gxu.impression.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import edu.gxu.impression.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pager article layout.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class PagerArticleActivity extends ActionBarActivity {

//	private GestureDetectorCompat mDetector;

	private int mIndex;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.github.longkai.android.R.layout.pager_with_tab_strip);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		PagerTabStrip tabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
		tabStrip.setTabIndicatorColorResource(com.github.longkai.android.R.color.holo_blue_dark);
		tabStrip.setBackgroundResource(com.github.longkai.android.R.color.holo_blue_light);

		mIndex = getIntent().getIntExtra(Constants.TITLE, 0);
		switch (mIndex) {
			case R.string.cards_at_gxu_title:
				pager.setAdapter(new CampusCardsAdapter(getSupportFragmentManager(),
						getResources().getStringArray(R.array.cards_at_gxu)));
				break;
			case R.string.places_at_gxu_title:
				pager.setAdapter(new ImageWithTextPagerAdapter(getSupportFragmentManager(),
						getResources().getStringArray(R.array.places_at_gxu)));
				break;
			case R.string.study_inside_outside_title:
				pager.setAdapter(new StudyInsideOutsideAdapter(getSupportFragmentManager(),
						getResources().getStringArray(R.array.study_inside_outside)));
				break;
			case R.string.bloom_yws_title:
				pager.setAdapter(new PlatformsPagerAdapter(getSupportFragmentManager(),
						getResources().getStringArray(R.array.platforms)));
				break;
			case  R.string.close_to_yws_title:
				pager.setAdapter(new YwsAvtivitiesPagerAdapater(getSupportFragmentManager()));
				tabStrip.setVisibility(View.GONE);
				break;
			case R.string.weekly_images_title:
				pager.setAdapter(new WeeklyImagesPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.magazine_iamge_descs)));
				tabStrip.setVisibility(View.GONE);
			default:
				break;
		}

		setTitle(mIndex);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		mDetector = new GestureDetectorCompat(this, new BackGestureListener(this));
	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		mDetector.onTouchEvent(event);
//		return super.onTouchEvent(event);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem share = menu.findItem(R.id.share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
		provider.setShareIntent(Constants.makeAShare(this, getString(mIndex), ""));
		return true;
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

	private static class CampusCardsAdapter extends CustomFragmentPagerAdapter {

		public CampusCardsAdapter(FragmentManager fm, String[] titles) {
			super(fm, titles);
			this.mTitles = titles;
			this.mFragments = new Fragment[mTitles.length];
			Bundle arg = new Bundle();
			arg.putInt(Constants.CONTENT, R.string.student_card_content);
			mFragments[0] = new TextArticleFragment();
			mFragments[0].setArguments(arg);

			Bundle arg1 = new Bundle();
			arg1.putInt(Constants.CONTENT, R.string.hot_water_content);
			mFragments[1] = new TextArticleFragment();
			mFragments[1].setArguments(arg1);

			Bundle arg2 = new Bundle();
			arg2.putInt(Constants.CONTENT, R.string.golden_card_content);
			mFragments[2] = new TextArticleFragment();
			mFragments[2].setArguments(arg2);

			Bundle arg3 = new Bundle();
			arg3.putInt(Constants.CONTENT, R.string.swimming_card_content);
			mFragments[3] = new TextArticleFragment();
			mFragments[3].setArguments(arg3);
		}

	}

	private static class ImageWithTextPagerAdapter extends CustomFragmentPagerAdapter {

		public ImageWithTextPagerAdapter(FragmentManager fm, String[] titles) {
			super(fm, titles);

			mFragments = new Fragment[mTitles.length];
			mFragments[0] = new FrutisFragment();

			Bundle arg1 = new Bundle();
			arg1.putInt(Constants.IMAGE, R.drawable.big_building);
			arg1.putInt(Constants.CONTENT, R.string.big_building);
			mFragments[1] = new ImageWithTextArticleFragment();
			mFragments[1].setArguments(arg1);

			Bundle arg2 = new Bundle();
			arg2.putInt(Constants.IMAGE, R.drawable.library);
			arg2.putInt(Constants.CONTENT, R.string.library);
			mFragments[2] = new ImageWithTextArticleFragment();
			mFragments[2].setArguments(arg2);

			Bundle arg3 = new Bundle();
			arg3.putInt(Constants.IMAGE, R.drawable.classroom);
			arg3.putInt(Constants.CONTENT, R.string.classroom);
			mFragments[3] = new ImageWithTextArticleFragment();
			mFragments[3].setArguments(arg3);

			Bundle arg4 = new Bundle();
			arg4.putInt(Constants.IMAGE, R.drawable.hall);
			arg4.putInt(Constants.CONTENT, R.string.hall);
			mFragments[4] = new ImageWithTextArticleFragment();
			mFragments[4].setArguments(arg4);

			Bundle arg5 = new Bundle();
			arg5.putInt(Constants.IMAGE, R.drawable.junwu_building);
			arg5.putInt(Constants.CONTENT, R.string.junwu_building);
			mFragments[5] = new ImageWithTextArticleFragment();
			mFragments[5].setArguments(arg5);

			Bundle arg6 = new Bundle();
			arg6.putInt(Constants.IMAGE, R.drawable.network_building);
			arg6.putInt(Constants.CONTENT, R.string.network_building);
			mFragments[6] = new ImageWithTextArticleFragment();
			mFragments[6].setArguments(arg6);

		}
	}

	private static class StudyInsideOutsideAdapter extends CustomFragmentPagerAdapter {

		public StudyInsideOutsideAdapter(FragmentManager fm, String[] titles) {
			super(fm, titles);

			mFragments = new Fragment[mTitles.length];
			Bundle arg0 = new Bundle();
			arg0.putInt(Constants.CONTENT, R.string.select_courses);
			mFragments[0] = new TextArticleFragment();
			mFragments[0].setArguments(arg0);

			Bundle arg1 = new Bundle();
			arg1.putInt(Constants.CONTENT, R.string.course_recommendation);
			mFragments[1] = new TextArticleFragment();
			mFragments[1].setArguments(arg1);

			Bundle arg2 = new Bundle();
			arg2.putInt(Constants.CONTENT, R.string.campus_activities);
			mFragments[2] = new TextArticleFragment();
			mFragments[2].setArguments(arg2);

			Bundle arg3 = new Bundle();
			arg3.putInt(Constants.CONTENT, R.string.campus_parties);
			arg3.putInt(Constants.IMAGE, R.drawable.outside);
			mFragments[3] = new ImageWithTextArticleFragment();
			mFragments[3].setArguments(arg3);
		}
	}

	private static class PlatformsPagerAdapter extends CustomFragmentPagerAdapter{

		public PlatformsPagerAdapter(FragmentManager fm, String[] titles) {
			super(fm, titles);
			mFragments = new Fragment[titles.length];
			Bundle arg0 = new Bundle();
			arg0.putInt(Constants.CONTENT, R.string.bbs);
			mFragments[0] = new TextArticleFragment();
			mFragments[0].setArguments(arg0);

			Bundle arg1 = new Bundle();
			arg1.putInt(Constants.CONTENT, R.string.lab);
			mFragments[1] = new TextArticleFragment();
			mFragments[1].setArguments(arg1);
		}
	}

	private static class YwsAvtivitiesPagerAdapater extends FragmentPagerAdapter {

		private static final int[] IMAGES = {
			R.drawable.yws_01,
			R.drawable.yws_02,
			R.drawable.yws_03,
			R.drawable.yws_04,
			R.drawable.yws_05,
			R.drawable.yws_06,
			R.drawable.yws_07,
			R.drawable.yws_08,
			R.drawable.yws_09,
			R.drawable.yws_10,
		};

		private Fragment[] mFragments;

		public YwsAvtivitiesPagerAdapater(FragmentManager fm) {
			super(fm);
			mFragments = new Fragment[IMAGES.length];
			for (int i = 0; i < IMAGES.length; i++) {
				mFragments[i] = new ImageWithTextArticleFragment();
				Bundle arg = new Bundle();
				arg.putInt(Constants.IMAGE, IMAGES[i]);
				mFragments[i].setArguments(arg);
			}
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments[position];
		}

		@Override
		public int getCount() {
			return IMAGES.length;
		}
	}

	public static class FrutisFragment extends ListFragment {

		private SimpleAdapter mAdapter;

		private static int[] mFruits = {
				R.drawable.fruit1,
				R.drawable.fruit2,
				R.drawable.fruit3,
				R.drawable.fruit4,
				R.drawable.fruit5,
				R.drawable.fruit6,
				R.drawable.fruit7,
		};

		private static String[] sDescs;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			sDescs = getResources().getStringArray(R.array.fruits);
			mAdapter = new SimpleAdapter(getActivity(), frutis(),
					R.layout.fruit_row,
					new String[]{Constants.IMAGE, Constants.CONTENT},
					new int[]{R.id.image, android.R.id.content});
		}

		private List<Map<String, ?>> frutis() {
			List<Map<String, ?>> fruits = new ArrayList<Map<String, ?>>(mFruits.length);
			for (int i = 0; i < mFruits.length; i++) {
				Map<String, Object> f = new HashMap<String, Object>();
				f.put(Constants.IMAGE, mFruits[i]);
				f.put(Constants.CONTENT, sDescs[i]);
				fruits.add(f);
			}
			return fruits;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setListAdapter(mAdapter);
		}
	}

	private static class WeeklyImagesPagerAdapter extends FragmentPagerAdapter {

		private static final int[] IMAGES = {
			R.drawable.magazine_1,
			R.drawable.magazine_2,
			R.drawable.magazine_3,
			R.drawable.magazine_4,
			R.drawable.magazine_5,
			R.drawable.magazine_6,
			R.drawable.magazine_7,
		};

		private Fragment[] mFragments;

		public WeeklyImagesPagerAdapter(FragmentManager fm, String[] descs) {
			super(fm);
			mFragments = new Fragment[IMAGES.length];
			for (int i = 0; i < IMAGES.length; i++) {
				Bundle arg = new Bundle();
				arg.putInt(Constants.IMAGE, IMAGES[i]);
				arg.putString(Constants.CONTENT, descs[i]);

				mFragments[i] = new ImageWithTextArticleFragment();
				mFragments[i].setArguments(arg);
			}
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments[position];
		}

		@Override
		public int getCount() {
			return IMAGES.length;
		}
	}
}