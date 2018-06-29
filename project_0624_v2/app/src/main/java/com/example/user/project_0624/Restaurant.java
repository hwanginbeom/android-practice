package com.example.user.project_0624;

public class Restaurant {
    String resID;
    String resTitle;
    String resTag;
    String resRating;
    String resReview1;
    String resReview2;
    String resRank;
    String resImage;
    String resNation;
    String resLink;

    public Restaurant(String resID, String resTitle, String resTag, String resRating, String resReview1, String resReview2, String resRank, String resImage, String resNation, String resLink) {
        this.resID = resID;
        this.resTitle = resTitle;
        this.resTag = resTag;
        this.resRating = resRating;
        this.resReview1 = resReview1;
        this.resReview2 = resReview2;
        this.resRank = resRank;
        this.resImage = resImage;
        this.resNation = resNation;
        this.resLink = resLink;
    }

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public String getResTitle() {
        return resTitle;
    }

    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
    }

    public String getResTag() {
        return resTag;
    }

    public void setResTag(String resTag) {
        this.resTag = resTag;
    }

    public String getResRating() {
        return resRating;
    }

    public void setResRating(String resRating) {
        this.resRating = resRating;
    }

    public String getResReview1() {
        return resReview1;
    }

    public void setResReview1(String resReview1) {
        this.resReview1 = resReview1;
    }

    public String getResReview2() {
        return resReview2;
    }

    public void setResReview2(String resReview2) {
        this.resReview2 = resReview2;
    }

    public String getResRank() {
        return resRank;
    }

    public void setResRank(String resRank) {
        this.resRank = resRank;
    }

    public String getResImage() {
        return resImage;
    }

    public void setResImage(String resImage) {
        this.resImage = resImage;
    }

    public String getResNation() {
        return resNation;
    }

    public void setResNation(String resNation) {
        this.resNation = resNation;
    }

    public String getResLink() {
        return resLink;
    }

    public void setResLink(String resLink) {
        this.resLink = resLink;
    }
}