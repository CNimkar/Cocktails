package com.example.chai.cocktails.models.apiResponseWrappers;

import com.example.chai.cocktails.models.pojos.DrinkDetail;

import java.util.List;

public class DrinkListingAPIResponse {

    public static final int SUCCESSFUL_RESPONSE = 100;
    public static final int REQUEST_ERROR_RESPONSE = 101;
    public static final int THROWABLE_ERROR_RESPONSE = 102;

    private List<DrinkDetail> drinks;
    private int requestError = 0;
    private Throwable error;
    private int responseType;

    public DrinkListingAPIResponse(List<DrinkDetail> drinks) {
        setResponseType(SUCCESSFUL_RESPONSE);
        this.drinks = drinks;
        this.error = null;
    }

    public DrinkListingAPIResponse(int requestError) {
        setResponseType(REQUEST_ERROR_RESPONSE);
        this.requestError = requestError;
        this.error = null;
        this.drinks = null;
    }

    public DrinkListingAPIResponse(Throwable error) {
        setResponseType(THROWABLE_ERROR_RESPONSE);
        this.error = error;
        this.drinks = null;
    }


    public List<DrinkDetail> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkDetail> drinks) {
        this.drinks = drinks;
    }

    public int getRequestError() {
        return requestError;
    }

    public void setRequestError(int requestError) {
        this.requestError = requestError;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public int getResponseType() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }
}
