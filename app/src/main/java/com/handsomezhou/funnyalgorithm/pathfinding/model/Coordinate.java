package com.handsomezhou.funnyalgorithm.pathfinding.model;

import java.util.List;

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

    /**
     * 坐标列表中是否包含某坐标
     * @param coordinates
     * @param coordinate
     * @return
     */
    public static boolean contains(List<Coordinate> coordinates,Coordinate coordinate){
        boolean contain=false;
        do{
            if(null==coordinates||coordinates.size()<=0){
                break;
            }

            if(null==coordinate){
                contain=true;
                break;
            }

            int coordinatesSize=coordinates.size();
            for(int i=0; i<coordinatesSize;i++){
                boolean equal=Coordinate.equals(coordinates.get(i),coordinate);
                if(true==equal){
                    contain=true;
                    break;
                }

            }
        }while (false);
        return contain;
    }
}
