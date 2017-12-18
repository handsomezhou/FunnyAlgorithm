package com.handsomezhou.funnyalgorithm.gridsplit.util;

import android.graphics.Rect;

import com.handsomezhou.funnyalgorithm.gridsplit.model.SplitGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2017/6/15.
 */

public class GridSplitUtil {

    /**
     * 分割格子
     *
     *
     *
     * @param gridWidth   格子宽度
     * @param gridHeight  格子高度
     * @param splitGridCount     分割格子数
     * @param minSplitGridWidth  最小分割格子宽度
     * @param minSplitGridHeight 最小分割格子高度
     * @return
     */
    public static List<Rect> splitGrid(int gridWidth,int gridHeight,int splitGridCount,int minSplitGridWidth,int minSplitGridHeight){
        List<Rect> splitGrids=new ArrayList<>();

        do{
            if((gridWidth<=0)||(gridHeight<=0)){
                break;
            }


            if(splitGridCount<=0){
                break;
            }

            if((minSplitGridWidth<=0)||(minSplitGridHeight<=0)){
                break;
            }

            int left=0;
            int top=0;
            int right=left+gridWidth;
            int bottom=top+gridHeight;
            Rect grid=new Rect(left,top,right,bottom);
            SplitGrid splitGrid=new SplitGrid(grid);

        }while (false);

        return splitGrids;
    }

    /**
     * 分割格子
     * @param splitGrid     分割格子
     * @param splitGridCount    分割格子数
     * @param minSplitGridWidth     最小分割格子宽度
     * @param minSplitGridHeight    最小分割格子高度
     */
    private static void  splitGrid(final SplitGrid splitGrid,int splitGridCount,int minSplitGridWidth,int minSplitGridHeight){
        do{
            if(null==splitGrid){
                break;
            }

            if(splitGridCount<=0){
                break;
            }

            if((minSplitGridWidth<=0)||(minSplitGridHeight<=0)){
                break;
            }


           int maxSplitGridCount=GridSplitUtil.getMaxSplitGridCount(splitGrid,minSplitGridWidth,minSplitGridHeight);
            if(maxSplitGridCount<splitGridCount){
                break;
            }

        }while (false);

    }

    /**
     * 获取最大的分割格子宽度数量
     * @param splitGrid
     * @param minSplitGridWidth 最小分割格子宽度
     * @return
     */
    private static int getMaxSplitGridWidthCount(final SplitGrid splitGrid,int minSplitGridWidth){
        int maxSplitGridWidthCount=0;

        do{
            if(null==splitGrid){
                break;
            }

            if(minSplitGridWidth<=0){
                break;
            }

            maxSplitGridWidthCount=splitGrid.getWidth()/minSplitGridWidth;
        }while (false);

        return maxSplitGridWidthCount;
    }

    /**
     * 获取最大的分割格子高度数量
     * @param splitGrid
     * @param minSplitGridHeight
     * @return
     */
    private static int getMaxSplitGridHeightCount(final SplitGrid splitGrid,int minSplitGridHeight){
        int maxSplitGridHeightCount=0;

        do{
            if(null==splitGrid){
                break;
            }

            if(minSplitGridHeight<=0){
                break;
            }

            maxSplitGridHeightCount=splitGrid.getHeight()/minSplitGridHeight;
        }while (false);

        return maxSplitGridHeightCount;
    }

    /**
     * 获取最大可分割的格子数量
     * @param splitGrid
     * @param minSplitGridWidth
     * @param minSplitGridHeight
     * @return
     */
    private static int getMaxSplitGridCount(final SplitGrid splitGrid,int minSplitGridWidth,int minSplitGridHeight){
        int maxSplitGridCount=0;

        do{
            if(null==splitGrid){
                break;
            }
            maxSplitGridCount=getMaxSplitGridWidthCount(splitGrid,minSplitGridWidth)*getMaxSplitGridHeightCount(splitGrid,minSplitGridHeight);
        }while (false);

        return maxSplitGridCount;
    }
}
