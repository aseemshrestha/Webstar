package com.webstar.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.webstar.util.TimeLapse;

@Entity
public class UserReposts
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    private String comments;

    private Long repostedBy;

    private String repostedByUsername;

    private String repostedByName;

    private Long postId;

    @Temporal( TemporalType.TIMESTAMP )
    private Date repostedOn;

    private String ip;

    private String repostOf;

    private String repostedPost;

    private String timeLapse;
    
    private Long repostOfId;

    public UserReposts()
    {}

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Long getRepostedBy()
    {
        return repostedBy;
    }

    public void setRepostedBy(Long repostedBy)
    {
        this.repostedBy = repostedBy;
    }

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public Date getRepostedOn()
    {
        return repostedOn;
    }

    public void setRepostedOn(Date repostedOn)
    {
        this.repostedOn = repostedOn;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getRepostedByName()
    {
        return repostedByName;
    }

    public void setRepostedByName(String repostedByName)
    {
        this.repostedByName = repostedByName;
    }

    public String getRepostOf()
    {
        return repostOf;
    }

    public void setRepostOf(String repostOf)
    {
        this.repostOf = repostOf;
    }

    public String getRepostedPost()
    {
        return repostedPost;
    }

    public void setRepostedPost(String repostedPost)
    {
        this.repostedPost = repostedPost;
    }

    public String getTimeLapse() throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date dateNow = new Date();

        String n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow);
        String n1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getRepostedOn());
        return TimeLapse.toRelative(format.parse(n1), format.parse(n));

    }

    public Long getRepostOfId()
    {
        return repostOfId;
    }

    public void setRepostOfId(Long repostOfId)
    {
        this.repostOfId = repostOfId;
    }

    public String getRepostedByUsername()
    {
        return repostedByUsername;
    }

    public void setRepostedByUsername(String repostedByUsername)
    {
        this.repostedByUsername = repostedByUsername;
    }

}
