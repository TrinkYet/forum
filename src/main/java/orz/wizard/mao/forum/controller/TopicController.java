package orz.wizard.mao.forum.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String showUserAll(@PathVariable long id, Map<String, Object> model) {
        model.put("topic", topicService.getTopic(id));
        return "topic/show";
    }
}
