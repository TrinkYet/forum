package orz.wizard.mao.forum.controller;

import java.util.Map;

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

import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLogin() {
    	return "user/login";
    }
    
    @RequestMapping(value = {"/put_objects_in_session"}, method = RequestMethod.GET)
    public String putObjectsInSession(HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        UserInfo userInfo = userService.getUserInfo(user.getId());
        session.setAttribute("user", user);
        session.setAttribute("userInfo", userInfo);
        return "redirect:/home";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showRegister() {
        return "user/register";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String processRegister(@Valid User user, HttpSession session, BindingResult result) throws BindException{
    	if (result.hasErrors()) {
    		throw new BindException(result);
    	}
    	userService.saveUser(user);
    	session.setAttribute("user", user);
    	return "redirect:";
    }
    
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String showUserInfo() {
        return "user/userinfo";
    }
    
    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String saveUserInfo(@Valid UserInfo userInfo, HttpSession session, BindingResult result) throws BindException{
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        User user = (User) session.getAttribute("user");
        userService.saveUserInfo(userInfo, user.getId());
        return "redirect:/home";
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showUserAll(@PathVariable long id, Map<String, Object> model) {
        model.put("groupList", groupService.getGroupList(id));
        model.put("userInfo", userService.getUserInfo(id));
        return "user/userall";
    }
}
