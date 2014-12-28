package orz.wizard.mao.forum.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.TopicService;

@Controller
@RequestMapping("/group")
public class GroupController {
    
    @Autowired
    private GroupService groupService;
    @Autowired
    private TopicService topicService;
    
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String showGroupTopic(HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if(user != null)
        	model.put("groupTopicList", topicService.getGroupTopicList(user.getUserId()));
        return "group/groupTopic";
    }
    
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String showCreateGroup(HttpSession session) {
        return "group/createGroup";
    }
    
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String processCreateGroup(@Valid Group group, HttpSession session, Map<String, Object> model, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        User user = (User) session.getAttribute("user");
        group.setUserId(user.getUserId());
        groupService.insertGroup(group);
        return "redirect:/group/" + group.getGroupId();
    }
    
    @RequestMapping(value = {"/{groupId}"}, method = RequestMethod.GET)
    public String showGroup(@PathVariable long groupId, Map<String, Object> model) {
        Group group = groupService.getGroup(groupId);
        model.put("group", group);
        List<Topic> topicList = topicService.getTopicList(groupId);
        model.put("topicList", topicList);
        model.put("recentUserList", groupService.getRecentUserList(groupId));
        model.put("userCount", groupService.getUserCount(groupId));
        return "group/groupHome";
    }
    
    @RequestMapping(value = {"/{groupId}/modify"}, method = RequestMethod.GET)
    public String showModifyGroup(@PathVariable long groupId, HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "noPermission";
        }
        Group group = groupService.getGroup(groupId);
        if (group.getUserId() != user.getUserId()) {
            return "noPermission";
        }
        model.put("group", group);
        return "group/modifyGroup";
    }
    
    @RequestMapping(value = {"/{groupId}/modify"}, method = RequestMethod.POST)
    public String modifyGroup(@PathVariable long groupId, @Valid Group group, HttpSession session) {
        User user = (User) session.getAttribute("user");
        group.setUserId(user.getUserId());
        groupService.saveGroup(group);
        return "redirect:/group/" + group.getGroupId();
    }
    
    @RequestMapping(value = {"/{groupId}/members"}, method = RequestMethod.GET)
    public String showMembers(@PathVariable long groupId, Map<String, Object> model) {
        model.put("userList", groupService.getUserList(groupId));
        return "group/members";
    }
    
    @RequestMapping(value = {"/{groupId}/join"})
    public @ResponseBody String joinGroup(@PathVariable long groupId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        groupService.joinGroup(groupId, user.getUserId());
        return "success";
    }
    
    @RequestMapping(value = {"/{groupId}/quit"})
    public @ResponseBody String quitGroup(@PathVariable long groupId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        groupService.quitGroup(groupId, user.getUserId());
        return "success";
    }
    
    @RequestMapping(value = {"/{groupId}/is_joined"})
    public @ResponseBody boolean isJoined(@PathVariable long groupId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return groupService.isJoined(groupId, user.getUserId());
    }
    
    @RequestMapping(value = {"/{groupId}/new_topic"}, method = RequestMethod.GET)
    public String newTopic(@PathVariable long groupId, Map<String, Object> model) {
        return "topic/newTopic";
    }
    
    @RequestMapping(value = {"/{groupId}/new_topic"}, method = RequestMethod.POST)
    public String postNewTopic(@PathVariable long groupId, @Valid Topic topic, HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        topic.setUserId(user.getUserId());
        topicService.insertTopic(topic);
        return "redirect:/topic/" + topic.getTopicId();
    }
    
    @RequestMapping(value = {"/search"})
    public String search(@RequestParam String cat, @RequestParam String q, Map<String, Object> model) {
    	String qr = "";
    	try {
		    qr = new String(q.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (cat.endsWith("group")) {
            model.put("result", groupService.searchGroup(qr));
        } else if (cat.endsWith("topic")) {
            model.put("result", topicService.searchTopic(qr));
        }
        model.put("cat", cat);
        model.put("q", qr);
        return "group/result";
    }
}
