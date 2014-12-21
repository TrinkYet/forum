<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<meta charset="UTF-8">
<%@ include file="/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/home.css">
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<style>
.main{
  width:960px;
  float:none;
  border:none;
  min-height:initial
}
#center .wrapper{
  padding: 0 0 0 85px
}
.wrapper{
  margin-bottom:70px;
}
.navbar .account{
  display:none
}
.main  .msg_ok{
  font-size:18px;
  border-bottom: 0;
  margin-left: 0;
  padding-left: 40px;
}
h3{
  padding-bottom: 8px;
  border-bottom: 1px solid #F1F1F1;
}
.msg_wrapper{
  padding-left:40px
}
.msg_list{
  margin-top:10px;
}

.msg_list li{
  line-height:22px;
  font-size:12px;
}
</style>
</head>
<body>
    <div class="container col-md-6 col-md-offset-3">
        <h2 class="msg_ok">请立即登录邮箱<a href="http://mail.google.com" target="_blank"> guhanya1@gmail.com </a>激活帐号</h2>
        <div class="msg_wrapper">
            <h3>如果你没有收到激活邮件：</h3>
            <ul class="msg_list">
                <li>1. 请检查邮箱的垃圾邮件(广告邮件)目录</li>
                <li>2. 如果还是没有找到，请<a href="#" class="resend_email"> 重新发送激活邮件</a>，稍后到邮箱查收</li>
                <li>3. 通过邮件联系我们：service@xueqiu.com</li>
            </ul>
        </div>
    </div>
</body>
</html>