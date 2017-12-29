package com.handsomezhou.funnyalgorithm.pathfinding.model;

/**
 * Created by handsomezhou on 2017/12/18.
 */

public class Coordinate {
    public int x;
    public int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * 计算两个坐标是否相等
     * @param src
     * @param dst
     * @return
     */
    public static boolean equals(final Coordinate src,final Coordinate dst){
        boolean equal=false;
        do{
            if((null==src)&&(null==dst)){
                equal=true;
                break;
            }

            if(null==src){
                break;
            }

            if(null==dst){
                break;
            }

            if((src.x==dst.x)&&(src.y==dst.y)){
                equal=true;
                break;
            }

        }while (false);

        return equal;
    }
}
