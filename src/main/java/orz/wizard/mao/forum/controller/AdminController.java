package orz.wizard.mao.forum.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.TopicService;
import orz.wizard.mao.forum.service.UserService;
import orz.wizard.mao.forum.util.GenerateLinkUtil;
import orz.wizard.mao.forum.util.ImageUtil;
import orz.wizard.mao.forum.util.MailUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLoginPage() {
    	return "admin/login";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public @ResponseBody String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (username == null || !username.equals("admin@fudangroup.com")) {
            return "failed";
        }
        if (password == null || !password.equals("admin")) {
            return "failed";
        }
        User user = new User();
        user.setStatus("admin");
        session.setAttribute("user", user);
        return "redirect:admin/home";
    }
    
    @RequestMapping(value = {"/forbid/{userId}"})
    public @ResponseBody String forbid(@PathVariable long userId, HttpSession session) {
        userService.forbid(userId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/topic/{topicId}"})
    public @ResponseBody String delTopic(@PathVariable long topicId, HttpSession session) {
        topicService.delete(topicId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/group/{groupId}"})
    public @ResponseBody String delGroup(@PathVariable long groupId, HttpSession session) {
        groupService.delete(groupId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/comment/{commentId}"})
    public @ResponseBody String delComment(@PathVariable long commentId, HttpSession session) {
        topicService.deleteCmt(commentId);
        return "success";
    }
    
    @RequestMapping(value = {"/query/alltopic"})
    public @ResponseBody List<Topic> allTopic(HttpSession session) {
        List<Topic> allTopic = topicService.getAllTopic();
        return allTopic;
    }
    
    @RequestMapping(value = {"/query/allgroup"})
    public @ResponseBody List<Group> allGroup(HttpSession session) {
        List<Group> allGroup = groupService.getAllGroup();
        return allGroup;
    }
    
    @RequestMapping(value = {"/query/alluser"})
    public @ResponseBody List<User> allUser(HttpSession session) {
        List<User> allUser = userService.getAllUser();
        return allUser;
    }
    
    @RequestMapping(value = {"/count/topic"})
    public @ResponseBody long countTopic(HttpSession session) {
        return topicService.count();
    }
    
    @RequestMapping(value = {"/count/comment"})
    public @ResponseBody long countComment(HttpSession session) {
        return topicService.countComment();
    }
    
    @RequestMapping(value = {"/count/group"})
    public @ResponseBody long countGroup(HttpSession session) {
        return groupService.count();
    }
    
    @RequestMapping(value = {"/count/user"})
    public @ResponseBody long countUser(HttpSession session) {
        return userService.count();
    }
}
