package com.example.chai.cocktails.utils;

public class Constants {
    public static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";

    public static final String FILTER_CATEGORY = "filter.php?c=";
    public static final String FILTER_GLASS = "filter.php?g=";
    public static final String FILTER_INGREDIENTS = "filter.php?i=";
    public static final String LOOKUP_BY_ID = "lookup.php?i=";

    public static final String CATEGORY_NAME = "categoryName";
    public static final String TYPE = "type";
    public static final String ID = "id";

    public static String getUrlByFilterAndName(String filter, String name) {
        return BASE_URL + filter + name;
    }

    public static String getUrlById(String url) {
        return BASE_URL + LOOKUP_BY_ID + url;
    }
}
