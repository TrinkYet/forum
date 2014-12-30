package orz.wizard.mao.forum.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.MessageService;
import orz.wizard.mao.forum.service.TopicService;
import orz.wizard.mao.forum.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageService messageService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLoginPage() {
    	return "admin/login";
    }
    
    @RequestMapping(value ={"/home"}, method = RequestMethod.GET)
    public String showAdminPage(HttpSession session, Map<String, Object> model){
    	if(session.getAttribute("user") == null)
    		return "redirect:login";
    	User user = (User)session.getAttribute("user");
    	if(user.getStatus() != "admin")
    		return "redirect:login";
    	List<User> allUser = userService.getAllUser();
        List<Topic> allTopic = topicService.getAllTopic();
        List<Group> allGroup = groupService.getAllGroup();
        model.put("allUser", allUser);
        model.put("allTopic", allTopic);
        model.put("allGroup", allGroup);
    	return "admin/adminhome";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Map<String, Object> model) {
        if (username == null || !username.equals("admin@fudangroup.com")) {
            return "redirect:login";
        }
        if (password == null || !password.equals("admin")) {
            return "redirect:login";
        }
        User user = new User();
        user.setStatus("admin");
        user.setNickname("管理员");
        session.setAttribute("user", user);
        List<User> allUser = userService.getAllUser();
        List<Topic> allTopic = topicService.getAllTopic();
        List<Group> allGroup = groupService.getAllGroup();
        model.put("allUser", allUser);
        model.put("allTopic", allTopic);
        model.put("allGroup", allGroup);
        return "admin/adminhome";
    }
    
    @RequestMapping(value = {"/logout"})
    public String doLogout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:login";
    }
    
	@RequestMapping(value = {"/forbid/{userId}"}, method = RequestMethod.POST)
    public @ResponseBody String forbid(@PathVariable long userId, HttpSession session) {
        userService.forbid(userId);
        return "success";
    }
	
	@RequestMapping(value = {"/unforbid/{userId}"}, method = RequestMethod.POST)
    public @ResponseBody String unforbid(@PathVariable long userId, HttpSession session) {
        userService.unforbid(userId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/topic/{topicId}"}, method = RequestMethod.POST)
    public @ResponseBody String delTopic(@PathVariable long topicId, HttpSession session) {
        topicService.delete(topicId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/group/{groupId}"}, method = RequestMethod.POST)
    public @ResponseBody String delGroup(@PathVariable long groupId, HttpSession session) {
        groupService.delete(groupId);
        return "success";
    }
    
    @RequestMapping(value = {"/del/comment/{commentId}"}, method = RequestMethod.POST)
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
    
    @RequestMapping(value = {"/sendmsg/{userId}"}, method = RequestMethod.POST)
    public @ResponseBody String sendMsg(@PathVariable long userId, @RequestParam String content, HttpSession session) {
        messageService.sendMsgToUser(userId, content);
        return "success";
    }
    
    @RequestMapping(value = {"/sendmsg"}, method = RequestMethod.POST)
    public @ResponseBody long sendMsg(@RequestParam String content, HttpSession session) {
        return messageService.sendMsgToAll(content);
    }
}
