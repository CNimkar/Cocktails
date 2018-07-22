package com.example.chai.cocktails.models.wrapperPojos;

import com.example.chai.cocktails.models.pojos.DrinkDetail;

import java.util.List;

public class DrinkDetailListing {
    List<DrinkDetail> drinks;

    public DrinkDetailListing(List<DrinkDetail> drinks) {
        this.drinks = drinks;
    }

    public List<DrinkDetail> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkDetail> drinks) {
        this.drinks = drinks;
    }
}
