package orz.wizard.mao.forum.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;
import orz.wizard.mao.forum.service.TopicService;
import orz.wizard.mao.forum.service.UserService;
import orz.wizard.mao.forum.util.GenerateLinkUtil;
import orz.wizard.mao.forum.util.ImageUtil;
import orz.wizard.mao.forum.util.MailUtil;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private MailUtil mailUtil;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showLoginPage() {
    	return "user/login";
    }
    
    @RequestMapping(value = {"/put_objects_in_session"}, method = RequestMethod.GET)
    public String putObjectsInSession(HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        UserInfo userInfo = userService.getUserInfo(user.getUserId());
        session.setAttribute("user", user);
        session.setAttribute("userInfo", userInfo);
        return "redirect:/group";
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showRegisterPage() {
        return "user/register";
    }
    
    @RequestMapping(value = {"/hasregistered"}, method = RequestMethod.POST)
    public @ResponseBody boolean hasRegistered(HttpServletRequest request){
    	String email = request.getParameter("email");
    	if (userService.getUser(email).getUserId() != 0){
    		return false;
    	}
    	return true;
    }
    
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String processRegister(@Valid User user, HttpServletRequest request, HttpSession session, BindingResult result) {
    	if (result.hasErrors()) {
    		return "user/register";
    	}
    	if (userService.getUser(user.getEmail()).getUserId() != 0) {
    	    // 邮箱已被注册
    		return "redirect:/user/register";
    	}
    	//System.out.println("now in processRegister");
    	userService.insertUser(user);
    	session.setAttribute("user", user);
    	String code = GenerateLinkUtil.generateCode(user);
    	String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    	userService.insertCode(user.getUserId(), code);
    	String activeUrl = baseUrl + "/user/activate?code=" + code + "&userId=" + user.getUserId();
    	String html = ""
    	        + "<div align='center'>"
    	        + "<table cellpadding='0' cellspacing='1' style='border:3px solid #d9e9f1;background:#7fbddd;text-align:left'>"
    	        + "<tbody><tr><td style='padding:0'>"
    	        + "<table cellpadding='30' cellspacing='0' style='border:1px solid #ffffff;background:#f7f7f7;width:500px'>"
    	        + "<tbody><tr><td style='line-height:2;font-size:12px'>"
    	        + "<div style='font-size:14px;margin-bottom:10px;font-weight:700'>Hi, " + user.getNickname() + "</div>"
    	        + "<p style='color:#ff6600;margin:0'>请点击下面的链接完成激活：</p>"
    	        + "<div style='padding:8px 10px;margin:10px 0 5px;background:#ffffff;border:1px solid #cbcbcb;word-break:break-all;word-wrap:break-word;line-height:1.5;font-size:14px'>"
    	        + "<a href='" + activeUrl + "'>" + activeUrl + "</a>"
    	        + "</div>"
    	        + "<div style='color:#999999;margin-bottom:5px'>如果不能点击链接，请复制地址并粘贴到浏览器的地址输入框</div>"
    	        + "激活后尽快删除此邮件，以免帐号信息泄漏"
    	        + "<div style='border-top:1px solid #e2e2e2;background:#ffffff;overflow:hidden;min-height:1px;margin:10px 0'></div>"
    	        + "欢迎您加入复旦小组"
    	        + "</td></tr></tbody></table></td></tr></tbody></table></div>";
    	mailUtil.send(user.getEmail(), "欢迎加入复旦小组，请尽快完成注册", html);
    	return "user/regsuc";
    }
    
    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    public String uploadAvatarPage() {
        return "user/avatar";
    }
    
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public @ResponseBody String updateAvatar(HttpSession session, HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) throws Exception {
        if (!avatar.isEmpty()) {
        	Integer x = Integer.valueOf(request.getParameter("x"));
        	Integer y = Integer.valueOf(request.getParameter("y"));
        	Integer w = Integer.valueOf(request.getParameter("w"));
        	Integer h = Integer.valueOf(request.getParameter("h"));
            User user = (User) session.getAttribute("user");
            BufferedImage image = ImageIO.read(avatar.getInputStream());
            BufferedImage image2 = ImageUtil.selectImageArea(image, x, y, w, h);
            String filePath = request.getSession().getServletContext().getRealPath("/") + "/avatar/user/" + user.getUserId() + ".jpg";
            ImageIO.write(image2, "jpg", new File(filePath));
            userService.updateAvatar(user.getUserId());
            user.setAvatar("avatar/user/" + user.getUserId() + ".jpg");
            return "success";
        } else {
            return "fail";
        }
    }
    
    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    public String showUserInfo(HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        model.put("userInfo", userService.getUserInfo(user.getUserId()));
        return "user/userinfo";
    }
    
    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.POST)
    public String updateUserInfo(@Valid UserInfo userInfo, HttpSession session, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        User user = (User) session.getAttribute("user");
        userInfo.setUserId(user.getUserId());
        userService.updateUserInfo(userInfo);
        return "redirect:/group";
    }
    
    @RequestMapping(value = {"/{userId}"}, method = RequestMethod.GET)
    public String showUserAll(@PathVariable long userId, Map<String, Object> model) {
    	model.put("pageuser", userService.getUser(userId));
        model.put("groupList", userService.getGroupList(userId));
        model.put("topicList", topicService.getUserTopicList(userId));
        model.put("createdList", userService.getCreatedList(userId));
        model.put("userInfo", userService.getUserInfo(userId));
        model.put("followerList", userService.getFollowerList(userId));
        model.put("followeeList", userService.getFolloweeList(userId));
        return "user/userall";
    }
    
    @RequestMapping(value = {"/{userId}/is_followed"})
    public @ResponseBody boolean isFollowed(@PathVariable long userId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.isFollowed(user.getUserId(), userId);
    }
    
    @RequestMapping(value = {"/{userId}/follow"})
    public @ResponseBody String doFollow(@PathVariable long userId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        userService.doFollow(user.getUserId(), userId);
        return "success";
    }
    
    @RequestMapping(value = {"/{userId}/cancel_follow"})
    public @ResponseBody String cancelFollow(@PathVariable long userId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        userService.cancelFollow(user.getUserId(), userId);
        return "success";
    }

    @RequestMapping(value = {"/activate"})
    public String activate(@RequestParam String code, @RequestParam long userId) {
        if (userService.activate(userId, code)) {
            return "user/userinfo";
        }
        return "failed";
    }
}
