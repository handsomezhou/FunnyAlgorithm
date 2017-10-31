package com.handsomezhou.funnyalgorithm.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handsomezhou.funnyalgorithm.Interface.OnTabChange;
import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.adapter.PartnerViewPagerAdapter;
import com.handsomezhou.funnyalgorithm.model.IconButtonData;
import com.handsomezhou.funnyalgorithm.model.IconButtonValue;
import com.handsomezhou.funnyalgorithm.model.PartnerView;
import com.handsomezhou.funnyalgorithm.view.BottomTabView;
import com.handsomezhou.funnyalgorithm.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2017/6/14.
 */

public class MainFragment extends BaseFragment implements OnTabChange{
    public enum BOTTOM_TAB_TAG{
        ALGORITHM_QUESTION,
        MORE,
    }
    private List<PartnerView> mMainPartnerViews;
    private CustomViewPager mCustomViewPager;
    private BottomTabView mBottomTabView;
    private PartnerViewPagerAdapter mPartnerViewPagerAdapter;

    private int mCurrentFragmentIndex;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment=mPartnerViewPagerAdapter.getItem(getCurrentFragmentIndex());
        if(null!=fragment){
            fragment.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        mMainPartnerViews=new ArrayList<PartnerView>();

		/*start: Algorithm  question*/
        PartnerView homePv=new PartnerView(BOTTOM_TAB_TAG.ALGORITHM_QUESTION, new AlgorithmQuestionFragment());
        mMainPartnerViews.add(homePv);
		/*end: Algorithm  question*/



		/*start: me*/
        PartnerView mePv=new PartnerView(BOTTOM_TAB_TAG.MORE, new MoreFragment());
        mMainPartnerViews.add(mePv);
		/*end: me*/

        setCurrentFragmentIndex(0);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mCustomViewPager=(CustomViewPager) view.findViewById(R.id.custom_view_pager);
        mCustomViewPager.setPagingEnabled(true);
        mCustomViewPager.setOffscreenPageLimit(mMainPartnerViews.size());

        mBottomTabView=(BottomTabView)view.findViewById(R.id.bottom_tab_view);
        mBottomTabView.removeAllViews();

      /*start: Algorithm  question*/
        IconButtonValue homeIbv=new IconButtonValue(BOTTOM_TAB_TAG.ALGORITHM_QUESTION,R.mipmap.algorithm_question_icon_selected_unfocused, R.mipmap.algorithm_question_icon_unselected, R.string.algorithm_question);
        IconButtonData homeIbd=new IconButtonData(getContext(), homeIbv);
        mBottomTabView.addIconButtonData(homeIbd);
		/*end:Algorithm  question*/

        /*start:me*/
        IconButtonValue meIbv=new IconButtonValue(BOTTOM_TAB_TAG.MORE,R.mipmap.more_icon_selected_unfocused, R.mipmap.more_icon_unselected, R.string.more);
        IconButtonData meIbd=new IconButtonData(getContext(), meIbv);
        mBottomTabView.addIconButtonData(meIbd);
		/*end:me*/

        mBottomTabView.setOnTabChange(this);
        return view;
    }

    @Override
    protected void initListener() {
        FragmentManager fm = getChildFragmentManager();
        mPartnerViewPagerAdapter=new PartnerViewPagerAdapter(fm, mMainPartnerViews);
        mCustomViewPager.setAdapter(mPartnerViewPagerAdapter);

        mCustomViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {

                PartnerView partnerView=mMainPartnerViews.get(pos);
                //Toast.makeText(getContext(),addressBookView.getTag().toString()+"+++" , Toast.LENGTH_LONG).show();
                mBottomTabView.setCurrentTabItem(partnerView.getTag());
                setCurrentFragmentIndex(pos);
                refreshFragment(pos);
            }

            @Override
            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /*start: OnTabChange*/
    @Override
    public void onChangeToTab(Object fromTab, Object toTab, TAB_CHANGE_STATE tabChangeState) {
        int item=getPartnerViewItem(mMainPartnerViews,toTab);
        mCustomViewPager.setCurrentItem(item);
    }

    @Override
    public void onClickTab(Object currentTab, TAB_CHANGE_STATE tabChangeState) {

    }
    /*end: OnTabChange*/

    public int getCurrentFragmentIndex() {
        return mCurrentFragmentIndex;
    }

    public void setCurrentFragmentIndex(int currentFragmentIndex) {
        mCurrentFragmentIndex = currentFragmentIndex;
    }

    public static int getPartnerViewItem(List<PartnerView> partnerViews,Object tag){
        int item=0;;
        do{
            if((null==partnerViews)||(null==tag)){
                break;
            }

            for(int i=0; i<partnerViews.size();i++){
                if(partnerViews.get(i).getTag().equals(tag)){
                    item=i;
                    break;
                }
            }
        }while(false);

        return item;
    }
    private void refreshFragment(int pageSelectedPos){
        if(pageSelectedPos>=0&&pageSelectedPos<mMainPartnerViews.size()){
            Fragment fragment=mPartnerViewPagerAdapter.getItem(pageSelectedPos);
            if(null!=fragment){
                if (fragment instanceof AlgorithmQuestionFragment) {
                    ((AlgorithmQuestionFragment) fragment).refreshFragment();
                }else if(fragment instanceof MoreFragment){
                    ((MoreFragment) fragment).refreshFragment();
                }
            }
        }
    }
}
