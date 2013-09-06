package edu.gxu.impression.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

	protected String[] mTitles;
	protected Fragment[] mFragments;


	public CustomFragmentPagerAdapter(FragmentManager fm, String[] titles) {
		super(fm);
		this.mTitles = titles;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments[position];
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}
}
