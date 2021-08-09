package ru.avem.cookmate;

import androidx.annotation.NonNull;

public class Recepts {

    String name;
    String[] ingredientsNeed;

    public Recepts() {
    }

    public Recepts(String name, String[] ingredientsNeed) {
        this.name = name;
        this.ingredientsNeed = ingredientsNeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredientsNeed() {
        return ingredientsNeed;
    }

    public void setIngredientsNeed(String[] ingredientsNeed) {
        this.ingredientsNeed = ingredientsNeed;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
