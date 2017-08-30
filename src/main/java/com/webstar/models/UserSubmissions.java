package com.webstar.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table( indexes = { @Index( name = "IDX_USER_SUBMISSIONS", columnList = "email" ) } )
public class UserSubmissions
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    @Size( min = 1, max = 200, message = "required." )
    private String contents;

    @Size( min = 1, max = 50, message = "required." )
    private String category;

    @Size( min = 1, max = 50, message = "required." )
    private String subcategory;

    private String videoUrl;

    private String imageUrl;

    private String Ip;
    @Temporal( TemporalType.TIMESTAMP )

    private Date submittiedDate;
    @Temporal( TemporalType.TIMESTAMP )

    private Date updatedDate;
    @Temporal( TemporalType.TIMESTAMP )

    private Date deletedDate;

    private String isActivePost;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "email" )
    private UserDetails userDetail;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getContents()
    {
        return contents;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getSubcategory()
    {
        return subcategory;
    }

    public void setSubcategory(String subcategory)
    {
        this.subcategory = subcategory;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getIp()
    {
        return Ip;
    }

    public void setIp(String ip)
    {
        Ip = ip;
    }

    public Date getSubmittiedDate()
    {
        return submittiedDate;
    }

    public void setSubmittiedDate(Date submittiedDate)
    {
        this.submittiedDate = submittiedDate;
    }

    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public Date getDeletedDate()
    {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate)
    {
        this.deletedDate = deletedDate;
    }

    public String getIsActivePost()
    {
        return isActivePost;
    }

    public void setIsActivePost(String isActivePost)
    {
        this.isActivePost = isActivePost;
    }

    public UserDetails getUserDetail()
    {
        return userDetail;
    }

    public void setUserDetail(UserDetails userDetail)
    {
        this.userDetail = userDetail;
    }

}
