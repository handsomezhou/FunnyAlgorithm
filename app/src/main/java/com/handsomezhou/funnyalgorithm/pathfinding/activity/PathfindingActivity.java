package com.handsomezhou.funnyalgorithm.pathfinding.activity;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.handsomezhou.funnyalgorithm.activity.BaseSingleFragmentActivity;
import com.handsomezhou.funnyalgorithm.pathfinding.fragment.PathfindingFragment;
import com.handsomezhou.funnyalgorithm.util.ActivityUtil;

/**
 * Created by zhoujq on 2018/1/2.
 */

public class PathfindingActivity extends BaseSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PathfindingFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }

    public static void launch(Context context) {
        ActivityUtil.launch(context, PathfindingActivity.class);
        return;
    }
}
