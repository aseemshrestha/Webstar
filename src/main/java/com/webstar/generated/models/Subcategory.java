package com.webstar.generated.models;

public class Subcategory
{
    private String[] sub;

    private String parent;

    public String[] getSub ()
    {
        return sub;
    }

    public void setSub (String[] sub)
    {
        this.sub = sub;
    }

    public String getParent ()
    {
        return parent;
    }

    public void setParent (String parent)
    {
        this.parent = parent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sub = "+sub+", parent = "+parent+"]";
    }
}
            
             