package com.handsomezhou.funnyalgorithm.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handsomezhou.funnyalgorithm.activity.AlgorithmQuestionDetailsActivity;
import com.handsomezhou.funnyalgorithm.helper.AlgorithmQuestionHelper;
import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.adapter.AlgorithmQuestionAdapter;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestion;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestionDetailsParameter;
import com.handsomezhou.funnyalgorithm.util.ToastUtil;
import com.handsomezhou.funnyalgorithm.util.ViewUtil;
import com.handsomezhou.funnyalgorithm.view.RecycleViewDivider;

/**
 * Created by zhoujq on 2017/10/31.
 */

public class AlgorithmQuestionFragment extends BaseFragment implements AlgorithmQuestionAdapter.OnAlgorithmQuestionAdapter{

    //private List<AlgorithmQuestion> mAlgorithmQuestions;
    private RecyclerView mAlgorithmQuestionRv;
    private TextView mNoDataTipsTv;
    private AlgorithmQuestionAdapter mAlgorithmQuestionAdapter;

    @Override
    public void onResume() {
        super.onResume();
        refreshRv();
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        initAlgorithmQuestions();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_algorithm_question, container, false);
        mAlgorithmQuestionRv=(RecyclerView) view.findViewById(R.id.algorithm_question_recycler_view);
        mNoDataTipsTv=(TextView) view.findViewById(R.id.no_data_tips_text_view);

        mAlgorithmQuestionRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAlgorithmQuestionRv.setItemAnimator(new DefaultItemAnimator());
        mAlgorithmQuestionRv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, getContext().getResources().getInteger(R.integer.horizontal_divider_height),getContext().getResources().getColor(R.color.color_horizontal_divider)));
        mAlgorithmQuestionAdapter=new AlgorithmQuestionAdapter(getContext(),R.layout.list_item_algorithm_question, AlgorithmQuestionHelper.getInstance().getAlgorithmQuestions());
        mAlgorithmQuestionAdapter.setOnAlgorithmQuestionAdapter(this);
        mAlgorithmQuestionRv.setAdapter(mAlgorithmQuestionAdapter);

        return view;
    }

    @Override
    protected void initListener() {

    }

    /*start: AlgorithmQuestionAdapter.OnAlgorithmQuestionAdapter*/

    @Override
    public void onItemClick(AlgorithmQuestion algorithmQuestion) {

        do{
            if(null==algorithmQuestion){
                break;
            }

            AlgorithmQuestionDetailsParameter algorithmQuestionDetailsParameter=new AlgorithmQuestionDetailsParameter();
            algorithmQuestionDetailsParameter.setId(algorithmQuestion.getId());
            AlgorithmQuestionDetailsActivity.launch(getActivity(),algorithmQuestionDetailsParameter);

        }while (false);

    }

    @Override
    public void onLongClick(AlgorithmQuestion algorithmQuestion) {

        do{
            if(null==algorithmQuestion){
                break;
            }

            //ToastUtil.toastLengthshort(getContext(),algorithmQuestion.getQuestion());
        }while (false);

    }
    /*end: AlgorithmQuestionAdapter.OnAlgorithmQuestionAdapter*/


    public void refreshFragment(){
        //ToastUtil.toastLengthLong(getContext(), TAG);
        return;
    }

    private void initAlgorithmQuestions(){
        if(AlgorithmQuestionHelper.getInstance().getAlgorithmQuestions().size()<=0) {
            AlgorithmQuestionHelper.getInstance().loadAllModules(getContext());
        }
    }

    private void refreshRv(){
        if (null == mAlgorithmQuestionRv) {
            return;
        }

        if (null != mAlgorithmQuestionAdapter) {
            mAlgorithmQuestionAdapter.notifyDataSetChanged();
            if (mAlgorithmQuestionAdapter.getItemCount()> 0) {
                ViewUtil.showView(mAlgorithmQuestionRv);
                ViewUtil.hideView(mNoDataTipsTv);

            } else {
                ViewUtil.hideView(mAlgorithmQuestionRv);
                ViewUtil.showView(mNoDataTipsTv);
            }
        }
        return;
    }
}
