package com.webstar.generated.models;

public class Category
{
    private Subcategory subcategory;

    private String name;

    public Subcategory getSubcategory ()
    {
        return subcategory;
    }

    public void setSubcategory (Subcategory subcategory)
    {
        this.subcategory = subcategory;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subcategory = "+subcategory+", name = "+name+"]";
    }
}
            
    