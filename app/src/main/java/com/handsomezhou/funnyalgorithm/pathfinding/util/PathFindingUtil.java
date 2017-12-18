package com.handsomezhou.funnyalgorithm.pathfinding.util;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.handsomezhou.funnyalgorithm.pathfinding.model.Coordinate;
import com.handsomezhou.funnyalgorithm.pathfinding.model.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2017/12/18.
 * 寻路算法工具类
 */

public class PathFindingUtil {

    /**
     * 根据平面坐标系中的参数,得到一条从开始坐标开始,经历需要遍历的坐标(绕过所有不能够遍历的坐标,不超过最小最大坐标),最后到达结束坐标结束的一条路径.
     * @param minCoordinate   最小坐标
     * @param maxUpCoordinate   最大坐标
     * @param startCoordinate  开始坐标
     * @param endCoordinate     结束坐标
     * @param traverseCoordinates  能够遍历的坐标
     * @param notTraverseCoordinates 不能够遍历的坐标
     * @return
     */
    public static List<Coordinate> getPath(@NonNull Coordinate minCoordinate,@NonNull Coordinate maxUpCoordinate,@NonNull Coordinate startCoordinate,@NonNull Coordinate endCoordinate, List<Coordinate> traverseCoordinates, List<Coordinate> notTraverseCoordinates) {
        List<Coordinate> coordinateList = new ArrayList<>();
        //网格头指针
        Grid headGrid = new Grid();

        do {
            if (null == minCoordinate || null == maxUpCoordinate || null == startCoordinate || null == endCoordinate) {
                break;
            }


            //没有需要遍历的点,并且开始坐标等于结束坐标
            if (null == traverseCoordinates || traverseCoordinates.size() <= 0) {
                if (Coordinate.equals(startCoordinate, endCoordinate)) {
                    break;
                }
            }

            //根据最小坐标和最大坐标生成相应范围的网格
            initGrid(minCoordinate,maxUpCoordinate,headGrid);

        } while (false);

        return coordinateList;
    }

    private static void initGrid(@NonNull Coordinate minCoordinate,@NonNull Coordinate maxCoordinate,@NonNull Grid headGrid){
        Grid cur_row=headGrid;
        Grid cur_col=headGrid;

        Grid grid=null;
        Point point=null;

        int minCoordinateY=minCoordinate.y;
        int minCoordinateX=minCoordinate.x;
        int maxCoordinateY=maxCoordinate.y;
        int maxCoordinateX=maxCoordinate.x;

        int x=minCoordinateX;
        int y=minCoordinateY;

        for(y=minCoordinateY; y<=maxCoordinateY; y++){
            cur_row.down= new Grid(x,y);

            cur_row.down.up=cur_row;
            cur_row.down.down=null;
            if(y==minCoordinateY){
                cur_row.down.left=cur_col;
                cur_col.right=cur_row.down;
            }else{
                cur_row.down.left=null;
            }
            cur_row.down.right=null;

            cur_row=cur_row.down;

            cur_col=cur_row;
            for(x=minCoordinateX; x<=maxCoordinateX; x++){
                if(minCoordinateX!=x){
                    cur_col.right=new Grid(x,y);
                    cur_col.right.left=cur_col;
                    cur_col.right.right=null;
                    if(y==minCoordinateY){
                        cur_col.right.up=null;
                    }
                    cur_col.right.getPoint().x =x;
                    cur_col.right.getPoint().y=y;
                    cur_col=cur_col.right;
                }else{
                    cur_col.getPoint().x =x;
                    cur_col.getPoint().y=y;
                }

            }
        }

        Grid prev_row=headGrid.down;
        Grid prev_col=null;

        for(cur_row=prev_row.down;cur_row!=null;cur_row=cur_row.down){
            for(prev_col=prev_row.right,cur_col=cur_row.right;cur_col!=null; prev_col=prev_col.right,cur_col=cur_col.right){
                prev_col.down=cur_col;
                cur_col.up=prev_col;
                cur_col.down=null;
            }
            prev_row=cur_row;
        }

    }
}
