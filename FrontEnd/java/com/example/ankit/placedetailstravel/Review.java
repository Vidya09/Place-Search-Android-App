package com.example.ankit.placedetailstravel;

public class Review {
    private String author_name;
    private String time_created;
    private String review_text;
    private String author_pic;
    private Double rate;

    public Review(){

    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Review(String author_name, String time_created, String review_text, String author_pic, Double rate){
        this.author_name = author_name;
        this.time_created = time_created;
        this.review_text = review_text;
        this.author_pic = author_pic;
        this.rate = rate;

    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getTime_created() {
        return time_created;
    }

    public String getReview_text() {
        return review_text;
    }

    public String getAuthor_pic() {
        return author_pic;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setTime_created(String time_created) {
        this.time_created = time_created;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public void setAuthor_pic(String author_pic) {
        this.author_pic = author_pic;
    }
}
