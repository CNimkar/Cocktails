package com.example.chai.cocktails.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.models.apiresponsewrappers.DrinkListingAPIResonse;
import com.example.chai.cocktails.repository.CocktailRepository;

public class DrinkDetailListingViewModel extends ViewModel {

    public MutableLiveData<DrinkListingAPIResonse> drinksListingObservable;
    String type;
    String name;
    CocktailRepository repository = CocktailRepository.getInstance();

    public DrinkDetailListingViewModel() {
        drinksListingObservable = new MutableLiveData<>();


    }

    public LiveData<DrinkListingAPIResonse> getApiResponse(String type, String name) {
        if (drinksListingObservable.getValue() == null ||
                drinksListingObservable.getValue().getResponseType()
                        != DrinkListingAPIResonse.SUCCESSFUL_RESPONSE) {
            getData(type, name);
        }

        return drinksListingObservable;
    }


    public void getData(String type, String name) {
        drinksListingObservable = repository.getData(type, name);
    }
}
