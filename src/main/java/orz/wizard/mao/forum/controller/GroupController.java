package orz.wizard.mao.forum.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String showMyGroupTopic(HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        // TODO
        return "group/myGroup";
    }
    
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String showCreateGroup(HttpSession session, Map<String, Object> model) {
        return "group/createGroup";
    }
    
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String processCreateGroup(@Valid Group group, HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        Group newGroup = groupService.saveGroup(group, user.getId());
        return "redirect:" + newGroup.getId();
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showGroup(@PathVariable long id, Map<String, Object> model) {
        Group group = groupService.getGroup(id);
        model.put("group", group);
        List<Topic> topicList = topicService.getTopicList(id);
        model.put("topicList", topicList);
        // TODO: recent join users
        return "group/groupHome";
    }
    
    @RequestMapping(value = {"/join/{groupId}"}, method = RequestMethod.POST)
    public @ResponseBody String joinGroup(HttpSession session, @PathVariable long groupId) {
        User user = (User) session.getAttribute("user");
        groupService.joinGroup(user.getId(), groupId);
        return "success";
    }
    
    @RequestMapping(value = {"/can_join/{groupId}"}, method = RequestMethod.POST)
    public @ResponseBody boolean canJoin(HttpSession session, @PathVariable long groupId) {
        User user = (User) session.getAttribute("user");
        return !groupService.isJoined(user.getId(), groupId);
    }
    
    @RequestMapping(value = {"/{id}/new_topic"}, method = RequestMethod.GET)
    public String newTopic(@PathVariable long id, Map<String, Object> model) {
        return "topic/newTopic";
    }
    
    @RequestMapping(value = {"/{id}/new_topic"}, method = RequestMethod.POST)
    public String processNewTopic(@PathVariable long groupId, @Valid Topic topic, HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        Topic newTopic = topicService.saveTopic(groupId, user.getId(), topic);
        return "redirect:topic/" + newTopic.getId();
    }
    
}
