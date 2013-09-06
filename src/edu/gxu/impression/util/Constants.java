package edu.gxu.impression.util;

import android.content.Context;
import android.content.Intent;
import edu.gxu.impression.ui.R;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class Constants {

	public static final String IMAGE = "image";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";

	public static final String ITEMS = "items";

	public static final String RUN_TIMES = "run_times";

	public static final int MAX_RUN_TIEMS = 3;

	public static final String RUN_WELCOME_AGAIN = "run_welcome_again";

	public static Intent makeAShare(Context context, String subject, String content) {
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain"); // more apps available
		String ads = context.getString(R.string.ads);
		share.putExtra(Intent.EXTRA_SUBJECT, subject);
		share.putExtra(Intent.EXTRA_TEXT, "[" + subject + "]" + ", " + ads + " " + content);
		return share;
	}

}
