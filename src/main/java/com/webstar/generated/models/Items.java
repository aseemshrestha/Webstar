package com.webstar.generated.models;

public class Items
{
    private Subcategory[] Item;

    public Subcategory[] getItem ()
    {
        return Item;
    }

    public void setItem (Subcategory[] Item)
    {
        this.Item = Item;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Item = "+Item+"]";
    }
}
    