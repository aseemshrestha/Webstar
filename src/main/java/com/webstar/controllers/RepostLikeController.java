package com.webstar.controllers;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstar.models.UserLikes;
import com.webstar.models.UserReposts;
import com.webstar.models.UserSubmissions;
import com.webstar.services.ILikesService;
import com.webstar.services.IRepostsService;
import com.webstar.services.ISubmissionService;
import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Utils;
import com.webstar.viewmodels.RepostViewModel;

@Controller
public class RepostLikeController
{
    private static final Logger LOG = LoggerFactory.getLogger(RepostLikeController.class);

    @Autowired
    private ILikesService likesService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISubmissionService submissionService;
    @Autowired
    private IRepostsService repostService;

    
    @RequestMapping( value = "/doLikes", method = { RequestMethod.POST, RequestMethod.GET },
        produces = { "application/json" } )
    
    public @ResponseBody int like(Long postId, HttpServletRequest request)
    {
        String nameEmail = userService.readNameEmailFromCookie(request);
        if (nameEmail.isEmpty()) {
            return -1;
        }
        Long _id = Long.parseLong(nameEmail.split(Constants.COOKIE_SEPARATOR)[2]);

        if (likesService.getLikedById(_id, postId) != null) {
            return -1;
        }
        int l = likesService.getTotalNumberOfLikes(postId);

        UserLikes likes = new UserLikes();
        UserSubmissions submissions = new UserSubmissions();
        likes.setIp(Utils.getClientIp(request));
        likes.setLikedBy(_id);
        likes.setLikedOn(new Date());
        likes.setRemoveLikedOn(null);
        int likeCount = l;
        likeCount = likeCount + 1;
        submissionService.updateTotalLikesCount(likeCount, postId);
        submissions.setId(postId);
        likes.setUserSubmissions(submissions);
        try {
            likesService.saveLikes(likes);
        } catch (Exception ex) {
            LOG.debug("Exception while trying to save likes", ex);
        }
        return likeCount;

    }

    @RequestMapping( value = "/doRepost", method = RequestMethod.GET,
        produces = { "application/json" } )
    public @ResponseBody RepostViewModel repost(Long postId, Model model)
    {
        Optional<UserSubmissions> submissions = submissionService.fetchByPostId(postId);
        RepostViewModel rv = new RepostViewModel();
        if (submissions.isPresent()) {
            UserSubmissions us = submissions.get();
            rv.setAuthor(us.getUserDetails().getFirstName() + " " + us.getUserDetails().getLastName());
            rv.setContents(us.getContents());
            rv.setPostId(us.getId());
            model.addAttribute("userrepost", new UserReposts());
            return rv;
        }
        return null;

    }

    @RequestMapping( value = "/doRepostPost", method = RequestMethod.POST )
    public String repostPost(Long repostpostid, String repostcomment,HttpServletRequest request)
    {
        Optional<UserSubmissions> submissions = submissionService.fetchByPostId(repostpostid);
        String[] cookieValues = userService.readNameEmailFromCookie(request).split(Constants.COOKIE_SEPARATOR);
        Long uid = Long.parseLong(cookieValues[2]);
        String name = cookieValues[1];
        
        if (submissions.isPresent()) {
            try {
                UserReposts userReposts = new UserReposts();
                userReposts.setComments(repostcomment);
                userReposts.setPostId(repostpostid);
                userReposts.setRepostedOn(new Date());
                userReposts.setRepostedBy(uid);
                userReposts.setRepostOf(submissions.get().getUserDetails().getFirstName()+" " +submissions.get().getUserDetails().getLastName());
                userReposts.setRepostedByName(name);
                userReposts.setRepostedPost(submissions.get().getContents());
                userReposts.setIp(Utils.getClientIp(request));
                userReposts.setRepostedByUsername(submissions.get().getUserDetails().getUsername());
                userReposts.setRepostOfId(submissions.get().getUserDetails().getId());
                int totalReposts = repostService.getTotalNumberOfReposts(repostpostid);
                totalReposts = totalReposts + 1;
                submissionService.updateTotalRepostsCount(totalReposts, repostpostid);
                repostService.saveReposts(userReposts);
            } catch (Exception ex) {
                LOG.debug("Exception while reposting :",ex);
            }
        }
        return "redirect:/byuser?uid=" + uid + "&offset=0&repost=0";

    }
}
