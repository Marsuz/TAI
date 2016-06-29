package model;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @Column(name="ingredient_id")
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    private String unit;

    public Ingredient() {
    }

    public Ingredient(String name, Category category, String unit) {
        this.name = name;
        this.category = category;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!name.equals(that.name)) return false;
        return category != null ? category.equals(that.category) : that.category == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
