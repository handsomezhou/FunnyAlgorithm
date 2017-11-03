package com.handsomezhou.funnyalgorithm.model;

import java.io.Serializable;

/**
 * Created by zhoujq on 2017/11/3.
 */

public class AlgorithmQuestionDetailsParameter  implements Serializable {
    private long id;

    public AlgorithmQuestionDetailsParameter() {
    }

    public AlgorithmQuestionDetailsParameter(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
