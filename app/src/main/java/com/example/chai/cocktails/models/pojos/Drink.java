package com.example.chai.cocktails.models.pojos;

import com.google.gson.annotations.SerializedName;

public class Drink {
    //used similar naming convention as backend api has
    @SerializedName(value = "strCategory", alternate = {"strGlass", "strIngredient1"})
    String category;

    public Drink(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
