package edu.gxu.impression.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.longkai.android.util.ViewUtils;
import edu.gxu.impression.util.Constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class ImageWithTextArticleFragment extends Fragment {

	private String mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		mContent = arguments.getString(Constants.CONTENT);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.image_with_text, container, false);
		Bundle arguments = getArguments();
		ImageView image = (ImageView) view.findViewById(R.id.image);
		image.setImageResource(arguments.getInt(Constants.IMAGE));
		if (mContent != null) {
			ViewUtils.setTextViewText(view, android.R.id.content, mContent);
			return view;
		}
		int text = arguments.getInt(Constants.CONTENT);
		if (text != 0) {
			TextView tv = ViewUtils.setTextViewText(view, android.R.id.content, text);
			tv.setVisibility(View.VISIBLE);
		} else {
			view.findViewById(android.R.id.content).setVisibility(View.GONE);
		}
		return view;
	}
}
