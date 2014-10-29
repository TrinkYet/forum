package orz.wizard.mao.forum.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.GroupService;
import orz.wizard.mao.forum.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
    
    @Autowired
    private TopicService topicService;
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showTopic(@PathVariable long id, Map<String, Object> model) {
        model.put("topic", topicService.getTopic(id));
        return "topic/topicHome";
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
