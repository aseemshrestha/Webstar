package com.webstar.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.webstar.util.Constants;
import com.webstar.util.TimeLapse;

@Entity
@Table( indexes = { @Index( name = "IDX_USER_COMMENTS", columnList = "POST_ID" ) } )
public class UserComments
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;

    @Size( min = 1, max = 200, message = "required." )
    private String comments;

    private String videoUrl;

    private String imageUrl;

    private String Ip;

    @Temporal( TemporalType.TIMESTAMP )
    private Date commentedOn;

    @Temporal( TemporalType.TIMESTAMP )
    private Date updatedDate;

    @Temporal( TemporalType.TIMESTAMP )
    private Date deletedDate;

    private long commentedBy;
    
    private String commentedByName; 

    @ManyToOne
    @JoinColumn( name = "POST_ID" )
    private UserSubmissions userSubmissions;

    public UserComments()
    {

    }

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

    public String getVideoUrl()
    {
        String videoId = "";
        if (videoUrl != null && !videoUrl.isEmpty()) {
            if (videoUrl.contains("youtube")) {
                if (videoUrl.contains("?v=")) {
                    videoId = "$$-" + videoUrl.substring(videoUrl.indexOf("?v=") + 3, videoUrl.length());
                }
                if (videoUrl.contains("/embed/")) {
                    videoId = "$$-" + videoUrl.split("/embed/")[1];
                }
            }
            if (videoUrl.contains("vimeo")) {
                videoId = "##-" + videoUrl.split("vimeo.com/")[1];
            }
        }
        return videoId;
    }

    public void setVideoUrl(String videoUrl)
    {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl()
    {
        if (imageUrl != null) {
            int index = imageUrl.indexOf(Constants.IMG_PATH_FOLDER);
            int l = imageUrl.length();
            return imageUrl.substring(index, l).replace("\\", "/");
        }
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

    public Date getCommentedOn()
    {
        return commentedOn;
    }

    public void setCommentedOn(Date commentedOn)
    {
        this.commentedOn = commentedOn;
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

    public UserSubmissions getUserSubmissions()
    {
        return userSubmissions;
    }

    public void setUserSubmissions(UserSubmissions userSubmissions)
    {
        this.userSubmissions = userSubmissions;
    }

    public String getTimeLapse() throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date dateNow = new Date();

        String n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow);
        String n1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getCommentedOn());
        return TimeLapse.toRelative(format.parse(n1), format.parse(n));

    }

    public long getCommentedBy()
    {
        return commentedBy;
    }

    public void setCommentedBy(long commentedBy)
    {
        this.commentedBy = commentedBy;
    }

    public String getCommentedByName()
    {
        return commentedByName;
    }

    public void setCommentedByName(String commentedByName)
    {
        this.commentedByName = commentedByName;
    }
    
   

}
