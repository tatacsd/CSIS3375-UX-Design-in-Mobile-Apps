package com.thayscasado.doginstagram;

import java.time.LocalDate;

public class Dog {
    private Integer ID;
    private Integer imageFileName;
    private String breed;
    private String name;
    private LocalDate DOB;
    private Integer numLikes;
    private boolean heartNoHeart;

    public Dog(Integer ID, Integer imageFileName, String breed, String name, LocalDate DOB) {
        this.ID = ID;
        this.imageFileName = imageFileName;
        this.breed = breed;
        this.name = name;
        this.DOB = DOB;
        this.numLikes = 0;
        heartNoHeart = false;
    }

    public Integer getID() { return ID; }
    public void setID(Integer ID) { this.ID = ID; }
    public Integer getImageFileName() { return imageFileName; }
    public void setImageFileName(Integer imageFileName) { this.imageFileName = imageFileName; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public String getName() { return name;}
    public void setName(String name) {this.name = name; }
    public LocalDate getDOB() { return DOB; }
    public void setDOB(LocalDate DOB) { this.DOB = DOB; }
    public Integer getNumLikes() { return numLikes; }
    public void setNumLikes(Integer numLikes) {  this.numLikes = numLikes; }
    public boolean getHeartNoHeart() {  return heartNoHeart; }
    public void setHeartNoHeart(boolean heartNoHeart) {  this.heartNoHeart = heartNoHeart; }
}
