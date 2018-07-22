package com.example.chai.cocktails.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkFullDetailsAPIResponse;
import com.example.chai.cocktails.repository.CocktailRepository;

public class DrinkFullDetailViewModel extends ViewModel {

    public MutableLiveData<DrinkFullDetailsAPIResponse> apiResponseObservable;
    CocktailRepository repository = CocktailRepository.getInstance();


    public DrinkFullDetailViewModel() {
        apiResponseObservable = new MutableLiveData<>();
    }

    public LiveData<DrinkFullDetailsAPIResponse> getApiResponse(String id) {
        if (apiResponseObservable.getValue() == null ||
                apiResponseObservable.getValue().getResponseType()
                        != DrinkFullDetailsAPIResponse.SUCCESSFUL_RESPONSE) {
            getDataFullDetails(id);
        }
        return apiResponseObservable;
    }


    public void getDataFullDetails(String id) {
        apiResponseObservable = repository.getDataFullDetails(id);
    }
}
