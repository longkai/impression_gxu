package edu.gxu.impression.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.*;
import com.github.longkai.android.app.BackGestureListener;
import edu.gxu.impression.util.Constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-9-5
 * @Mail im.longkai@gmail.com
 */
public class SoundsMagzineActivity extends ActionBarActivity {

	private int mTitleRes;
	private GestureDetectorCompat mDetector;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.github.longkai.android.R.layout.fragment_container);

		mTitleRes = getIntent().getIntExtra(Constants.TITLE, 0);

		getSupportFragmentManager()
				.beginTransaction()
				.replace(com.github.longkai.android.R.id.fragment_container, new SoundsFragment())
				.commit();

		getSupportActionBar().setTitle(mTitleRes);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mDetector = new GestureDetectorCompat(this, new BackGestureListener(this));
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem share = menu.findItem(R.id.share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
		provider.setShareIntent(Constants.makeAShare(this, getString(mTitleRes), getString(R.string.sounds_desc)));
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

	public static class SoundsFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.sounds, container, false);
			view.findViewById(android.R.id.text1).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Fragment article = new ImageWithTextArticleFragment();
					Bundle arg = new Bundle();
					arg.putInt(Constants.CONTENT, R.string.excellent_graduate_content);
					arg.putInt(Constants.IMAGE, R.drawable.excellent_graduate);
					article.setArguments(arg);

					getFragmentManager()
							.beginTransaction()
							.replace(com.github.longkai.android.R.id.fragment_container, article)
							.addToBackStack(null)
							.commit();
				}
			});
			view.findViewById(android.R.id.text2).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(), PagerArticleActivity.class);
					i.putExtra(Constants.TITLE, R.string.weekly_images_title);
					getActivity().startActivity(i);
				}
			});
			return view;
		}
	}
}