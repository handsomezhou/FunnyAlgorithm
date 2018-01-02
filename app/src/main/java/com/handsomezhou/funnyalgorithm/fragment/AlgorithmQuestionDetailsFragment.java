package com.handsomezhou.funnyalgorithm.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.constant.AlgorithmQuestionsId;
import com.handsomezhou.funnyalgorithm.helper.AlgorithmQuestionHelper;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestion;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestionDetailsParameter;
import com.handsomezhou.funnyalgorithm.pathfinding.activity.PathfindingActivity;
import com.handsomezhou.funnyalgorithm.util.ActivityUtil;
import com.handsomezhou.funnyalgorithm.util.ToastUtil;
import com.handsomezhou.funnyalgorithm.util.ViewUtil;
import com.handsomezhou.funnyalgorithm.view.NavigationBarLayout;

/**
 * Created by zhoujq on 2017/11/3.
 */

public class AlgorithmQuestionDetailsFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout {
    public static final String EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER = "AlgorithmQuestionDetailsFragment.EXTRA_ALGORITHM_QUESTION_DETAILS_PARAMETER";

    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;

    private TextView mQuestionTv;
    private TextView mQuestionDescriptionTv;
    private TextView mReferenceSolutionTv;

    private AlgorithmQuestion mAlgorithmQuestion;
    private AlgorithmQuestionDetailsParameter mAlgorithmQuestionDetailsParameter;


    public static AlgorithmQuestionDetailsFragment newInstance(AlgorithmQuestionDetailsParameter algorithmQuestionDetailsParameter) {
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
            mAlgorithmQuestion = AlgorithmQuestionHelper.getInstance().get(mAlgorithmQuestionDetailsParameter.getId());
        } else {
            mAlgorithmQuestionDetailsParameter = new AlgorithmQuestionDetailsParameter();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView();
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.algorithm_question_details);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_algorithm_question_details, container, false);
        mNavigationBarLayout = (NavigationBarLayout) view
                .findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mNavigationBarLayout.setTitle(mTitle);

        mQuestionTv = (TextView) view.findViewById(R.id.question_text_view);
        mQuestionDescriptionTv = (TextView) view.findViewById(R.id.question_description_text_view);
        mReferenceSolutionTv = (TextView) view.findViewById(R.id.reference_solution_text_view);
        return view;
    }

    @Override
    protected void initListener() {
        mReferenceSolutionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReferenceSolution((int)mAlgorithmQuestion.getId());
            }
        });
    }

    /*start: NavigationBarLayout.OnNavigationBarLayout*/
    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /*end: NavigationBarLayout.OnNavigationBarLayout*/

    private void refreshView() {
        refreshQuestionTv();
        refreshQuestionDescriptionTv();
    }

    private void refreshQuestionTv() {
        if (null != mAlgorithmQuestion) {

            ViewUtil.showTextHighlightLink(mQuestionTv, mAlgorithmQuestion.getQuestion(), mAlgorithmQuestion.getQuestionSource());
        }
    }

    private void refreshQuestionDescriptionTv() {
        if (null != mAlgorithmQuestion) {
            mQuestionDescriptionTv.setText(mAlgorithmQuestion.getQuestionDescription());
        }
    }

    private void viewReferenceSolution(int id) {
        switch (id) {
            case AlgorithmQuestionsId.GRID_SPLIT:
                ToastUtil.toastLengthLong(getContext(),"id="+id);
                break;
            case AlgorithmQuestionsId.PATHFINDING:
                PathfindingActivity.launch(getContext());
                break;
            default:
                break;
        }
    }

}
