package com.fireflies.mcqs.dto;

import java.util.List;

public class McqDto {
    private int id;
    private String question;
    private String ansChosen;
    private boolean ansFlag;
    private String ansCorrect;
    private List<McqDto> resultList;

    public McqDto(){
    }

    public List<McqDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<McqDto> resultList) {
        this.resultList = resultList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnsChosen() {
        return ansChosen;
    }

    public void setAnsChosen(String ansChosen) {
        this.ansChosen = ansChosen;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public boolean isAnsFlag() {
        return ansFlag;
    }

    public void setAnsFlag(boolean ansFlag) {
        this.ansFlag = ansFlag;
    }

    public String getAnsCorrect() {
        return ansCorrect;
    }

    public void setAnsCorrect(String ansCorrect) {
        this.ansCorrect = ansCorrect;
    }
}
