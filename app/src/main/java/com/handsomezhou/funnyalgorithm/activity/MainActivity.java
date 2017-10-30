package com.handsomezhou.funnyalgorithm.activity;

import android.support.v4.app.Fragment;

import com.handsomezhou.funnyalgorithm.fragment.MainFragment;

/**
 * Created by handsomezhou on 2017/6/14.
 */

public class MainActivity extends BaseSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }
}
