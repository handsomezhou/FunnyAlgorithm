package com.handsomezhou.funnyalgorithm.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handsomezhou.funnyalgorithm.R;

/**
 * Created by handsomezhou on 2017/6/14.
 */

public class MainFragment extends BaseFragment {
    @Override
    protected void initData() {
        setContext(getActivity());
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    protected void initListener() {

    }
}
