package com.example.chai.cocktails.models.apiResponseWrappers;

import com.example.chai.cocktails.models.pojos.DrinkFullDetail;

public class DrinkFullDetailsAPIResponse {
    public static final int SUCCESSFUL_RESPONSE = 100;
    public static final int REQUEST_ERROR_RESPONSE = 101;
    public static final int THROWABLE_ERROR_RESPONSE = 102;

    private DrinkFullDetail drink;
    private int requestError = 0;
    private Throwable error;
    private int responseType;


    public DrinkFullDetailsAPIResponse(DrinkFullDetail drink) {
        setResponseType(SUCCESSFUL_RESPONSE);
        this.drink = drink;
        this.error = null;
    }

    public DrinkFullDetailsAPIResponse(int requestError) {
        setResponseType(REQUEST_ERROR_RESPONSE);
        this.requestError = requestError;
        this.error = null;
        this.drink = null;
    }

    public DrinkFullDetailsAPIResponse(Throwable error) {
        setResponseType(THROWABLE_ERROR_RESPONSE);
        this.error = error;
        this.drink = null;
    }

    public DrinkFullDetail getDrink() {
        return drink;
    }

    public void setDrink(DrinkFullDetail drink) {
        this.drink = drink;
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
