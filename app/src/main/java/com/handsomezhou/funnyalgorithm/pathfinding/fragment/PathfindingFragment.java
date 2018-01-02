package com.handsomezhou.funnyalgorithm.pathfinding.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.handsomezhou.funnyalgorithm.R;
import com.handsomezhou.funnyalgorithm.constant.Constant;
import com.handsomezhou.funnyalgorithm.fragment.BaseFragment;
import com.handsomezhou.funnyalgorithm.fragment.MoreFragment;
import com.handsomezhou.funnyalgorithm.pathfinding.model.Coordinate;
import com.handsomezhou.funnyalgorithm.pathfinding.util.PathFindingUtil;
import com.handsomezhou.funnyalgorithm.util.ActivityUtil;
import com.handsomezhou.funnyalgorithm.util.JsonUtil;
import com.handsomezhou.funnyalgorithm.util.LogUtil;
import com.handsomezhou.funnyalgorithm.view.NavigationBarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoujq on 2018/1/2.
 */

public class PathfindingFragment extends BaseFragment implements NavigationBarLayout.OnNavigationBarLayout{
    private static final String TAG ="PathfindingFragment";
    private NavigationBarLayout mNavigationBarLayout;
    private String mTitle;

    private TextView mPathfindingTv;
    private Button mGeneratePathBtn;

    private StringBuffer mPathfindingStringBuffer;
    @Override
    protected void initData() {
        setContext(getActivity());
        mTitle = getContext().getString(R.string.path_finding);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_pathfinding, container,false);

        mNavigationBarLayout = (NavigationBarLayout) view.findViewById(R.id.navigation_bar_layout);
        mNavigationBarLayout.setOnNavigationBarLayout(this);
        mNavigationBarLayout.setTitle(mTitle);

        mPathfindingTv=(TextView) view.findViewById(R.id.pathfinding_text_view);
        mGeneratePathBtn=(Button) view.findViewById(R.id.generate_path_btn);

        return view;
    }

    @Override
    protected void initListener() {
        mGeneratePathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePath();
                refreshView();
            }
        });

        generatePath();
        refreshView();
    }

    /*start: NavigationBarLayout.OnNavigationBarLayout*/

    @Override
    public void onBack() {
        ActivityUtil.back(getActivity());
    }
    /*end: NavigationBarLayout.OnNavigationBarLayout*/

    private void initPathfindingStringBuffer(){
        if(null==mPathfindingStringBuffer){
            mPathfindingStringBuffer=new StringBuffer();
        }else {
            mPathfindingStringBuffer.delete(0,mPathfindingStringBuffer.length());
        }
    }

    private void generatePath(){
        Coordinate minCoordinate=new Coordinate(0,0);
        Coordinate maxUpCoordinate=new Coordinate(10,10);
        Coordinate startCoordinate=new Coordinate(0,0);
        Coordinate endCoordinate=new Coordinate(0,0);
        List<Coordinate > traverseCoordinates=new ArrayList<>();
        // traverseCoordinates.add(new Coordinate(5,6));
        traverseCoordinates.add(new Coordinate(2,2));
        traverseCoordinates.add(new Coordinate(5,5));
        traverseCoordinates.add(new Coordinate(4,4));
        traverseCoordinates.add(new Coordinate(9,6));

        List< Coordinate > notTraverseCoordinates=new ArrayList<>();
        notTraverseCoordinates.add(new Coordinate(0,3));
        notTraverseCoordinates.add(new Coordinate(1,3));
        notTraverseCoordinates.add(new Coordinate(2,3));
        //notTraverseCoordinates.add(new Coordinate(3,3));
        notTraverseCoordinates.add(new Coordinate(4,3));
        notTraverseCoordinates.add(new Coordinate(5,3));
        notTraverseCoordinates.add(new Coordinate(6,3));
        notTraverseCoordinates.add(new Coordinate(7,3));
        notTraverseCoordinates.add(new Coordinate(8,3));
        notTraverseCoordinates.add(new Coordinate(9,3));
        notTraverseCoordinates.add(new Coordinate(10,3));

        initPathfindingStringBuffer();
        mPathfindingStringBuffer.append("最小坐标").append(getCoordinate(minCoordinate)).append(Constant.NEW_LINE);
        mPathfindingStringBuffer.append("最大坐标").append(getCoordinate(maxUpCoordinate)).append(Constant.NEW_LINE);
        mPathfindingStringBuffer.append("开始坐标").append(getCoordinate(startCoordinate)).append(Constant.NEW_LINE);
        mPathfindingStringBuffer.append("结束坐标").append(getCoordinate(endCoordinate)).append(Constant.NEW_LINE);
        mPathfindingStringBuffer.append("要遍历坐标").append(getCoordinateList(traverseCoordinates)).append(Constant.NEW_LINE);
        mPathfindingStringBuffer.append("不能遍历坐标").append(getCoordinateList(notTraverseCoordinates)).append(Constant.NEW_LINE).append(Constant.NEW_LINE);




        long startTime=System.currentTimeMillis();
        List<Coordinate> pathCoordinateList= PathFindingUtil.getPath(minCoordinate,maxUpCoordinate,startCoordinate,endCoordinate,traverseCoordinates,notTraverseCoordinates);
        long endTime=System.currentTimeMillis();
        if(pathCoordinateList.size()<=0){
            mPathfindingStringBuffer.append("无有效路径").append(getCoordinateList(pathCoordinateList)).append(Constant.NEW_LINE);
        }else {
            mPathfindingStringBuffer.append("生成路径").append(getCoordinateList(pathCoordinateList)).append(Constant.NEW_LINE);
        }
        mPathfindingStringBuffer.append("耗时").append("["+(endTime-startTime)+"]ms").append(Constant.NEW_LINE);

    }

    private void refreshView(){
        refreshPathfindingTv();
        return;
    }

    private void refreshPathfindingTv(){
        if(null!=mPathfindingStringBuffer) {
            mPathfindingTv.setText(mPathfindingStringBuffer.toString());
        }
        return;
    }

    private static String getCoordinate(Coordinate coordinate){
        StringBuffer coordinateSb=new StringBuffer();
        do{
            if(null==coordinate){
                break;
            }

            coordinateSb.append(Constant.LEFT_ROUND_BRACKETS).append(coordinate.getX()).append(Constant.COMMA).append(coordinate.getY()).append(Constant.RIGHT_ROUND_BRACKETS);

        }while (false);

        return coordinateSb.toString();
    }

    private static String getCoordinateList(List<Coordinate> coordinates){
        StringBuffer coordinateListSb=new StringBuffer();
        do{
            if(null==coordinates||coordinates.size()<=0){
                break;
            }

            for (Coordinate coordinate:coordinates) {
                coordinateListSb.append(getCoordinate(coordinate));
            }
        }while (false);
        return coordinateListSb.toString();
    }
}
