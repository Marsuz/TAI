package model;

import javax.persistence.*;

@Entity
public class Ingredient extends ObjectWithId {

    private String name;
    private String unit;

    @ManyToOne
    private Category category;

    public Ingredient() {
    }

    public Ingredient(String name, String unit, Category category) {
        this.name = name;
        this.unit = unit;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
