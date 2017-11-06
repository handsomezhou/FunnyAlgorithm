package com.handsomezhou.funnyalgorithm.helper;

import android.content.Context;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.model.AlgorithmQuestion;
import com.handsomezhou.funnyalgorithm.util.JsonUtil;
import com.handsomezhou.funnyalgorithm.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujq on 2017/10/31.
 */

public class AlgorithmQuestionHelper {
    private static final String TAG="AlgorithmQuestionHelper";
    private static AlgorithmQuestionHelper mInstance;
    private List<AlgorithmQuestion> mAlgorithmQuestions;


    public static AlgorithmQuestionHelper getInstance() {
        if (null == mInstance) {
            mInstance = new AlgorithmQuestionHelper();
        }

        return mInstance;
    }

    private AlgorithmQuestionHelper(){
        initModuleHelper();
    }

    private void initModuleHelper(){
        initAlgorithmQuestions();
    }

    private void initAlgorithmQuestions(){
        if(null==mAlgorithmQuestions){
            mAlgorithmQuestions=new ArrayList<>();
        }else{
            mAlgorithmQuestions.clear();
        }
    }

    public List<AlgorithmQuestion> getAlgorithmQuestions() {
        return mAlgorithmQuestions;
    }

    public void setAlgorithmQuestions(List<AlgorithmQuestion> algorithmQuestions) {
        mAlgorithmQuestions = algorithmQuestions;
    }

    public void loadAllModules(Context context){
        do{
            if(null==context){
                break;
            }

            initAlgorithmQuestions();
            String[] algorithmQuestionItems =context.getResources().getStringArray(R.array.algorithm_questions);
            for(String item: algorithmQuestionItems){
                LogUtil.i(TAG,item);
                AlgorithmQuestion algorithmQuestion= JsonUtil.fromJson(item,AlgorithmQuestion.class);
                if(null!=algorithmQuestion) {
                    mAlgorithmQuestions.add(algorithmQuestion);
                }
            }
        }while (false);

        return;
    }

    public AlgorithmQuestion get(long id){
        AlgorithmQuestion algorithmQuestion=null;

        do{
            if(null==mAlgorithmQuestions||mAlgorithmQuestions.size()<=0){
                break;
            }

            for (AlgorithmQuestion aq:mAlgorithmQuestions) {
                if(aq.getId()==id){
                    algorithmQuestion=aq;
                    break;
                }
            }
        }while (false);

        return algorithmQuestion;
    }

}
