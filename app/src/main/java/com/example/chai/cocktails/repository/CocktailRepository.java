package com.example.chai.cocktails.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.chai.cocktails.interfaces.ListingService;
import com.example.chai.cocktails.models.apiResponseWrappers.DrinkFullDetailsAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.models.wrapperPojos.DrinkDetailListing;
import com.example.chai.cocktails.models.wrapperPojos.DrinkFullDetailWrapper;
import com.example.chai.cocktails.models.wrapperPojos.DrinkListing;
import com.example.chai.cocktails.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailRepository {
    private static CocktailRepository cocktailRepository;

    private ListingService listingService;

    private MutableLiveData<NameListingAPIResponse> nameListingApiResponse
            = new MutableLiveData<>();
    private MutableLiveData<DrinkListingAPIResponse> drinkListingAPIResponse
            = new MutableLiveData<>();
    private MutableLiveData<DrinkFullDetailsAPIResponse> drinkFullDetailsAPIResponse
            = new MutableLiveData<>();

    private CocktailRepository() {
        listingService = ListingService.retrofit.create(ListingService.class);
    }

    public static synchronized CocktailRepository getInstance() {
        if (cocktailRepository == null) {
            cocktailRepository = new CocktailRepository();
        }
        return cocktailRepository;
    }

    //only for testing purposes
    public static CocktailRepository createNewInstance(){
        return new CocktailRepository();
    }


    public MutableLiveData<NameListingAPIResponse> getData(final String filter) {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkListing> call;

        switch (filter) {
            case Constants.FILTER_CATEGORY:
                call = webService.listDrinksByCategory();
                break;
            case Constants.FILTER_GLASS:
                call = webService.listDrinksByGlass();
                break;
            case Constants.FILTER_INGREDIENTS:
                call = webService.listDrinksByIngredient();
                break;
            default:
                call = webService.listDrinksByCategory();
        }

        call.enqueue(new Callback<DrinkListing>() {
            @Override
            public void onResponse(Call<DrinkListing> call,
                                   Response<DrinkListing> response) {
                if (response.isSuccessful()) {
                    nameListingApiResponse.postValue(
                            new NameListingAPIResponse(
                                    response.body().getDrinks()));
                } else {
                    int statusCode = response.code();
                    nameListingApiResponse.postValue(new NameListingAPIResponse(statusCode));
                }
            }

            @Override
            public void onFailure(Call<DrinkListing> call, Throwable t) {
                nameListingApiResponse.postValue(new NameListingAPIResponse(t));
            }


        });

        return nameListingApiResponse;

    }


    public MutableLiveData<DrinkListingAPIResponse> getData(String type, String name) {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkDetailListing> call = webService.filterByMentioned(
                Constants.getUrlByFilterAndName(type, name));
        call.enqueue(new Callback<DrinkDetailListing>() {
            @Override
            public void onResponse(Call<DrinkDetailListing> call,
                                   Response<DrinkDetailListing> response) {
                if (response.isSuccessful()) {
                    drinkListingAPIResponse.postValue(
                            new DrinkListingAPIResponse(response.body().getDrinks()));
                } else {
                    int responseCode = response.code();
                    drinkListingAPIResponse.postValue(new DrinkListingAPIResponse(responseCode));
                }
            }

            @Override
            public void onFailure(Call<DrinkDetailListing> call, Throwable t) {
                drinkListingAPIResponse.postValue(new DrinkListingAPIResponse(t));
            }


        });

        return drinkListingAPIResponse;

    }

    public MutableLiveData<DrinkFullDetailsAPIResponse> getDataFullDetails(String id) {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkFullDetailWrapper> call = webService.getDrinkById(Constants.getUrlById(id));
        call.enqueue(new Callback<DrinkFullDetailWrapper>() {
            @Override
            public void onResponse(Call<DrinkFullDetailWrapper> call
                    , Response<DrinkFullDetailWrapper> response) {
                if (response.isSuccessful()) {
                    drinkFullDetailsAPIResponse
                            .postValue(
                                    new DrinkFullDetailsAPIResponse(
                                            response.body().getDrinks().get(0)));
                } else {
                    int responseCode = response.code();
                    drinkFullDetailsAPIResponse.postValue(
                            new DrinkFullDetailsAPIResponse(responseCode));
                }
            }

            @Override
            public void onFailure(Call<DrinkFullDetailWrapper> call, Throwable t) {
                drinkFullDetailsAPIResponse.postValue(new DrinkFullDetailsAPIResponse(t));
            }


        });

        return drinkFullDetailsAPIResponse;
    }
}
