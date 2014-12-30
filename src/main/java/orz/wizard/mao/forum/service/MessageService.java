package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.MessageDao;
import orz.wizard.mao.forum.dao.UserDao;
import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.SysMsg;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;

@Service
public class MessageService {
    
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;

    public List<Topic> getTopicMsgList(long userId) {
        return messageDao.getTopicMsgList(userId);
    }

    public void setReadTopic(long topicId, long userId) {
        messageDao.setReadTopic(topicId, userId);
    }

    public List<Comment> getCmtMsgList(long userId) {
        return messageDao.getCmtMsgList(userId);
    }
    
    public void setReadCmt(long cmtId, long userId) {
        messageDao.setReadCmt(cmtId, userId);
    }

    public List<SysMsg> getSysMsgList(long userId) {
        return messageDao.getSysMsgList(userId);
    }

    public void setReadSysMsg(long msgId) {
        messageDao.setReadSysMsg(msgId);
    }

    public void sendMsgToUser(long userId, String content) {
        SysMsg sysMsg = new SysMsg();
        sysMsg.setToUserId(userId);
        sysMsg.setContent(content);
        messageDao.insertSysMsg(sysMsg);
    }

    public long sendMsgToAll(String content) {
        List<User> userList = userDao.getAllUser();
        int cnt = 0;
        for (User user : userList) {
            SysMsg sysMsg = new SysMsg();
            sysMsg.setToUserId(user.getUserId());
            sysMsg.setContent(content);
            messageDao.insertSysMsg(sysMsg);
            ++cnt;
        }
        return cnt;
    }
}
