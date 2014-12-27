package orz.wizard.mao.forum.controller;

import java.util.HashMap;
import java.util.Iterator;
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

import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
    
    @Autowired
    private TopicService topicService;
    
    @RequestMapping(value = {"/{topicId}"}, method = RequestMethod.GET)
    public String showTopic(@PathVariable long topicId, Map<String, Object> model) {
        model.put("topic", topicService.getTopic(topicId));
        List<Comment> cmtList = topicService.getCommentList(topicId);
        Map<Long, Comment> cmtMap = new HashMap<Long, Comment>();
        Iterator<Comment> it = cmtList.iterator();
        while (it.hasNext()) {
            Comment comment = it.next();
            cmtMap.put(comment.getCommentId(), comment);
        }
        model.put("cmtMap", cmtMap);
        return "topic/topicHome";
    }
    
    @RequestMapping(value = {"/{topicId}/post_comment"}, method = RequestMethod.POST)
    public @ResponseBody Comment postComment(@PathVariable long topicId, @Valid Comment comment) {
        topicService.insertComment(comment);
        return comment;
    }
    
    @RequestMapping(value = {"/{topicId}/modify"}, method = RequestMethod.GET)
    public String showModifyTopic(@PathVariable long topicId, Map<String, Object> model) {
        model.put("topic", topicService.getTopic(topicId));
        return "topic/modifyTopic";
    }
    
    @RequestMapping(value = {"/{topicId}/modify"}, method = RequestMethod.POST)
    public String modifyTopic(@PathVariable long topicId, @Valid Topic topic, HttpSession session) {
        User user = (User) session.getAttribute("user");
        topic.setUserId(user.getUserId());
        topicService.saveTopic(topic);
        return "redirect:/topic/" + topic.getTopicId();
    }
}
