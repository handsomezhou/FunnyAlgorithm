package com.handsomezhou.funnyalgorithm.pathfinding.model;

import android.graphics.Point;

import com.handsomezhou.funnyalgorithm.pathfinding.constant.GridType;

import java.util.Comparator;

/**
 * Created by zhoujq on 2017/6/9.
 */

public class Grid {
    private int mId=-1;
    private Coordinate mCoordinate;//坐标
    int mGridType = GridType.NONE;//标签
    private String mTag;
    /*start: A star*/
    //https://zh.wikipedia.org/wiki/A*%E6%90%9C%E5%AF%BB%E7%AE%97%E6%B3%95
    private  int mGScore=0;
    private  int mHScore=0;
    private  int mFScore=0;
    public Grid comeFrom=null;
    /*end: A star*/
    public Grid up=null;
    public Grid right=null;
    public Grid down=null;
    public Grid left=null;

    public Grid() {
        mCoordinate=new Coordinate(-1,-1);
        resetAStarValue();
    }

    public Grid(int x, int y){
        mCoordinate=new Coordinate(x,y);
        resetAStarValue();
    }

    public Grid(Coordinate coordinate) {
        mCoordinate = coordinate;
        resetAStarValue();
    }


    public static Comparator<Grid> mSortByFScoreAsc = new Comparator<Grid>() {

        @Override
        public int compare(Grid lhs, Grid rhs) {
            if((null==lhs)||(null==rhs)){
                return 0;
            }

            int compareValue=lhs.getFScore()-rhs.getFScore();

            return compareValue;

        }
    };

    public static Comparator<Grid> mSortByFScoreDes = new Comparator<Grid>() {

        @Override
        public int compare(Grid lhs, Grid rhs) {
            if((null==lhs)||(null==rhs)){
                return 0;
            }

            int compareValue=rhs.getFScore()-lhs.getFScore();

            return compareValue;

        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Coordinate getCoordinate() {
        return mCoordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        mCoordinate = coordinate;
    }

    public int getGridType() {
        return mGridType;
    }

    public void setGridType(int gridType) {
        mGridType = gridType;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public Grid getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(Grid comeFrom) {
        this.comeFrom = comeFrom;
    }

    public int getFScore() {
        return mFScore;
    }

    public void setFScore(int FScore) {
        mFScore = FScore;
    }

    public int getGScore() {
        return mGScore;
    }

    public void setGScore(int GScore) {
        mGScore = GScore;
    }

    public int getHScore() {
        return mHScore;
    }

    public void setHScore(int HScore) {
        mHScore = HScore;
    }

    public void resetAStarValue(){
        setGScore(0);
        setHScore(0);
        setFScore(0);
        comeFrom=null;
    }

    public static boolean validGrid(final Grid grid){
        boolean valid=false;

        do{
            if(null==grid){
                break;
            }

            valid=(grid.getCoordinate().x>=0)&&(grid.getCoordinate().y>=0);
        }while (false);

        return valid;
    }
}
