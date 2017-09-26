package com.webstar.viewmodels;

import java.text.ParseException;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class RepostSubmissionsViewModel
{
    private Long post_id;

    private Long repostedById;

    private String firstName;

    private String lastName;

    private String contents;

    private String category;

    private String subcategory;

    private String videoUrl;

    private String imageUrl;

    private Date submittiedDate;

    private String comments;

    private Date repostedOn;

    private String repostedBy;

    private String repostedOf;

    private String repostedPost;

    private String timeLapse;

    private Long repostOfId;

    private int totalComments;

    private int totalLikes;

    private String username;

    private double avgRatings;

    public String getRepostedPost()
    {
        return repostedPost;
    }

    public void setRepostedPost(String repostedPost)
    {
        this.repostedPost = repostedPost;
    }

    public Long getPost_id()
    {
        return post_id;
    }

    public void setPost_id(Long post_id)
    {
        this.post_id = post_id;
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

    public Date getSubmittiedDate()
    {
        return submittiedDate;
    }

    public Long getRepostedById()
    {
        return repostedById;
    }

    public void setRepostedById(Long repostedById)
    {
        this.repostedById = repostedById;
    }

    public void setSubmittiedDate(Date submittiedDate)
    {
        this.submittiedDate = submittiedDate;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Date getRepostedOn()
    {
        return repostedOn;
    }

    public void setRepostedOn(Date repostedOn)
    {
        this.repostedOn = repostedOn;
    }

    public String getRepostedBy()
    {
        return repostedBy;
    }

    public void setRepostedBy(String repostedBy)
    {
        this.repostedBy = repostedBy;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setTimeLapse(String timeLapse) throws ParseException
    {
        this.timeLapse = timeLapse;
    }

    public Long getRepostOfId()
    {
        return repostOfId;
    }

    public void setRepostOfId(Long repostOfId)
    {
        this.repostOfId = repostOfId;
    }

    public String getTimeLapse()
    {
        return timeLapse;
    }

    public String getRepostedOf()
    {
        return repostedOf;
    }

    public void setRepostedOf(String repostedOf)
    {
        this.repostedOf = repostedOf;
    }

    public int getTotalComments()
    {
        return totalComments;
    }

    public void setTotalComments(int totalComments)
    {
        this.totalComments = totalComments;
    }

    public int getTotalLikes()
    {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes)
    {
        this.totalLikes = totalLikes;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public double getAvgRatings()
    {
        return avgRatings;
    }

    public void setAvgRatings(double avgRatings)
    {
        this.avgRatings = avgRatings;
    }

}
