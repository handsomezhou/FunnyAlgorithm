package com.handsomezhou.funnyalgorithm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestionDetailsParameter;
import com.handsomezhou.funnyalgorithm.util.ActivityUtil;
import com.handsomezhou.funnyalgorithm.util.ToastUtil;
import com.handsomezhou.funnyalgorithm.view.NavigationBarLayout;

/**
 * Created by zhoujq on 2017/11/3.
 */

public class AlgorithmQuestionDetailsFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout{
    public static final String EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER = "AlgorithmQuestionDetailsFragment.EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER";

    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;
    private AlgorithmQuestionDetailsParameter mAlgorithmQuestionDetailsParameter;

    public static AlgorithmQuestionDetailsFragment newInstance( AlgorithmQuestionDetailsParameter algorithmQuestionDetailsParameter) {
        Bundle bundle = new Bundle();
        if (null != algorithmQuestionDetailsParameter) {
            bundle.putSerializable(EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER, algorithmQuestionDetailsParameter);
        } else {
            bundle.putSerializable(EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER, new AlgorithmQuestionDetailsParameter());
        }

        AlgorithmQuestionDetailsFragment fragment = new AlgorithmQuestionDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER)) {
            mAlgorithmQuestionDetailsParameter = (AlgorithmQuestionDetailsParameter) getArguments().getSerializable(EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER);
        } else {
            mAlgorithmQuestionDetailsParameter = new AlgorithmQuestionDetailsParameter();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(null!=mAlgorithmQuestionDetailsParameter){
            ToastUtil.toastLengthshort(getContext(),"id:"+mAlgorithmQuestionDetailsParameter.getId());
        }
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.algorithm_question_details);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_algorithm_question_details, container,false);
        mNavigationBarLayout = (NavigationBarLayout) view
                .findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mNavigationBarLayout.setTitle(mTitle);

        return view;
    }

    @Override
    protected void initListener() {

    }

    /*start: NavigationBarLayout.OnNavigationBarLayout*/
    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /*end: NavigationBarLayout.OnNavigationBarLayout*/
}
