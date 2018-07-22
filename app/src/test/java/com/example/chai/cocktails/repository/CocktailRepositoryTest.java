package com.example.chai.cocktails.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.testUtils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CocktailRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Test
    public void getInstance() {
    }

    @Test
    public void getDataWithFilter() {
        CocktailRepository repository = CocktailRepository.getInstance();

        try {
          NameListingAPIResponse response =  LiveDataTestUtil.getValue(repository.getData(Constants.FILTER_CATEGORY));
            assertEquals(response.getDrinks().size(), 11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithIngredientName() {
        CocktailRepository repository = CocktailRepository.getInstance();

        try {
            DrinkListingAPIResponse ingredientResponse =  LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_INGREDIENTS, "Gin"));
            assertEquals(ingredientResponse.getDrinks().size(), 92);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithCategoryName() {
        CocktailRepository repository = CocktailRepository.getInstance();
        DrinkListingAPIResponse categoryResponse = null;
        try {
            categoryResponse = LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_CATEGORY, "Milk / Float / Shake"));

            assertEquals(categoryResponse.getDrinks().size(), 17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithGlassName() {
        CocktailRepository repository = CocktailRepository.getInstance();
        DrinkListingAPIResponse glassResponse = null;
        try {
            glassResponse = LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_GLASS, "Champagne flute"));

            assertEquals(glassResponse.getDrinks().size(), 13);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}