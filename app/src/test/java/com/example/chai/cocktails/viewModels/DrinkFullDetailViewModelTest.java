package com.example.chai.cocktails.viewModels;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.chai.cocktails.models.apiResponseWrappers.DrinkFullDetailsAPIResponse;
import com.example.chai.cocktails.models.apiResponseWrappers.DrinkListingAPIResponse;
import com.example.chai.cocktails.models.pojos.DrinkFullDetail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DrinkFullDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getApiResponse() {
        DrinkFullDetailViewModel viewModel = Mockito.spy(DrinkFullDetailViewModel.class);
        viewModel.getApiResponse("15346");
        verify(viewModel, times(1)).getDataFullDetails(
                "15346");
    }

    @Test
    public void getApiResponseNotCalledObservableNotNull() {
        MutableLiveData<DrinkFullDetailsAPIResponse> apiResponse = new MutableLiveData<>();
        apiResponse.postValue(new DrinkFullDetailsAPIResponse(new DrinkFullDetail()));
        DrinkFullDetailViewModel viewModel = Mockito.spy(DrinkFullDetailViewModel.class);
        viewModel.apiResponseObservable = apiResponse;
        viewModel.getApiResponse("15346");
        verify(viewModel, times(0)).getDataFullDetails(
                "15346");
    }

    @Test
    public void getApiResponseCalledEarlierFailed() {
        MutableLiveData<DrinkFullDetailsAPIResponse> apiResponse = new MutableLiveData<>();
        DrinkFullDetailsAPIResponse response =
                new DrinkFullDetailsAPIResponse(new DrinkFullDetail());
        response.setResponseType(DrinkListingAPIResponse.REQUEST_ERROR_RESPONSE);
        apiResponse.postValue(response);
        DrinkFullDetailViewModel viewModel = Mockito.spy(DrinkFullDetailViewModel.class);
        viewModel.apiResponseObservable = apiResponse;
        viewModel.getApiResponse("15346");
        verify(viewModel, times(1)).getDataFullDetails(
                "15346");
    }

}