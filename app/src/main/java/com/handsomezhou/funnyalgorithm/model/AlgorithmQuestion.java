package com.handsomezhou.funnyalgorithm.model;

/**
 * Created by zhoujq on 2017/10/31.
 */

public class AlgorithmQuestion {

    /**
     * id : 1
     * question : 一个格子摆放的随机排序的算法？
     * questionDescription : 已知外部的大格子的总面积、长、宽是固定，其中分割的格子数也是固定的13个（不一定是13个，这个只是举例，但是随机前知道多少格）。现在我需求是想用某个算法，通过程序去做一个随机，让13个格子的位置随机变化（13个格子的面积、长、宽需要有限制，如面积不小于xx，长和宽不小于xx）。这应该是个比较复杂的算法，可以帮忙给出逻辑即可。
     * questionSource : https://www.zhihu.com/question/48397239
     * knowledgeKeywords : ["递归","深度搜索"]
     */

    private int id;
    private String question;
    private String questionDescription;
    private String questionSource;
    private String knowledgeKeywords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionSource() {
        return questionSource;
    }

    public void setQuestionSource(String questionSource) {
        this.questionSource = questionSource;
    }

    public String getKnowledgeKeywords() {
        return knowledgeKeywords;
    }

    public void setKnowledgeKeywords(String knowledgeKeywords) {
        this.knowledgeKeywords = knowledgeKeywords;
    }
}
