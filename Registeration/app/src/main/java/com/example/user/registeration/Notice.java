package com.example.user.registeration;

public class Notice {

    String title;
    String tag;
    String rating;
    String link;

    public Notice(String title, String tag, String rating, String link) {
        this.title = title;
        this.tag = tag;
        this.rating = rating;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
