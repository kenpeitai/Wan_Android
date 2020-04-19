package com.example.wanandroid;

public class Datas{
private String title;
private String niceDate;
private String link;
private String superChapterName;

    public Datas(String title, String niceDate,String superChapterName) {
        this.title = title;
        this.niceDate = niceDate;
        this.superChapterName = superChapterName;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
