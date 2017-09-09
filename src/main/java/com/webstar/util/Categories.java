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
        CATEGORIES.put("Schools", "Schools");
        CATEGORIES.put("Sports", "Sports");
        CATEGORIES.put("Women", "Women");
        return CATEGORIES;
    }

    public static Map<String, String> getSubCategories()
    {
        SUB_CATEGORIES.put("Any", "Any");
        SUB_CATEGORIES.put("Gaming", "Action,Fighting,Music & Party,Role-Playing,Shooter,Simulation,Strategy,Sports");
        SUB_CATEGORIES.put("Kids", "Cartoons,Educational,LOL,Sports,Toys,Other");
        SUB_CATEGORIES.put("Lol", "Lol1, Lol2, Lol3, Lol4");
        SUB_CATEGORIES.put("Music", "Alternative Rock Ambient,Classical,Country,Dance & EDM,Dancehall, Deep House,Disco,Drum & Bass,Dubstep,Electronic,Folk,Hip-hop & Rap,House,Indie,Jazz & Blues,"+
        "Latin,Metal,Piano,Pop,R&B & Soul,Reggae,Reggaeton,Rock,Soundtrack,Techno,Trance,Trap,Triphop,World");
        SUB_CATEGORIES.put("Movies", "Action,Adventure,Animation,Biography,Comedy,Crime,Documentary,Drama,Family,Fantasy,Game-Show,History,Horror,Musical,Mystery,Reality-TV,Romance,Sci-Fi,"+
        "Tv Shows,Sports,Talk-Show,Thriller,War,Western");
        SUB_CATEGORIES.put("News", "Breaking News,Local News, State News, Global News");
        SUB_CATEGORIES.put("Schools", "College,High School,Elementary");
        SUB_CATEGORIES.put("Sports", "Football,Basketball,Baseball,Boxing,Hockey,MMA,Motor,Soccer,Other");
         SUB_CATEGORIES.put("Women", "Beauty,LOL,Gossip,Recipes,Romance,Sports,Other");
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
