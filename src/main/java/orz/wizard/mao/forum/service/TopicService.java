package orz.wizard.mao.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orz.wizard.mao.forum.dao.TopicDao;
import orz.wizard.mao.forum.entity.Topic;

@Service
public class TopicService {
    @Autowired
    private TopicDao topicDao;
    
    public Topic getTopic(long id) {
        return topicDao.getTopicById(id);
    }
    
    public List<Topic> getTopicList(long id) {
        return topicDao.getTopicListById(id);
    }
}
