package com.webstar.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( indexes = { @Index( name = "IDX_USER_LIKES", columnList = "POST_ID" ) } )
public class UserLikes
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    private Long likedBy;

    private String Ip;

    @Temporal( TemporalType.TIMESTAMP )
    private Date likedOn;

    @Temporal( TemporalType.TIMESTAMP )
    private Date removeLikedOn;

    @ManyToOne
    @JoinColumn( name = "POST_ID" )
    private UserSubmissions userSubmissions;

    public UserLikes()
    {}

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getIp()
    {
        return Ip;
    }

    public void setIp(String ip)
    {
        Ip = ip;
    }

    public Date getLikedOn()
    {
        return likedOn;
    }

    public void setLikedOn(Date likedOn)
    {
        this.likedOn = likedOn;
    }

    public Date getRemoveLikedOn()
    {
        return removeLikedOn;
    }

    public void setRemoveLikedOn(Date removeLikedOn)
    {
        this.removeLikedOn = removeLikedOn;
    }

    public UserSubmissions getUserSubmissions()
    {
        return userSubmissions;
    }

    public void setUserSubmissions(UserSubmissions userSubmissions)
    {
        this.userSubmissions = userSubmissions;
    }

    public Long getLikedBy()
    {
        return likedBy;
    }

    public void setLikedBy(Long likedBy)
    {
        this.likedBy = likedBy;
    }

}
