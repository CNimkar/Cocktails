package com.example.chai.cocktails.testUtils;

import com.example.chai.cocktails.models.pojos.DrinkFullDetail;

public class DrinkProviderTestUtil {
    public static DrinkFullDetail createTestDrink() {
        DrinkFullDetail drink = new DrinkFullDetail();
        drink.setName("155 Belmont");
        drink.setCategory("Cocktail");
        drink.setAlcoholic("Alcoholic");
        drink.setGlassType("White wine glass");
        drink.setInstructions("Blend with ice. Serve in a wine glass. Garnish with carrot.");
        return drink;
    }
}
