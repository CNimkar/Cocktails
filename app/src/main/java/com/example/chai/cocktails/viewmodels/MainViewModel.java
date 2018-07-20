package com.example.chai.cocktails.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.models.NameListingAPIResponse;
import com.example.chai.cocktails.repository.CocktailRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<NameListingAPIResponse> nameListingAPIResponse;
    CocktailRepository repository =
            CocktailRepository.getInstance();

    public MainViewModel() {
        nameListingAPIResponse = new MutableLiveData<>();
    }

    public void getData(String filter) {
        nameListingAPIResponse = repository.getData(filter);
    }


}