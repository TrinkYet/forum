package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.TopicDao;
import orz.wizard.mao.forum.dao.UserDao;
import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;

@Service
public class TopicService {
    
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private UserDao userDao;
    
    public Topic getTopic(long topicId) {
        return topicDao.getTopicById(topicId);
    }
    
    public List<Topic> getGroupTopicList(long userId) {
        return topicDao.getGroupTopicListByUserId(userId);
    }
    
    public List<Topic> getUserTopicList(long userId) {
    	return topicDao.getUserTopicListByUserId(userId);
    }
    
    public List<Topic> getTopicList(long groupId) {
        return topicDao.getTopicListByGroupId(groupId);
    }

    public void insertTopic(Topic topic) {
        topicDao.insertTopic(topic);
        List<User> followerList = userDao.getFollowerList(topic.getUserId());
        for (User user : followerList) {
            topicDao.insertMsgTopic(topic.getTopicId(), user.getUserId());
        }
    }
    
    public List<Comment> getCommentList(long topicId) {
        return topicDao.getCommentListByTopicId(topicId);
    }

    public void insertComment(Comment comment) {
        topicDao.insertComment(comment);
        if (comment.getToCommentId() > 0) {
            Comment toCmt = topicDao.getCommentById(comment.getToCommentId());
            topicDao.insertMsgCmt(comment.getCommentId(), toCmt.getUserId());
        } else {
            Topic topic = topicDao.getTopicById(comment.getTopicId());
            topicDao.insertMsgCmt(comment.getCommentId(), topic.getUserId());
        }
    }

    public List<Topic> searchTopic(String q) {
        return topicDao.searchTopic(q);
    }

    public void saveTopic(Topic topic) {
        topicDao.saveTopic(topic);
    }

    public void delete(long topicId) {
        topicDao.delete(topicId);
    }
    
    public void deleteCmt(long commentId) {
        topicDao.deleteCmt(commentId);
    }

    public List<Topic> getAllTopic() {
        return topicDao.getAllTopic();
    }

    public long count() {
        return topicDao.count();
    }

    public long countComment() {
        return topicDao.countComment();
    }
}
