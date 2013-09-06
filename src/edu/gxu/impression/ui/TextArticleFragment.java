package edu.gxu.impression.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.longkai.android.util.ViewUtils;
import edu.gxu.impression.util.Constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class TextArticleFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.article, container, false);
		ViewUtils.setTextViewText(view, android.R.id.content, getArguments().getInt(Constants.CONTENT));
		return view;
	}
}
