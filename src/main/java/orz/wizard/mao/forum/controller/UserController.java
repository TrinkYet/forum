package orz.wizard.mao.forum.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = {"/show"}, method = RequestMethod.GET)
    public String showUser(Map<String, Object> model) {
        model.put("user", userService.getUser(1));
        return "show";
    }
    
    @RequestMapping(value = {"/login"})
    public String login(Map<String, Object> model) {
    	return "login";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User registerUser(@Valid User user, 
    		HttpServletResponse response, BindingResult result, Model model) throws BindException{
    	if (result.hasErrors()){
    		throw new BindException(result);
    	}
    	userService.saveUser(user);
    	response.setHeader("Location", "/user/"+user.getId());
    	return user;
    }
}
