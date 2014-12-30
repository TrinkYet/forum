package orz.wizard.mao.forum.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.SysMsg;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.MessageService;

@Controller
@RequestMapping("/msg")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
	
	@RequestMapping(value = {"/topic"}, method = RequestMethod.GET)
	public @ResponseBody List<Topic> getTopicMsg(HttpSession session, Map<String, Object> model){
	    User user = (User) session.getAttribute("user");
	    if (user != null) {
	        return messageService.getTopicMsgList(user.getUserId());
	    }
	    return null;
	}
	
	@RequestMapping(value = {"/topic/read"}, method = RequestMethod.POST)
    public @ResponseBody String readTopic(HttpSession session, @RequestParam long topicId){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            messageService.setReadTopic(topicId, user.getUserId());
            return "success";
        }
        return null;
    }
	
	@RequestMapping(value = {"/cmt"}, method = RequestMethod.GET)
    public @ResponseBody List<Comment> getCommentMsg(HttpSession session, Map<String, Object> model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return messageService.getCmtMsgList(user.getUserId());
        }
        return null;
    }
	
	@RequestMapping(value = {"/cmt/read"})
    public @ResponseBody String readCmt(HttpSession session, @RequestParam long cmtId){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            messageService.setReadCmt(cmtId, user.getUserId());
            return "success";
        }
        return null;
    }
	
	@RequestMapping(value = {"/sys"}, method = RequestMethod.GET)
    public @ResponseBody List<SysMsg> getSysMsg(HttpSession session, Map<String, Object> model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return messageService.getSysMsgList(user.getUserId());
        }
        return null;
    }
	
	@RequestMapping(value = {"/sys/read"})
    public @ResponseBody String readSysMsg(HttpSession session, @RequestParam long msgId){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            messageService.setReadSysMsg(msgId);
            return "success";
        }
        return null;
    }
}
