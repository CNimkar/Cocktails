package com.example.chai.cocktails.viewModels;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.models.pojos.DrinkDetail;
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
public class DrinkDetailListingViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Test
    public void getApiResponse() {
        DrinkDetailListingViewModel viewModel = Mockito.spy(DrinkDetailListingViewModel.class);
        viewModel.getApiResponse(Constants.FILTER_GLASS, "Champagne flute");
        verify(viewModel, times(1)).getData(
                Constants.FILTER_GLASS, "Champagne flute");
    }

    @Test
    public void getApiResponseNotCalledObservableNotNull() {
        MutableLiveData<DrinkListingAPIResponse> apiResponse = new MutableLiveData<>();
        apiResponse.postValue(new DrinkListingAPIResponse(new ArrayList<DrinkDetail>()));
        DrinkDetailListingViewModel viewModel = Mockito.spy(DrinkDetailListingViewModel.class);
        viewModel.drinksListingObservable = apiResponse;
        viewModel.getApiResponse(Constants.FILTER_GLASS, "Champagne flute");
        verify(viewModel, times(0)).getData(
                Constants.FILTER_GLASS, "Champagne flute");

    }

    @Test
    public void getApiResponseCalledEarlierFailed() {
        MutableLiveData<DrinkListingAPIResponse> apiResponse = new MutableLiveData<>();
        DrinkListingAPIResponse response = new DrinkListingAPIResponse(new ArrayList<DrinkDetail>());
        response.setResponseType(DrinkListingAPIResponse.REQUEST_ERROR_RESPONSE);
        apiResponse.postValue(response);
        DrinkDetailListingViewModel viewModel = Mockito.spy(DrinkDetailListingViewModel.class);
        viewModel.drinksListingObservable = apiResponse;
        viewModel.getApiResponse(Constants.FILTER_GLASS, "Champagne flute");
        verify(viewModel, times(1)).getData(
                Constants.FILTER_GLASS, "Champagne flute");

    }
}