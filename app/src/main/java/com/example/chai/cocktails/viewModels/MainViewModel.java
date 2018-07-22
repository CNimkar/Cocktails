package com.example.chai.cocktails.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.repository.CocktailRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<NameListingAPIResponse> nameListingAPIResponse;
    CocktailRepository repository =
            CocktailRepository.getInstance();

    public MainViewModel() {
        nameListingAPIResponse = new MutableLiveData<>();
    }

    public LiveData<NameListingAPIResponse> getApiResponse(String filter) {
        if (nameListingAPIResponse.getValue() == null ||
                nameListingAPIResponse.getValue().getResponseType()
                        != NameListingAPIResponse.SUCCESSFUL_RESPONSE) {
            getData(filter);
        }
        return nameListingAPIResponse;
    }

    public void getData(String filter) {
        nameListingAPIResponse = repository.getData(filter);
    }


}