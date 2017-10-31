package com.handsomezhou.funnyalgorithm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.handsomezhou.funnyalgorithm.model.PartnerView;

import java.util.List;


/**
 * Created by zhoujq.
 */

public class PartnerViewPagerAdapter extends FragmentPagerAdapter {
	private List<PartnerView> mPartnerViews;
	public PartnerViewPagerAdapter(FragmentManager fm, List<PartnerView> partnerViews) {
		super(fm);
		mPartnerViews=partnerViews;
	}

	@Override
	public Fragment getItem(int pos) {
		PartnerView partnerViews=mPartnerViews.get(pos);
		return partnerViews.getFragment();
	}

	@Override
	public int getCount() {
		
		return mPartnerViews.size();
	}

}
