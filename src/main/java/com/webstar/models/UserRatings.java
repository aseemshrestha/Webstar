package com.webstar.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table( indexes = { @Index( name = "IDX_USER_RATING", columnList = "id,postId" ) } )
public class UserRatings
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long Id;
    private double rating;
    private long postId;
    private long ratedBy;
    private String ratedByUsername;
    private String ipAddress;
    private Date ratedOn;

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public long getPostId()
    {
        return postId;
    }

    public void setPostId(long postId)
    {
        this.postId = postId;
    }

    public long getRatedBy()
    {
        return ratedBy;
    }

    public void setRatedBy(long ratedBy)
    {
        this.ratedBy = ratedBy;
    }

    public String getRatedByUsername()
    {
        return ratedByUsername;
    }

    public void setRatedByUsername(String ratedByUsername)
    {
        this.ratedByUsername = ratedByUsername;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public Date getRatedOn()
    {
        return ratedOn;
    }

    public void setRatedOn(Date ratedOn)
    {
        this.ratedOn = ratedOn;
    }

}
