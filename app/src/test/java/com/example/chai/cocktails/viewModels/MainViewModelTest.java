package com.example.chai.cocktails.viewModels;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.chai.cocktails.models.apiResponseWrappers.NameListingAPIResponse;
import com.example.chai.cocktails.models.pojos.Drink;
import com.example.chai.cocktails.utils.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Test
    public void getApiResponseCalled() {
        MainViewModel mainViewModel = Mockito.spy(new MainViewModel());
        mainViewModel.getApiResponse(Constants.FILTER_CATEGORY);
        verify(mainViewModel, times(1)).getData(Constants.FILTER_CATEGORY);
    }

    @Test
    public void getApiResponseNotCalledObservableNotNull() {
        MutableLiveData<NameListingAPIResponse> apiResponse = new MutableLiveData<>();
        apiResponse.postValue(new NameListingAPIResponse(new ArrayList<Drink>()));
        MainViewModel mainViewModel = Mockito.spy(new MainViewModel());
        mainViewModel.nameListingAPIResponse = apiResponse;
        mainViewModel.getApiResponse(Constants.FILTER_CATEGORY);
        verify(mainViewModel, times(0)).getData(Constants.FILTER_CATEGORY);
    }

    @Test
    public void getApiResponseCalledEarlierFailed() {
        MutableLiveData<NameListingAPIResponse> apiResponse = new MutableLiveData<>();
        NameListingAPIResponse response = new NameListingAPIResponse(new ArrayList<Drink>());
        response.setResponseType(NameListingAPIResponse.REQUEST_ERROR_RESPONSE);
        apiResponse.postValue(response);
        MainViewModel mainViewModel = Mockito.spy(new MainViewModel());
        mainViewModel.nameListingAPIResponse = apiResponse;
        mainViewModel.getApiResponse(Constants.FILTER_CATEGORY);
        verify(mainViewModel, times(1)).getData(Constants.FILTER_CATEGORY);
    }

}