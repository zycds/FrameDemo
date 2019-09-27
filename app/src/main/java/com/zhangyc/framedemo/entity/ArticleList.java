package com.zhangyc.framedemo.entity;

import java.util.List;

public class ArticleList {

    private int curPage;

    private int pageCount;

    private int size;

    private int total;

    private boolean over;

    private List<Article> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public void setDatas(List<Article> articleList) {
        datas = articleList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"curPage\":")
                .append(curPage);
        sb.append(",\"pageCount\":")
                .append(pageCount);
        sb.append(",\"size\":")
                .append(size);
        sb.append(",\"total\":")
                .append(total);
        sb.append(",\"over\":")
                .append(over);
        sb.append(",\"datas\":")
                .append(datas);
        sb.append('}');
        return sb.toString();
    }
}
