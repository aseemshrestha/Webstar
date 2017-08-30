package com.webstar.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Categories
{
    public static final Map<String, String> CATEGORIES = new HashMap<>();
    public static final Map<String, String> SUB_CATEGORIES = new HashMap<>();

    public static Map<String, String> getCategories()
    {
        CATEGORIES.put("Gaming", "Gaming");
        CATEGORIES.put("Kids", "Kids");
        CATEGORIES.put("Lol", "Lol");
        CATEGORIES.put("Music", "Music");
        CATEGORIES.put("Movies", "Movies");
        CATEGORIES.put("News", "News");
        CATEGORIES.put("Photos", "Photos");
        CATEGORIES.put("Politics", "Politics");
        CATEGORIES.put("Schools", "Schools");
        CATEGORIES.put("Sports", "Sports");
        CATEGORIES.put("Television", "Television");
        CATEGORIES.put("Women", "Women");
        return CATEGORIES;
    }

    public static Map<String, String> getSubCategories()
    {
        SUB_CATEGORIES.put("Kids", "Kids1,Kids2,Kids3");
        SUB_CATEGORIES.put("Music", "Music1,Music2,Music3");
        return SUB_CATEGORIES;

    }

    public static String getSubCategoryByKey(String category)
    {

        return getSubCategories().entrySet().stream()
            .filter(map -> category.equals(map.getKey()))
            .map(map -> map.getValue())
            .collect(Collectors.joining());
    }
}
