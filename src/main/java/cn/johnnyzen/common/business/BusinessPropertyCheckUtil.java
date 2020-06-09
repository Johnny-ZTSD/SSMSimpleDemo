package cn.johnnyzen.common.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @IDE: Created by IntelliJ IDEA.
 * @Author: 千千寰宇
 * @createDate: 2018/11/4  22:08:16
 * @modifyDate: 2020-06-06 16:37 - zengtai(更换日志器及打日志的方法)
 * @Description: 业务属性检查工具类：判断字符串[包含对常见业务字段的判别]、数组、集合的操作
 */

public class BusinessPropertyCheckUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessPropertyCheckUtil.class);

    /**
     * 判断用户名是否合法
     *  1.长度：[3,18]
     *  2.字符：仅由数字、英文26字母、中文组成
     */
    public static int isLegalUsername(String username){
        if(username == null){
            LOGGER.info(" username<" + username + "> is null,so it is illegal format!");
            return 1;
        }
        if(username.length() < 3 || username.length() > 18){
            LOGGER.info(" username<" + username + ">'s length is not in [3,18],so it is illegal format!");
            return 2;
        }
        if(!isOnlyContainsChineseAndNumberAndEnglishAlpha(username)){//除了中文、英文字母、数字外还包含其他字符
            LOGGER.info(" username<" + username + ">'s is only that it contains chinese or number or english alpha,so it is illegal format!");
            return 3;
        }
        return 4;//legal
    }

    /**
     * 判断性别(字段)是否合法
     *  Only： F(女) M(男) U(未知)
     *  @param sex
     */
    public static boolean isLegalSex(Character sex){
        if(sex == null){ // 判空
            return false;
        }
        if(sex.equals('F') || sex.equals('M') || sex.equals('U')){//判别类型
            return true;
        }
        return false;
    }

    /**
     * 判别密码正确性
     *  0.长度：[6,18]
     *  1.字符：仅数字和英文字母组成
     *  @param password
     */
    public static int isLegalPassword(String password){
        if(password == null){
            LOGGER.info(" password<" + password + "> is null,so it is illegal format!");
            return 1;
        }
        if(password.length() < 6 || password.length() > 18){
            LOGGER.info(" password<" + password + ">'s length is not in [6,18],so it is illegal format!");
            return 2;
        }
        if(!isOnlyContainsNumberAndEnglishAlpha(password)){//除了英文字母和数字外，还含其他字符
            LOGGER.info(" password<" + password + "> is only that it contains english alpha and number,so it is illegal format!");
            return 3;
        }
        return 4;
    }

    /**
     * 判断email是否合法
     *  1.必须符合邮箱格式
     *  2.长度：[7,50]
     *  3.参考正则：^\w+@(\w+\.)+\w+$
     *  @param email
     */
    public static int isLegalEmail(String email){
        if(email == null){
            return 0;
        }
        email = email.trim();
        if(email.length() < 7 || email.length() > 50){//长度违法
            LOGGER.info("length of email<" + email + "> is illegal,because its length is not in [7,50]!");
            return 1;
        }
        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){//非邮箱格式
            LOGGER.info("format of email<" + email + "> is illegal!");
//            System.out.println(logPrefix + "format of email<" + email + "> is illegal!");
            return 2;
        }
        //判断是否有空格字符串
        if(!BusinessPropertyCheckUtil.isNotContainsBlankChar(email)){//if contains blank char
            LOGGER.info("This email<" + email + "> contains blank char,and it is illegal!");
            return 3;
        }
        return 4;//legal
    }


    /**
     * 是否是中国的合法手机号
     *  @author yuongxi
     *  @reference https://blog.csdn.net/m18860232520/article/details/79396889
     *      中国电信号段 133、149、153、173、177、180、181、189、199
     *      中国联通号段 130、131、132、145、155、156、166、175、176、185、186
     *      中国移动号段 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     *      其他号段
     *      14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     *      虚拟运营商
     *          电信：1700、1701、1702
     *          移动：1703、1705、1706
     *          联通：1704、1707、1708、1709、171
     *          卫星通信：1349
     * @param phone
     */
    public static boolean isPhoneNumberOfChina(String phone){
        if(phone == null){
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            LOGGER.info("In China, phone number's length is must 11.");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {//格式错误
                LOGGER.info("In China, this phone number's format is error!");
            }
            return isMatch;
        }
    }

    /**
     * 是否包含空格字符
     *  true：不包含任何空格字符
     *  false：包含空字符
     * @param str
     */
    public static boolean isNotContainsBlankChar(String str){
        String logPrefix = "[CollectionUtil.isNotContainsBlankChar] ";
        if(str == null){
            return false;
        }
        for (int t = 0; t < str.length(); t++) {
            String b = str.substring(t, t + 1);
            if (b.equals(" ")) {
                LOGGER.info(logPrefix + "str<" + str + "> is not legal format, because it contains empty char!");
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串中是否仅包含字母数字和汉字
     *      汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     *      数字：[0x30,0x39]（或十进制[48, 57]）
     *      小写字母：[0x61,0x7a]（或十进制[97, 122]）
     *      大写字母：[0x41,0x5a]（或十进制[65, 90]）
     *  @param str
     */
    public static boolean isOnlyContainsChineseAndNumberAndEnglishAlpha(String str){
        if(str == null){
            return false;
        }
        return str.matches("^[a-z0-9A-Z\\u4e00-\\u9fa5]+$");
    }

    /**
     * 判断字符串是否仅由26个英文字母、数字组成
     * @param str
     */
    public static boolean isOnlyContainsNumberAndEnglishAlpha(String str){
        if(str == null){
            return false;
        }
        return str.matches("^[a-z0-9A-Z]+$");
    }
}
