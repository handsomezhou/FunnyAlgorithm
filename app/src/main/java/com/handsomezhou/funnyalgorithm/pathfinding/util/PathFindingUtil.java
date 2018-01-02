package com.handsomezhou.funnyalgorithm.pathfinding.util;

import android.support.annotation.NonNull;

import com.handsomezhou.funnyalgorithm.pathfinding.constant.GridType;
import com.handsomezhou.funnyalgorithm.pathfinding.model.Coordinate;
import com.handsomezhou.funnyalgorithm.pathfinding.model.Grid;
import com.handsomezhou.funnyalgorithm.util.JsonUtil;
import com.handsomezhou.funnyalgorithm.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by handsomezhou on 2017/12/18.
 * 寻路算法工具类
 */

public class PathFindingUtil {
    private static final String TAG = "PathFindingUtil";


    /**
     * 根据平面坐标系中的参数,得到一条从开始坐标开始,经历需要遍历的坐标列表(绕过所有不能够遍历的坐标列表,不超过最小、最大坐标),最后到达结束坐标结束的一条路径.
     *
     * @param minCoordinate          最小坐标
     * @param maxUpCoordinate        最大坐标
     * @param startCoordinate        开始坐标
     * @param endCoordinate          结束坐标
     * @param traverseCoordinates    能够遍历的坐标
     * @param notTraverseCoordinates 不能够遍历的坐标
     * @return
     */
    public static List<Coordinate> getPath(@NonNull Coordinate minCoordinate, @NonNull Coordinate maxUpCoordinate, @NonNull Coordinate startCoordinate, @NonNull Coordinate endCoordinate, List<Coordinate> traverseCoordinates, List<Coordinate> notTraverseCoordinates) {
        List<Coordinate> pathCoordinateList = new ArrayList<>();
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
            initGrid(minCoordinate, maxUpCoordinate, headGrid);
            //初始化要遍历的点坐标列表
            initTraverseCoordinates(headGrid, traverseCoordinates);
            //初始化不能够遍历的坐标列表
            initNotTraverseCoordinates(headGrid, notTraverseCoordinates);

            //打印日志
            //log(headGrid);
            //生成路径
            generatePath(headGrid,startCoordinate,endCoordinate,traverseCoordinates,pathCoordinateList);
            boolean valid=validPath(pathCoordinateList,startCoordinate,endCoordinate,traverseCoordinates);
            if(false==valid){
                pathCoordinateList.clear();
            }
        } while (false);

