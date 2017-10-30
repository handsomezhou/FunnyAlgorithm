package com.handsomezhou.funnyalgorithm.gridsplit.model;

import android.graphics.Rect;

/**
 * Created by handsomezhou on 2017/6/15.
 */

public class SplitGrid {
    private Rect grid;
    public SplitGrid left=null;
    public SplitGrid right=null;

    public SplitGrid() {
    }

    public SplitGrid(Rect grid) {
        this.grid = grid;
    }

    public Rect getGrid() {
        return grid;
    }

    public void setGrid(Rect grid) {
        this.grid = grid;
    }

    public int getWidth(){
        return (null==grid)?(0):(grid.right-grid.left);
    }

    public int getHeight(){
        return (null==grid)?(0):(grid.bottom-grid.top);
    }
}
