package ru.avem.cookmate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Category {

    public ArrayList<Category> children;
    public ArrayList<String> selection;

    public String name;

    public Category() {
        children = new ArrayList<Category>();
        selection = new ArrayList<String>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static ArrayList<Category> getCategories() {
        ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(IngredientsData.allIngredients));
        ArrayList<Category> categories = new ArrayList<Category>();
        String firstCharacterLastTime = "";
        Category category = new Category();
        for (int i = 0; i < ingredientsList.size(); i++) {
            String firstCharacter = String.valueOf(ingredientsList.get(i).charAt(0));
            if (!firstCharacter.equals(firstCharacterLastTime)) {
                category = new Category(firstCharacter);
            }
            category.children.add(new Category(ingredientsList.get(i)));

            if (!firstCharacter.equals(firstCharacterLastTime)) {
                categories.add(category);
            }
            firstCharacterLastTime = firstCharacter;
        }
        return categories;
    }

    public static Category get(String name) {
        ArrayList<Category> collection = Category.getCategories();
        for (Iterator<Category> iterator = collection.iterator(); iterator.hasNext(); ) {
            Category cat = (Category) iterator.next();
            if (cat.name.equals(name)) {
                return cat;
            }

        }
        return null;
    }
}
