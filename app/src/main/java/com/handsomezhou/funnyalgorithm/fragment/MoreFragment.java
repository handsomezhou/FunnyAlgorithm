package com.handsomezhou.funnyalgorithm.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.util.ViewUtil;
import com.handsomezhou.funnyalgorithm.view.NavigationBarLayout;


public class MoreFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout {
	private static final String TAG = MoreFragment.class.getSimpleName();
	
	private NavigationBarLayout mNavigationBarLayout;
	private ImageView mShareIv;

	private View mSettingsView;
	private View mAboutView;
	private String mTitle;
	@Override
	public void onResume() {
	    super.onResume();
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	}
	
    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.more);
        
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_more, container,false);
        mNavigationBarLayout = (NavigationBarLayout) view
				.findViewById(R.id.navigation_bar_layout);
		mNavigationBarLayout.setOnNavigationBarLayout(this);
		mNavigationBarLayout.setTitle(mTitle);
		ViewUtil.hideView(mNavigationBarLayout.getImageBackBtn());

		mShareIv=(ImageView) view.findViewById(R.id.share_image_view);
		mSettingsView=view.findViewById(R.id.settings_layout);
		mAboutView=view.findViewById(R.id.about_layout);

        return view;
    }

    @Override
    protected void initListener() {
    	mShareIv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				share();
			}
		});

    	mSettingsView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//enterSettings();
				
			}
		});
    	
    	mAboutView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//enterAbout();
				
			}
		});


    }

  
	/* Start: OnNavigationBarLayout */
	@Override
	public void onBack() {
		

	}

	/* End: OnNavigationBarLayout */
	
	public void refreshFragment(){
        //ToastUtil.toastLengthLong(getContext(), TAG);
        return;
    }

	

	private void share(){
		//ShareActivity.launch(getContext());
		return;
	}
	
	
	

}
