package ru.tbank.edu.model;

public class Category {
    private Integer id;
    private String slug;
    private String name;

    public Category(Integer id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }

    public Category() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
