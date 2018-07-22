package com.example.chai.cocktails.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.repository.CocktailRepository;

public class DrinkDetailListingViewModel extends ViewModel {

    public MutableLiveData<DrinkListingAPIResponse> drinksListingObservable;
    String type;
    String name;
    CocktailRepository repository = CocktailRepository.getInstance();

    public DrinkDetailListingViewModel() {
        drinksListingObservable = new MutableLiveData<>();


    }

    public LiveData<DrinkListingAPIResponse> getApiResponse(String type, String name) {
        if (drinksListingObservable.getValue() == null ||
                drinksListingObservable.getValue().getResponseType()
                        != DrinkListingAPIResponse.SUCCESSFUL_RESPONSE) {
            getData(type, name);
        }

        return drinksListingObservable;
    }


    public void getData(String type, String name) {
        drinksListingObservable = repository.getData(type, name);
    }
}
