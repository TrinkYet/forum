package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.TopicDao;
import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.Topic;

@Service
public class TopicService {
    
    @Autowired
    private TopicDao topicDao;
    
    public Topic getTopic(long topicId) {
        return topicDao.getTopicById(topicId);
    }
    
    public List<Topic> getGroupTopicList(long userId) {
        return topicDao.getGroupTopicListByUserId(userId);
    }
    
    public List<Topic> getTopicList(long groupId) {
        return topicDao.getTopicListByGroupId(groupId);
    }

    public void insertTopic(Topic topic) {
        topicDao.insertTopic(topic);
    }
    
    public List<Comment> getCommentList(long topicId) {
        return topicDao.getCommentListByTopicId(topicId);
    }

    public void insertComment(Comment comment) {
        topicDao.insertComment(comment);
    }

    public List<Topic> searchTopic(String q) {
        return topicDao.searchTopic(q);
    }
}
