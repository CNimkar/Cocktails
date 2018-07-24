package com.example.chai.cocktails.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkFullDetailsAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.models.pojos.DrinkFullDetail;
import com.example.chai.cocktails.testUtils.DrinkProviderTestUtil;
import com.example.chai.cocktails.utils.Constants;
import com.example.chai.cocktails.testUtils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CocktailRepositoryTest {

    CocktailRepository repository;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initRep(){
        repository = CocktailRepository.createNewInstance();
    }

    @Test
    public void getInstance() {
        assertNotNull(repository);
    }

    @Test
    public void getDataWithCategoryFilter() {

        try {
          NameListingAPIResponse response =
                  LiveDataTestUtil.getValue(repository.getData(Constants.FILTER_CATEGORY));
            assertEquals(11, response.getDrinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithIngredientFilter() {

        try {
            NameListingAPIResponse response =
                    LiveDataTestUtil.getValue(repository.getData(Constants.FILTER_INGREDIENTS));
            assertEquals(160, response.getDrinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithGlassFilter() {

        try {
            NameListingAPIResponse response =
                    LiveDataTestUtil.getValue(repository.getData(Constants.FILTER_GLASS));
            assertEquals(31, response.getDrinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getDataWithCategoryName() {
        DrinkListingAPIResponse categoryResponse = null;
        try {
            categoryResponse = LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_CATEGORY, "Milk / Float / Shake"));

            assertEquals(17, categoryResponse.getDrinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithIngredientName() {

        try {
            DrinkListingAPIResponse ingredientResponse =  LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_INGREDIENTS, "Apple brandy"));
            assertEquals( 2, ingredientResponse.getDrinks().size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataWithGlassName() {
        DrinkListingAPIResponse glassResponse = null;
        try {
            glassResponse = LiveDataTestUtil.getValue(
                    repository.getData(Constants.FILTER_GLASS, "Champagne flute"));

            assertEquals(13, glassResponse.getDrinks().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDataFullDetails(){
        DrinkFullDetailsAPIResponse glassResponse = null;
        try {
            glassResponse = LiveDataTestUtil.getValue(
                    repository.getDataFullDetails("15346"));

            DrinkFullDetail drink  = glassResponse.getDrink();
            DrinkFullDetail testDrink = DrinkProviderTestUtil.createTestDrink();

            assertEquals(testDrink.getName(), drink.getName());
            assertEquals(testDrink.getCategory(), drink.getCategory());
            assertEquals(testDrink.getGlassType(), drink.getGlassType());
            assertEquals(testDrink.getAlcoholic(), drink.getAlcoholic());
            assertEquals(testDrink.getInstructions(), drink.getInstructions());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}