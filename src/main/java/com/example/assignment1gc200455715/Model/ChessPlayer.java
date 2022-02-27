package com.example.assignment1gc200455715.Model;

public class ChessPlayer {
    private int id, rating;
    private String name, sex, country;

    public ChessPlayer(int id, String name, String country, String sex, int rating) {
        setId(id);
        setRating(rating);
        setCountry(country);
        setSex(sex);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating >= 1000 && rating <= 3000)
            this.rating = rating;
        else
            throw(new IllegalArgumentException("Rating must be between 1000 to 3000"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() > 3 && name.length() < 61)
            this.name = name;
        else
            throw(new IllegalArgumentException("Name must be at least 3 char long and less than 60 char"));

    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if(sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F"))
            this.sex = sex.toUpperCase();
        else
            throw(new IllegalArgumentException("Please write M OR F for male and female respectively"));

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(country.length() >=2)
            this.country = country;
        else
            throw(new IllegalArgumentException("Country name must be at least 2 char long"));
    }
}
