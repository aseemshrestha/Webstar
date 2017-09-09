package com.webstar.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webstar.models.UserComments;
import com.webstar.models.UserDetails;
import com.webstar.models.UserSubmissions;
import com.webstar.services.ICommentService;
import com.webstar.services.ISubmissionService;
import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Utils;
import com.webstar.util.Views;

@Controller
public class CommentController
{
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentService commentService;
    @Autowired
    private ISubmissionService submissionService;

    @RequestMapping( value = "/postcomment", method = { RequestMethod.POST, RequestMethod.GET } )
    public String postcomment(
        @RequestParam( "file" ) MultipartFile file,
        @ModelAttribute( "usercomments" ) UserComments usercomments,
        HttpServletRequest request, String postId)
    {
        String nameEmail = userService.readNameEmailFromCookie(request);
        long postid = Long.parseLong(postId);

        if (nameEmail.isEmpty()) {
            return "redirect:/";
        }
        try {

            if (!file.isEmpty()) {
                try {
                    File fileSaveDir = new File(Constants.IMG_PATH);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdir();
                    }
                    String absPath = request.getServletContext().getRealPath("/");
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(absPath + Constants.IMG_PATH + file.getOriginalFilename());
                    usercomments.setImageUrl(path.toString());
                    Files.write(path, bytes);
                } catch (Exception ex) {
                    LOG.debug("[CommentController]Exception saving image :", ex);
                }
            }

            UserSubmissions usersub = new UserSubmissions();
            usercomments.setIp(Utils.getClientIp(request));
            usercomments.setUpdatedDate(new Date());
            usercomments.setCommentedOn(new Date());
            usersub.setId(postid);
            usercomments.setUserSubmissions(usersub);
            usercomments.setCommentedBy(Long.parseLong(nameEmail.split(Constants.COOKIE_SEPARATOR)[2]));
            usercomments.setCommentedByName(nameEmail.split(Constants.COOKIE_SEPARATOR)[1]);
            int totalComments = commentService.getTotalNumberComments(postid);
            totalComments = totalComments + 1;
            submissionService.updateTotalCommentCount(totalComments, postid);
            commentService.save(usercomments);

        } catch (Exception ex) {
            LOG.debug("[CommentController  - exception while saving comment into db]", ex);
        }
        return "redirect:/getcomments?postid=" + postid + "&offset=0";
    }

    @RequestMapping( value = "/getcomments", method = { RequestMethod.POST, RequestMethod.GET } )
    public String showComments(Long postid, int offset, HttpServletRequest request, Model model)
        throws ParseException
    {

        Optional<List<UserComments>> commentsList =
            commentService.fetchCommentsByPostId(postid, Constants.BLOCKSIZE, offset);
        model.addAttribute("commentsList", commentsList.get());
        if (commentsList.get().size() > 0) {
            UserDetails details = commentsList.get().get(0).getUserSubmissions().getUserDetails();
            UserSubmissions submissions = commentsList.get().get(0).getUserSubmissions();
            model.addAttribute("postedby",
                               details.getFirstName() + " " + details.getLastName() + " " + submissions.getTimeLapse());
            model.addAttribute("post", submissions.getContents());
            model.addAttribute("category", submissions.getCategory() + " - " + submissions.getSubcategory());
        }
        int totalComments = commentService.getTotalNumberComments(postid);
        model.addAttribute("totalComments", totalComments);
        model.addAttribute("usersubmissions", new UserSubmissions());
        model.addAttribute("usercomments", new UserComments());
        return Views.COMMENTS;
    }

    @RequestMapping( value = "/loadmorecomments", method = RequestMethod.GET, produces = { "application/json" } )
    public @ResponseBody List<UserComments> getMoreComments(Long postid, int offset, HttpServletRequest request,
        Model model)
        throws ParseException
    {
        if (offset != 0)
            offset = Constants.BLOCKSIZE * offset;
        return commentService.fetchCommentsByPostId(postid, Constants.BLOCKSIZE, offset).get();

    }

    @RequestMapping( value = "/bycategorypage", method = RequestMethod.GET )
    public ModelAndView filterByCateogryPage(@RequestParam( "category" ) String category,
        @RequestParam( "offset" ) int offset,
        Model model, HttpServletRequest request, ModelAndView modelAndView)
    {
        if (offset != 0) {
            offset = Constants.BLOCKSIZE * offset;
        }
        modelAndView.setViewName(Views.CATEGORIES);
        modelAndView.addObject("categoriescomments",
                               submissionService.fetchByCategoryDesc(category, Constants.BLOCKSIZE, offset).get());
        modelAndView.addObject("usersubmissions", new UserSubmissions());
        modelAndView.addObject("usercomments", new UserComments());

        return modelAndView;
    }

    @RequestMapping( value = "/loadcomments", method = RequestMethod.GET, produces = { "application/json" } )
    public @ResponseBody List<UserComments> loadcomments(@RequestParam( "postid" ) Long postid,
        @RequestParam( "offset" ) int offset, HttpServletRequest request, Model model)
    {
        Optional<List<UserComments>> commentsList =
            commentService.fetchCommentsByPostId(postid, Constants.BLOCKSIZE, offset);
        return commentsList.get();
    }

}
