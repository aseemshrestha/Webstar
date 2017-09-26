package com.webstar.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webstar.models.UserRatings;
import com.webstar.services.IRatingService;
import com.webstar.services.ISubmissionService;
import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Utils;

@Controller
public class RatingController
{
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRatingService ratingService;
    @Autowired
    private ISubmissionService submissionService;

    @RequestMapping( value = "/postrating", method = RequestMethod.POST )
    public String rateThePost(double rating, long postId, HttpServletRequest request)
    {
        String nameEmail = userService.readNameEmailFromCookie(request);
         if (nameEmail.isEmpty()) {
            return "redirect:/";
        }
        UserRatings ur = new UserRatings();
        NumberFormat formatter = new DecimalFormat("#0.00");
        String[] values = nameEmail.split(Constants.COOKIE_SEPARATOR);
        ur.setPostId(postId);
        ur.setRating(Double.parseDouble(formatter.format(rating)));
        ur.setRatedBy(Long.parseLong(values[2]));
        ur.setRatedByUsername(values[3]);
        ur.setRatedOn(new Date());
        ur.setIpAddress(Utils.getClientIp(request));
        try {
            ratingService.saveRatings(ur);
            double avgRating = ratingService.getAverageRating(postId);
            submissionService.updateAvgRatings(avgRating, new Date(), postId);
            UserController.postsMap.clear();
        } catch (Exception ex) {
            LOG.debug("Error tryign to save the ratings:", ex);
        }
        return "redirect:/myhomepage";
    }

}
