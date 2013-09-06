package edu.gxu.impression.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.*;
import android.widget.*;
import com.github.longkai.android.app.ActionBarDrawerHelper;
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
public class CardActivity extends ActionBarActivity implements DrawerLayout.DrawerListener, AdapterView.OnItemClickListener {

	private CardItemsAdapter mAdapter;
	private ListView mCards;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawer;
	private ActionBarDrawerToggle mDrawerToggle;
	private ActionBarDrawerHelper mActionBar;

	private static final int[] DRAWER_ICONS = {
		R.drawable.content_picture_dark,
		R.drawable.navigation_refresh_dark,
		R.drawable.action_about_dark,
		R.drawable.social_add_group_dark,
	};

	private String[] drawerItems;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(com.github.longkai.android.R.layout.fragment_container);
//		getSupportFragmentManager()
//				.beginTransaction()
//					.replace(com.github.longkai.android.R.id.fragment_container, new CardFragment())
//				.commit();
		setContentView(R.layout.main);
		mDrawer = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.open_drawer, R.string.close_drawer);
		mDrawerLayout.setDrawerListener(this);

		mActionBar = new ActionBarCompatDrwaerHelper(getSupportActionBar());
		mActionBar.init();


		drawerItems = getResources().getStringArray(R.array.drwer_items);

		mDrawer.setAdapter(new SimpleAdapter(this, makeData(), R.layout.drawer_row,
				new String[]{Constants.IMAGE, Constants.TITLE},
				new int[]{android.R.id.icon, android.R.id.title}));
		mDrawer.setOnItemClickListener(this);

		mCards = (ListView) findViewById(android.R.id.list);
		mAdapter = new CardItemsAdapter(this, makeCards());
		mCards.setAdapter(mAdapter);
		mCards.setDividerHeight(0);
