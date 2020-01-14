package cn.travel.domain;

import java.util.List;

public class TurnPage<T> {
    private String turnPageCurrentPage;
    private String turnPageShowNum;
    private String turnPageTotalNum;
    private String turnPageTotalPage;
    private List<T> list;

    public TurnPage() {
    }

    public TurnPage(String turnPageCurrentPage, String turnPageShowNum, String turnPageTotalNum, String turnPageTotalPage, List<T> list) {
        this.turnPageCurrentPage = turnPageCurrentPage;
        this.turnPageShowNum = turnPageShowNum;
        this.turnPageTotalNum = turnPageTotalNum;
        this.turnPageTotalPage = turnPageTotalPage;
        this.list = list;
    }

    public String getTurnPageCurrentPage() {
        return turnPageCurrentPage;
    }

    public void setTurnPageCurrentPage(String turnPageCurrentPage) {
        this.turnPageCurrentPage = turnPageCurrentPage;
    }

    public String getTurnPageShowNum() {
        return turnPageShowNum;
    }

    public void setTurnPageShowNum(String turnPageShowNum) {
        this.turnPageShowNum = turnPageShowNum;
    }

    public String getTurnPageTotalNum() {
        return turnPageTotalNum;
    }

    public void setTurnPageTotalNum(String turnPageTotalNum) {
        this.turnPageTotalNum = turnPageTotalNum;
    }

    public String getTurnPageTotalPage() {
        return turnPageTotalPage;
    }

    public void setTurnPageTotalPage(String turnPageTotalPage) {
        this.turnPageTotalPage = turnPageTotalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
