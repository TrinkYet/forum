package orz.wizard.mao.forum.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.TopicService;

@Controller
@RequestMapping("/group")
public class GroupController {
    
    @Autowired
    private GroupService groupService;
    @Autowired
    private TopicService topicService;
    
    @RequestMapping(value = {"/", "/explore"}, method = RequestMethod.GET)
    public String explore() {
        return "group/explore";
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showGroup(@PathVariable long id, Map<String, Object> model) {
        Group group = groupService.getGroup(id);
        model.put("group", group);
        List<Topic> topicList = topicService.getTopicList(id);
        model.put("topicList", topicList);
        return "group/show";
    }
}
