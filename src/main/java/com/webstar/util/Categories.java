package com.webstar.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Categories
{
    public static final Map<String, String> CATEGORIES = new LinkedHashMap<>();
    public static final Map<String, String> SUB_CATEGORIES = new LinkedHashMap<>();

    public static Map<String, String> getCategories()
    {   
        CATEGORIES.put("Any", "Any");
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
        SUB_CATEGORIES.put("Any", "Any");
        SUB_CATEGORIES.put("Gaming", "Gaming1, Gaming2, Gaming3, Gaming4, Gaming5, Gaming6");
        SUB_CATEGORIES.put("Kids", "Kids1, Kids1, Kids2, Kids3");
        SUB_CATEGORIES.put("Lol", "Lol1, Lol2, Lol3, Lol4");
        SUB_CATEGORIES.put("Music", "Alternative Rock Ambient,Classical,Country,Dance & EDM,Dancehall, Deep House,Disco,Drum & Bass,Dubstep,Electronic,Folk,Hip-hop & Rap,House,Indie,Jazz & Blues"+
        "Latin,Metal,Piano,Pop,R&B & Soul,Reggae,Reggaeton,Rock,Soundtrack,Techno,Trance,Trap,Triphop,World");
        SUB_CATEGORIES.put("Movies", "Movies1, Movies2, Movies3, Movies4, Movies5");
        SUB_CATEGORIES.put("News", "News1, News2");
        SUB_CATEGORIES.put("Photos", "Photos1, Photos2");
        SUB_CATEGORIES.put("Politics", "Politics1, Politics2");
        SUB_CATEGORIES.put("Schools", "Schools1, Schools2");
        SUB_CATEGORIES.put("Sports", "Sports1, Sports2");
        SUB_CATEGORIES.put("Television", "Television1, Television2");
        SUB_CATEGORIES.put("Women", "Women1, Women2");
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
