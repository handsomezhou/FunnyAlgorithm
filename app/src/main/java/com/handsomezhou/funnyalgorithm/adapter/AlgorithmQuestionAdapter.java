package com.handsomezhou.funnyalgorithm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestion;

import java.util.List;

/**
 * Created by zhoujq on 2017/10/31.
 */

public class AlgorithmQuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private int mLayoutResId;
    private List<AlgorithmQuestion> mAlgorithmQuestions;
    private OnAlgorithmQuestionAdapter mOnAlgorithmQuestionAdapter;

    public interface OnAlgorithmQuestionAdapter{
        void onItemClick(AlgorithmQuestion algorithmQuestion);
        void onLongClick(AlgorithmQuestion algorithmQuestion);
    }

    public AlgorithmQuestionAdapter(Context context, int layoutResId, List<AlgorithmQuestion> algorithmQuestions) {
        mContext = context;
        mLayoutResId = layoutResId;
        mAlgorithmQuestions = algorithmQuestions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutResId,parent,false);
        AlgorithmQuestionViewHolder algorithmQuestionViewHolder =new AlgorithmQuestionViewHolder(view);
        return algorithmQuestionViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlgorithmQuestion algorithmQuestion=mAlgorithmQuestions.get(position);
        AlgorithmQuestionViewHolder algorithmQuestionViewHolder =(AlgorithmQuestionViewHolder)holder;
        if(null!= algorithmQuestionViewHolder){
            if(null!=algorithmQuestion){
                algorithmQuestionViewHolder.mQuestionTv.setText(algorithmQuestion.getQuestion());
            }
        }
    }

    @Override
    public int getItemCount() {
        return  (null!=mAlgorithmQuestions)?mAlgorithmQuestions.size():0;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public List<AlgorithmQuestion> getAlgorithmQuestions() {
        return mAlgorithmQuestions;
    }

    public void setAlgorithmQuestions(List<AlgorithmQuestion> algorithmQuestions) {
        mAlgorithmQuestions = algorithmQuestions;
    }

    public OnAlgorithmQuestionAdapter getOnAlgorithmQuestionAdapter() {
        return mOnAlgorithmQuestionAdapter;
    }

    public void setOnAlgorithmQuestionAdapter(OnAlgorithmQuestionAdapter onAlgorithmQuestionAdapter) {
        mOnAlgorithmQuestionAdapter = onAlgorithmQuestionAdapter;
    }

    public  class AlgorithmQuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView mQuestionTv;


        public AlgorithmQuestionViewHolder(View view){
            super(view);
            mQuestionTv=(TextView) view.findViewById(R.id.question_text_view);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=mOnAlgorithmQuestionAdapter){
                        AlgorithmQuestion algorithmQuestion=mAlgorithmQuestions.get(getAdapterPosition());
                        mOnAlgorithmQuestionAdapter.onItemClick(algorithmQuestion);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(null!=mOnAlgorithmQuestionAdapter){
                        AlgorithmQuestion algorithmQuestion=mAlgorithmQuestions.get(getAdapterPosition());
                        mOnAlgorithmQuestionAdapter.onLongClick(algorithmQuestion);
                    }
                    return true;
                }
            });
        }
    }
}