//		getSupportActionBar().setSubtitle(getString(R.string.lab_product));
	}

	private List<Map<String, ?>> makeData() {
		List<Map<String, ?>> items = new ArrayList<Map<String, ?>>(DRAWER_ICONS.length);
		for (int i = 0; i < DRAWER_ICONS.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put(Constants.IMAGE, DRAWER_ICONS[i]);
			item.put(Constants.TITLE, drawerItems[i]);
			items.add(item);
		}
		return items;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item != null && item.getItemId() == android.R.id.home && mDrawerToggle.isDrawerIndicatorEnabled()) {
			if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
				mDrawerLayout.closeDrawer(GravityCompat.START);
			} else {
				mDrawerLayout.openDrawer(GravityCompat.START);
			}
			return true;
		}
		switch (item.getItemId()) {
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
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem share = menu.findItem(R.id.share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
		provider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {
			@Override
			public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
				startActivity(intent);
				return true;
			}
		});
		provider.setShareIntent(Constants.makeAShare(this, getString(R.string.app_name), ""));
		return true;
	}

	private List<Map<String, ?>> makeCards() {
		List<Map<String, ?>> cards = new ArrayList<Map<String, ?>>();

		Map<String, Object> campus = new HashMap<String, Object>();
		campus.put(Constants.TITLE, getString(R.string.card_campus));
		campus.put(Constants.IMAGE, R.drawable.study_inside_outside);
		campus.put(Constants.ITEMS, getResources().getStringArray(R.array.campus_items));
		cards.add(campus);

		Map<String, Object> life = new HashMap<String, Object>();
		life.put(Constants.TITLE, getString(R.string.card_life));
		life.put(Constants.ITEMS, getResources().getStringArray(R.array.life_items));
		life.put(Constants.IMAGE, R.drawable.foods);
		cards.add(life);

		Map<String, Object> yws = new HashMap<String, Object>();
		yws.put(Constants.TITLE, getString(R.string.card_yws));
		yws.put(Constants.ITEMS, getResources().getStringArray(R.array.yws_items));
		yws.put(Constants.IMAGE, R.drawable.meet_yws);
		cards.add(yws);

		return cards;
	}

	@Override
	public void onDrawerSlide(View drawerView, float slideOffset) {
		mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
	}

	@Override
	public void onDrawerOpened(View drawerView) {
		mDrawerToggle.onDrawerOpened(drawerView);
		mActionBar.onDrawerOpened();
	}

	@Override
	public void onDrawerClosed(View drawerView) {
		mDrawerToggle.onDrawerClosed(drawerView);
		mActionBar.onDrawerClosed();
	}

	@Override
	public void onDrawerStateChanged(int newState) {
		mDrawerToggle.onDrawerStateChanged(newState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				Intent i = new Intent(this, HelloActivity.class);
				i.putExtra(Constants.RUN_WELCOME_AGAIN, true);
				startActivity(i);
				break;
			case 1:
				// check for new version.
				// use json uri http://lab.newgxu.cn/apps/2/history.json, but it required internet access
				// fallback, we use browser...
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://lab.newgxu.cn/apps/2")));
				break;
			case 2:
				startActivity(new Intent(this, AboutActivity.class));
				break;
			case 3:
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://lab.newgxu.cn")));
				break;
			default:
				break;
		}
		mDrawerLayout.closeDrawer(mDrawer);
	}

	private static class CardItemsAdapter extends SimpleAdapter implements View.OnClickListener {

		private Context mContext;
		private List<? extends Map<String, ?>> mData;

		public CardItemsAdapter(Context context, List<? extends Map<String, ?>> data) {
			super(context, data, R.layout.card, null, null);
			this.mContext = context;
			this.mData = data;
		}

		@Override
		public void onClick(View v) {
//			hard code -.-
			int id = v.getId();
			int titleRes = R.string.places_at_gxu_title;
			Class<? extends Activity> activity = null;
			if (id == "00".hashCode()) {
				titleRes = R.string.cards_at_gxu_title;
			} else if (id == "01".hashCode()) {
				titleRes = R.string.places_at_gxu_title;
			} else if (id == "02".hashCode()) {
				titleRes = R.string.study_inside_outside_title;
			} else if (id == "10".hashCode()) {
				titleRes = R.string.refresh_guides_title;
				activity = ArticleActivity.class;
			} else if (id == "11".hashCode()) {
				titleRes = R.string.tips_title;
				activity = ArticleActivity.class;
			} else if (id == "12".hashCode()) {
				titleRes = R.string.food_title;
				activity = ArticleActivity.class;
			} else if (id == "13".hashCode()) {
				titleRes = R.string.outside_title;
				activity = ArticleActivity.class;
			} else if (id == "20".hashCode()) {
				titleRes = R.string.yws_refresh_group_title;
				activity = ArticleActivity.class;
			} else if (id == "21".hashCode()) {
				titleRes = R.string.i_wanna_say;
				activity = ListItemsActivity.class;
			} else if (id == "22".hashCode()) {
				titleRes = R.string.meet_yws_title;
				activity = ArticleActivity.class;
			} else if (id == "23".hashCode()) {
				titleRes = R.string.close_to_yws_title;
			} else if (id == "24".hashCode()) {
				titleRes = R.string.bloom_yws_title;
			} else if (id == "25".hashCode()) {
				titleRes = R.string.sounds_title;
				activity = SoundsMagzineActivity.class;
			}

			if (activity == null) {
				activity = PagerArticleActivity.class;
			}
			Intent intent = new Intent(mContext, activity);
			intent.putExtra(Constants.TITLE, titleRes);
			mContext.startActivity(intent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			Map<String, ?> map = mData.get(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
				holder = new ViewHolder();
				holder.container = (LinearLayout) convertView.findViewById(R.id.items);
				holder.title = (TextView) convertView.findViewById(android.R.id.title);
				holder.image = (ImageView) convertView.findViewById(R.id.card_image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.image.setImageResource((Integer) map.get(Constants.IMAGE));
			holder.title.setText(map.get(Constants.TITLE).toString());
			holder.container.removeAllViews();
			String[] items = (String[]) map.get(Constants.ITEMS);
			for (int i = 0; i < items.length; i++) {
				TextView tv = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
				tv.setId((position + "" + i).hashCode());
				tv.setOnClickListener(this);
				tv.setText(items[i]);
				holder.container.addView(tv);
			}
			return convertView;
		}

		private static class ViewHolder {
			LinearLayout container;
			TextView title;
			ImageView image;
		}
	}

	private static class ActionBarCompatDrwaerHelper implements ActionBarDrawerHelper {

		private ActionBar mActionBar;

		private ActionBarCompatDrwaerHelper(ActionBar mActionBar) {
			this.mActionBar = mActionBar;
		}

		@Override
		public void init() {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
		}

		@Override
		public void onDrawerClosed() {
		}

		@Override
		public void onDrawerOpened() {
		}

		@Override
		public void setTitle(CharSequence title) {
		}
	}

}