package com.example.chai.cocktails.models.wrapperPojos;

import com.example.chai.cocktails.models.pojos.Drink;

import java.util.List;

public class DrinkListing {


    List<Drink> drinks;

    public DrinkListing(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
