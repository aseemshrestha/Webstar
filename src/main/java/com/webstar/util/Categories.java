package com.webstar.util;

import java.util.Map;
import java.util.TreeMap;

public class Categories
{
    public static final Map<String, String> CATEGORIES = new TreeMap<>();

    public Categories()
    {
        setCategories();
    }

    private void setCategories()
    {

        CATEGORIES.put("KIDS", "KIDS");
        CATEGORIES.put("MUSIC", "MUSIC");
        CATEGORIES.put("WOMEN", "WOMEN");
        CATEGORIES.put("SCHOOLS", "SCHOOLS");
        CATEGORIES.put("SPORTS", "SPORTS");
        CATEGORIES.put("GAMING", "GAMING");
        CATEGORIES.put("MOVIES", "MOVIES");
        CATEGORIES.put("PHOTOS", "PHOTOS");
        CATEGORIES.put("POLITICS", "POLITICS");
        CATEGORIES.put("LOL", "LOL");
        CATEGORIES.put("NEWS", "NEWS");
        CATEGORIES.put("TELEVISION", "TELEVISION");
    }

    public Map<String, String> getCategories()
    {

        return CATEGORIES;
    }
}
