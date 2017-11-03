package com.handsomezhou.funnyalgorithm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.handsomezhou.funnyalgorithm.fragment.AlgorithmQuestionDetailsFragment;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestionDetailsParameter;

/**
 * Created by zhoujq on 2017/11/3.
 */

public class AlgorithmQuestionDetailsActivity extends BaseSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        AlgorithmQuestionDetailsParameter algorithmQuestionDetailsParameter=(AlgorithmQuestionDetailsParameter) getIntent().getSerializableExtra(AlgorithmQuestionDetailsFragment.EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER);

        return  AlgorithmQuestionDetailsFragment.newInstance(algorithmQuestionDetailsParameter);

    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        return false;
    }

    public static void launch(Activity activity,AlgorithmQuestionDetailsParameter algorithmQuestionDetailsParameter){
        if(null==activity||null==algorithmQuestionDetailsParameter){
            return;
        }

        Intent intent=new Intent(activity, AlgorithmQuestionDetailsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(AlgorithmQuestionDetailsFragment.EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER, algorithmQuestionDetailsParameter);
        intent.putExtras(bundle);

        activity.startActivity(intent);

        return;
    }
}
