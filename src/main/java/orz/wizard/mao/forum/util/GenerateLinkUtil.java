package orz.wizard.mao.forum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import orz.wizard.mao.forum.entity.User;

/**
 * 生成账户激活、重设密码的链接
 */
public class GenerateLinkUtil {
    
    /**
     * 生成验证帐户的MD5校验码，将用户名和密码组合后，通过md5加密后的16进制格式的字符串
     */
    public static String generateCode(User user) {
        return md5(user.getEmail() + ";" + user.getPassword());
    }
    
    public static String md5(String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }
}
