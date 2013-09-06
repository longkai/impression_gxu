package edu.gxu.impression.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import com.github.longkai.android.app.BackGestureListener;
import edu.gxu.impression.util.Constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class ArticleActivity extends ActionBarActivity {

	private GestureDetectorCompat mDetector;

	private int mIndex;
	private int mContent;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.github.longkai.android.R.layout.fragment_container);

		mIndex = getIntent().getIntExtra(Constants.TITLE, 0);
		int imageSrc = 0;
		switch (mIndex) {
			case R.string.refresh_guides_title:
				mContent = R.string.refresh_guides_content;
				break;
			case R.string.tips_title:
				mContent = R.string.tips_content;
				imageSrc = R.drawable.tips;
				break;
			case R.string.food_title:
				mContent = R.string.food_content;
				break;
			case R.string.outside_title:
				mContent = R.string.outsiede_content;
				imageSrc = R.drawable.goout;
				break;
			case R.string.yws_refresh_group_title:
				mContent = R.string.yws_refresh_group_content;
				break;
			case R.string.meet_yws_title:
				mContent = R.string.meet_yws_content;
				break;
			case R.string.close_to_yws_title:
				mContent = R.string.close_to_yws_content;
				break;
			default:
				throw new RuntimeException("no this content!");
		}

		Bundle arg = new Bundle();
		arg.putInt(Constants.CONTENT, mContent);
		Fragment article;
		if (imageSrc != 0) {
			article = new ImageWithTextArticleFragment();
			arg.putInt(Constants.IMAGE, imageSrc);
		} else {
			article = new TextArticleFragment();
		}

		article.setArguments(arg);
		getSupportFragmentManager()
				.beginTransaction()
					.replace(com.github.longkai.android.R.id.fragment_container, article)
				.commit();
		setTitle(mIndex);

		mDetector = new GestureDetectorCompat(this, new BackGestureListener(this));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem share = menu.findItem(R.id.share);
		ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
		provider.setShareIntent(Constants.makeAShare(this, getString(mIndex), getString(mContent)));
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
