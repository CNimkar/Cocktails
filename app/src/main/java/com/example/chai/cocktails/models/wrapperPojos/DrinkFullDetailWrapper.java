package com.example.chai.cocktails.models.wrapperPojos;

import com.example.chai.cocktails.models.pojos.DrinkFullDetail;

import java.util.List;

public class DrinkFullDetailWrapper {
    List<DrinkFullDetail> drinks;

    public DrinkFullDetailWrapper(List<DrinkFullDetail> drinks) {
        this.drinks = drinks;
    }

    public List<DrinkFullDetail> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkFullDetail> drinks) {
        this.drinks = drinks;
    }
}
