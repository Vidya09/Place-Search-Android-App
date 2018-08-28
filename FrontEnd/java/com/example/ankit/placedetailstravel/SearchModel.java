package com.example.ankit.placedetailstravel;

public class SearchModel {
    private String imgphoto;
    private String name;
    private String description;
    private String placeId;

    public SearchModel(){

    }

    public SearchModel(String imgphoto, String name, String description,String placeId) {
        this.imgphoto = imgphoto;
        this.name = name;
        this.description = description;
        this.placeId = placeId;
    }

    //Getter

    public String getPhoto() {
        return imgphoto;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlaceId() {
        return placeId;
    }

    //Setter

    public void setPhoto(String imgphoto) {
        this.imgphoto = imgphoto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlaceId(String placeId){this.placeId = placeId;}
}
