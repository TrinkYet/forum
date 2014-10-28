package orz.wizard.mao.forum.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;
import orz.wizard.mao.forum.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLogin() {
    	return "login";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showRegister() {
        return "register";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(@Valid User user, 
    		HttpSession session, HttpServletResponse response, BindingResult result, Model model) throws BindException{
    	if (result.hasErrors()){
    		throw new BindException(result);
    	}
    	userService.saveUser(user);
    	session.setAttribute("user", user);
    	return "redirect:" + user.getId();
    }
    
    @RequestMapping(value = {"/saveinfo"}, method = RequestMethod.GET)
    public String showAnInteger(){
        return "redirect:/index.html";
    }
    
    @RequestMapping(value = {"/saveinfo"}, method = RequestMethod.POST)
    public String saveUserInfo(@Valid UserInfo userInfo,
            HttpSession session, BindingResult result, Model model) throws BindException{
        if (result.hasErrors()){
            throw new BindException(result);
        }
        User user = (User) session.getAttribute("user");
        userService.saveUserInfo(userInfo, user.getId());
        return "redirect:home";
    }
    
    @RequestMapping("/{id}")
    public String userInfo(@PathVariable long id, Map<String, Object> model) {
        System.out.println(id);
        model.put("userInfo", userService.getUserInfo(id));
        return "userinfo";
    }
    
    @RequestMapping("/home")
    public String userHome(HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        UserInfo userInfo = userService.getUserInfo(user.getId());
        session.setAttribute("user", user);
        session.setAttribute("userInfo", userInfo);
        return "home";
    }
}
