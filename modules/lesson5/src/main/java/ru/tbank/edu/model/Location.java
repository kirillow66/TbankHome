package ru.tbank.edu.model;

public class Location {
    private String slug;
    private String name;

    public Location(String slug, String name) {

        this.slug = slug;
        this.name = name;
    }

    public Location() {
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