        return pathCoordinateList;
    }


    /**
     *  生成路径
     * @param headGrid  网格头节点
     * @param startCoordinate 开始坐标
     * @param endCoordinate    结束坐标
     * @param traverseCoordinates 能够遍历的坐标
     * @param coordinateList
     */
    private static void generatePath( @NonNull Grid headGrid,@NonNull Coordinate startCoordinate, @NonNull Coordinate endCoordinate, List<Coordinate> traverseCoordinates,List<Coordinate> coordinateList){

        do{

            if (null == headGrid) {
                break;
            }

            if ( null == startCoordinate || null == endCoordinate) {
                break;
            }

            if(null==coordinateList){
                break;
            }

            //没有需要遍历的点
            if (null == traverseCoordinates || traverseCoordinates.size() <= 0) {
                if (Coordinate.equals(startCoordinate, endCoordinate)) {
                    break;
                }else {
                    coordinateList.addAll(PathFindingUtil.aStar(headGrid,startCoordinate,endCoordinate));
                    break;
                }
            }

            int traverseCoordinatesSize=traverseCoordinates.size();
            Grid grid=null;
            for(int i=0; i<=traverseCoordinatesSize; i++){
               // LogUtil.i(TAG,i+"start-----------------------------------------");
                if(0==i){
                    coordinateList.addAll(PathFindingUtil.aStar(headGrid,startCoordinate,traverseCoordinates.get(i)));
                    //移除最后一个坐标,因为在下一点路径规划中将会以此坐标开始
                    coordinateList.remove(coordinateList.size()-1);
                    grid=get(headGrid,traverseCoordinates.get(i));
                }else if((traverseCoordinatesSize)==i){
                    coordinateList.addAll(PathFindingUtil.aStar(headGrid,traverseCoordinates.get(i-1),endCoordinate));
                    grid=get(headGrid,traverseCoordinates.get(i-1));
                }else {
                    coordinateList.addAll(PathFindingUtil.aStar(headGrid,traverseCoordinates.get(i-1),traverseCoordinates.get(i)));
                    //移除最后一个坐标,因为在下一点路径规划中将会以此坐标开始
                    coordinateList.remove(coordinateList.size()-1);
                    grid=get(headGrid,traverseCoordinates.get(i));
                }

                if(null!=grid){
                    grid.setGridType(GridType.NONE);
                }
                //log(headGrid);
                //LogUtil.i(TAG,i+"end-----------------------------------------");
            }

        }while (false);

        return;
    }


    /**
     * 是否有效路径
     * @param pathCoordinateList 路径坐标列表
     * @param startCoordinate    开始坐标
     * @param endCoordinate      结束坐标
     * @param traverseCoordinates 能够遍历的坐标
     * @return
     */
    private static boolean validPath(final List<Coordinate> pathCoordinateList, @NonNull Coordinate startCoordinate, @NonNull Coordinate endCoordinate, List<Coordinate> traverseCoordinates){
        boolean valid=true;
        do{
            if(false==Coordinate.contains(pathCoordinateList,startCoordinate)){
                valid=false;
                break;
            }

            if(false==Coordinate.contains(pathCoordinateList,endCoordinate)){
                valid=false;
                break;
            }

            for (Coordinate coordinate:traverseCoordinates) {
                boolean contain=Coordinate.contains(pathCoordinateList,coordinate);
                if(false==contain){
                    valid=false;
                    break;
                }
            }
        }while (false);

        return valid;
    }

    /**
     * 初始化网格
     *
     * @param minCoordinate 最小坐标
     * @param maxCoordinate 最大坐标
     * @param headGrid      网格头节点
     */
    private static void initGrid(@NonNull Coordinate minCoordinate, @NonNull Coordinate maxCoordinate, @NonNull Grid headGrid) {
        Grid cur_row = headGrid;
        Grid cur_col = headGrid;

        Grid grid = null;
        Coordinate point = null;

        int minCoordinateY = minCoordinate.y;
        int minCoordinateX = minCoordinate.x;
        int maxCoordinateY = maxCoordinate.y;
        int maxCoordinateX = maxCoordinate.x;

        int x = minCoordinateX;
        int y = minCoordinateY;

        for (y = minCoordinateY; y <= maxCoordinateY; y++) {
            cur_row.down = new Grid(x, y);

            cur_row.down.up = cur_row;
            cur_row.down.down = null;
            if (y == minCoordinateY) {
                cur_row.down.left = cur_col;
                cur_col.right = cur_row.down;
            } else {
                cur_row.down.left = null;
            }
            cur_row.down.right = null;

            cur_row = cur_row.down;

            cur_col = cur_row;
            for (x = minCoordinateX; x <= maxCoordinateX; x++) {
                if (minCoordinateX != x) {
                    cur_col.right = new Grid(x, y);
                    cur_col.right.left = cur_col;
                    cur_col.right.right = null;
                    if (y == minCoordinateY) {
                        cur_col.right.up = null;
                    }
                    cur_col.right.getCoordinate().x = x;
                    cur_col.right.getCoordinate().y = y;
                    cur_col = cur_col.right;
                } else {
                    cur_col.getCoordinate().x = x;
                    cur_col.getCoordinate().y = y;
                }

            }
        }

        Grid prev_row = headGrid.down;
        Grid prev_col = null;

        for (cur_row = prev_row.down; cur_row != null; cur_row = cur_row.down) {
            for (prev_col = prev_row.right, cur_col = cur_row.right; cur_col != null; prev_col = prev_col.right, cur_col = cur_col.right) {
                prev_col.down = cur_col;
                cur_col.up = prev_col;
                cur_col.down = null;
            }
            prev_row = cur_row;
        }

    }

    /**
     * @param headGrid            网格头节点
     * @param traverseCoordinates 需要遍历的点坐标列表
     */
    private static void initTraverseCoordinates(@NonNull Grid headGrid, List<Coordinate> traverseCoordinates) {

        do {
            if (null == headGrid) {
                break;
            }

            if (null == traverseCoordinates || traverseCoordinates.size() <= 0) {
                break;
            }

            Grid grid = null;
            for (Coordinate coordinate : traverseCoordinates) {
                grid = PathFindingUtil.get(headGrid, coordinate);
                if (null != grid) {
                    grid.setGridType(GridType.FOOD);
                }

            }


        } while (false);


    }


    /**
     *
     * @param headGrid  网格头节点
     * @param notTraverseCoordinates 不能够遍历的坐标
     */
    private static void initNotTraverseCoordinates(@NonNull Grid headGrid, List<Coordinate> notTraverseCoordinates) {
        do {
            if (null == headGrid) {
                break;
            }

            if (null == notTraverseCoordinates || notTraverseCoordinates.size() <= 0) {
                break;
            }

            Grid grid = null;
            for (Coordinate coordinate : notTraverseCoordinates) {
                grid = PathFindingUtil.get(headGrid, coordinate);
                if (null != grid) {
                    grid.setGridType(GridType.OBSTACLE);
                }

            }


        } while (false);
    }

    /**
     * 根据坐标点获取其在网格中的位置索引
     *
     * @param headGrid
     * @param coordinate
     * @return
     */
    private static Grid get(final Grid headGrid, Coordinate coordinate) {
        Grid grd = null;

        do {
            if (null == headGrid) {
                break;
            }

            if (null == coordinate) {
                break;
            }

            Grid cur_row = null;
            Grid cur_col = null;
            boolean getSuccess = false;
            for (cur_row = headGrid.down; cur_row != null; cur_row = cur_row.down) {
                for (cur_col = cur_row; cur_col != null; cur_col = cur_col.right) {
                    if (Coordinate.equals(cur_col.getCoordinate(), coordinate)) {
                        grd = cur_col;
                        getSuccess = true;
                        break;
                    }
                }
                if (true == getSuccess) {
                    break;
                }
            }
        } while (false);

        return grd;
    }


    /**
     * 打印日志
     * @param headGrid
     * @return
     */
    private static Grid log(final Grid headGrid) {
        Grid grd = null;

        do {
            if (null == headGrid) {
                break;
            }



            Grid cur_row = null;
            Grid cur_col = null;
            boolean getSuccess = false;
            for (cur_row = headGrid.down; cur_row != null; cur_row = cur_row.down) {
                for (cur_col = cur_row; cur_col != null; cur_col = cur_col.right) {
                    LogUtil.i(TAG, JsonUtil.toJson(cur_col.getCoordinate())+" GridType:"+cur_col.getGridType());
                }

            }
        } while (false);

        return grd;
    }

    /*start: A star*/
    /**
     * Reference:
     * https://en.wikipedia.org/wiki/A*_search_algorithm
     * https://zh.wikipedia.org/wiki/A*%E6%90%9C%E5%AF%BB%E7%AE%97%E6%B3%95
     * http://theory.stanford.edu/~amitp/GameProgramming/
     * http://blog.csdn.net/changbaohua/article/details/3860307
     * http://www.cnblogs.com/kanego/archive/2011/08/30/2159070.html
     * https://wapbaike.baidu.com/item/A*%E7%AE%97%E6%B3%95?adapt=1
     * */

    private static List<Coordinate> aStar(final Grid headGrid,Coordinate start,Coordinate goal){
        List<Coordinate> paths=new ArrayList<>();

        do{
            if(null==headGrid){
                break;
            }


            if(null==start){
                break;
            }

            if(null==goal){
                break;
            }

            //https://zh.wikipedia.org/wiki/A*%E6%90%9C%E5%AF%BB%E7%AE%97%E6%B3%95
            List<Grid> closeGrids=new ArrayList<>();
            List<Grid> openGrids=new ArrayList<>();

            Grid startGrid=PathFindingUtil.get(headGrid,start);
            if(null==startGrid){
                break;
            }
            openGrids.add(startGrid);
            startGrid.setGScore(0);
            startGrid.setHScore(getHeuristicEstimateOfDistance(start,goal));
            startGrid.setFScore(startGrid.getGScore()+startGrid.getHScore());
            Grid minFScoreGrid=null;
            List<Grid> neighborGrids=null;
            int tentativeGScore=0;//暂定G score
            boolean tentativeIsBetter=false;//暂时判断为更好
            while (openGrids.size()>0){
                minFScoreGrid=PathFindingUtil.getMinFScore(openGrids);
                if(null==minFScoreGrid){
                    break;
                }

                if(true==Coordinate.equals(minFScoreGrid.getCoordinate(),goal)){
                    paths.addAll(reconstructPath(PathFindingUtil.get(headGrid,goal)));
                    PathFindingUtil.resetAStar(headGrid);
                    break;
                }

                openGrids.remove(minFScoreGrid);
                closeGrids.add(minFScoreGrid);
                neighborGrids=PathFindingUtil.getNeighborGrid(minFScoreGrid);
                for(Grid grd:neighborGrids){
                    if(PathFindingUtil.inGrids(closeGrids,grd)){
                        continue;
                    }

                    tentativeGScore=minFScoreGrid.getGScore()+PathFindingUtil.getDistBetween(minFScoreGrid.getCoordinate(),grd.getCoordinate());
                    if (false==PathFindingUtil.inGrids(openGrids,grd)){
                        openGrids.add(grd);
                        tentativeIsBetter= true;
                    }else if(tentativeGScore<grd.getGScore()){
                        tentativeIsBetter= true;
                    }else{
                        tentativeIsBetter= false;
                    }

                    if(true==tentativeIsBetter){
                        grd.comeFrom=minFScoreGrid;
                        grd.setGScore(tentativeGScore);
                        grd.setHScore(PathFindingUtil.getHeuristicEstimateOfDistance(grd.getCoordinate(),goal));
                        grd.setFScore(grd.getGScore()+grd.getHScore());
                    }

                }

            }
        }while (false);

        return paths;
    }

    private static void resetAStar(final Grid headGrid){

        do{
            if(null==headGrid){
                break;
            }

            Grid cur_row=null;
            Grid cur_col=null;
            boolean getSuccess=false;
            for(cur_row=headGrid.down;cur_row!=null; cur_row=cur_row.down) {
                for (cur_col = cur_row; cur_col != null; cur_col = cur_col.right) {
                    cur_col.resetAStarValue();
                }
            }
        }while (false);

        return ;
    }

    private static Grid getMinFScore(final List<Grid> grids){
        Grid grid=null;
        do{
            if(null==grids||grids.size()<=0){
                break;
            }

            Collections.sort(grids,Grid.mSortByFScoreAsc);
            grid=grids.get(0);

        }while (false);
        return grid;
    }

    private static List<Coordinate> reconstructPath(Grid goalGrid){
        List<Coordinate> paths =new ArrayList<>();

        do{
            if(null==goalGrid){
                break;
            }


            Grid curGrid=null;
            for(curGrid=goalGrid;null!=curGrid;curGrid=curGrid.comeFrom){
                paths.add(0,curGrid.getCoordinate());
            }

        }while (false);

        return paths;
    }

    private static List<Grid> getNeighborGrid(Grid grid){
        List<Grid> neighborGrids=new ArrayList<>();
        do{

            if(null==grid){
                break;
            }

            if(null!=grid.up){
                if(true==Grid.validGrid(grid.up)) {
                    if(grid.getGridType()==GridType.NONE) {
                            neighborGrids.add(grid.up);
                    }
                }
            }

            if(null!=grid.left){
                if(true==Grid.validGrid(grid.left)) {
                    if(grid.getGridType()==GridType.NONE) {
                            neighborGrids.add(grid.left);
                    }
                }
            }

            if(null!=grid.right){
                if(true==Grid.validGrid(grid.right)) {
                    if(grid.getGridType()==GridType.NONE) {
                            neighborGrids.add(grid.right);
                    }
                }
            }

            if(null!=grid.down){
                if(true==Grid.validGrid(grid.down)) {
                    if(grid.getGridType()==GridType.NONE) {
                            neighborGrids.add(grid.down);

                    }
                }
            }

        }while (false);

        return neighborGrids;
    }

    /**
     * grid 是否在grids中
     * @param grids
     * @param grid
     * @return
     */
    private static boolean inGrids(final List<Grid> grids,Grid grid){
        boolean in=false;
        do{
            if(null==grids){
                break;
            }

            if(null==grid){
                break;
            }

            for(Grid grd:grids){
                if(true==Coordinate.equals(grid.getCoordinate(),grd.getCoordinate())){
                    in=true;
                    break;
                }
            }
        }while (false);
        return in;
    }

  /*  private static void remove(final List<Grid> grids,Grid grid){
        do{
            if(null==grids){
                break;
            }

            if(null==grid){
                break;
            }

            for(int i=0; i<grids.size(); i++){

            }
        }while (false);

        return;
    }

    private static void add(final List<Grid> grids,Grid grid){
        do{
            if(null==grids){
                break;
            }

            if(null==grid){
                break;
            }

            grids.add(grid);
        }while (false);

        return;
    }
*/

    /**
     * 获取曼哈顿距离
     * @param from
     * @param to
     * @return
     */
    private static int getManhattanDistance(Coordinate from,Coordinate to){
        int manhattanDistance=0;
        do{
            if(null==from){
                break;
            }

            if(null==to){
                break;
            }

            manhattanDistance=Math.abs(from.x-to.x)+Math.abs(from.y-to.y);
        }while (false);

        return manhattanDistance;
    }

    /**
     * 获取启发式估计距离
     * @param from
     * @param to
     * @return
     */
    private static int getHeuristicEstimateOfDistance(Coordinate from,Coordinate to){
        return getManhattanDistance(from,to);
    }

    /**
     * 获取两点之间距离
     * @param from
     * @param to
     * @return
     */
    private static int getDistBetween(Coordinate from,Coordinate to){
        return getManhattanDistance(from,to);
    }
     /*end: A star*/
}
