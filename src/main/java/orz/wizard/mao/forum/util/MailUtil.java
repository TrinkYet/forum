package orz.wizard.mao.forum.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mailUtil")
public class MailUtil {
    
    @Autowired
    private JavaMailSender sender;
    
    public void send() {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo("hygu11@fudan.edu.cn");
            helper.setFrom("fudangroup@163.com");
            helper.setSubject("账户激活邮件");
            helper.setText("<h1>Hello</h1>");
            sender.send(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
