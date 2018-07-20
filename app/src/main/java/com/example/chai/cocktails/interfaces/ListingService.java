package com.example.chai.cocktails.interfaces;

import com.example.chai.cocktails.models.DrinkDetailListing;
import com.example.chai.cocktails.models.DrinkFullDetailWrapper;
import com.example.chai.cocktails.models.DrinkListing;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ListingService {

    String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    String LIST_BY_CATEGORY = "list.php?c=list";
    String LIST_BY_GLASS = "list.php?g=list";
    String LIST_BY_INGREDIENTS = "list.php?i=list";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(LIST_BY_CATEGORY)
    Call<DrinkListing> listDrinksByCategory();

    @GET(LIST_BY_GLASS)
    Call<DrinkListing> listDrinksByGlass();

    @GET(LIST_BY_INGREDIENTS)
    Call<DrinkListing> listDrinksByIngredient();

    @GET
    Call<DrinkDetailListing> filterByMentioned(@Url String url);

    @GET
    Call<DrinkFullDetailWrapper> getDrinkById(@Url String url);


}