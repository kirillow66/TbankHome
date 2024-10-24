package ru.tbank.edu.model;

public class LocationDTO {
    private Integer id;
    private String slug;
    private String name;

    public LocationDTO(Integer id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }

    public LocationDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
