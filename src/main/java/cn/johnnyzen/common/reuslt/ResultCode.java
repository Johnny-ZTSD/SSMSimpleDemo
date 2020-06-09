package cn.johnnyzen.common.reuslt;

import java.io.Serializable;

/**
 * @IDE: Created by IntelliJ IDEA.
 * @Author: 千千寰宇
 * @createDate: 2018/9/30  18:52:48
 * @modifyDate:
 *  2020-06-06 16:37 - zengtai
 *      更换代码值及其意义
 *      实现Serializable接口
 * @Description: ...
 */

public enum ResultCode implements Serializable {
    /**
     * @description 操作符合操作预期
     * 2000-2999
     */
    SUCCESS(2000, "操作成功"),//[概括性地描述，概括范围 2001-2999]

    /**
     * @description 操作失败，且操作不符合预期而导致的操作失败 (服务器正常运行)
     * 4000-4999
     */
    FAIL(4000, "操作失败或执行异常"),//[概括性地描述，概括范围 4001-4999]
    API_NOT_FOUND_ERROR(4100, "接口不存在错误"),
    API_NO_DEVELOP_OR_DEVELOPING_ERROR(4101, "接口请求成功，但尚未或尚在开发中"),
    PARAMETERS_UNCOMPLETE_ERROR(4200, "参数不完整(少参)"),
    PARAMETERS_TYPE_OR_FORMAT_ERROR(4300, "多参数中存在参数类型或参数格式错误"),
    //特设 认证类型枚举[4400-4499]
    AUTHORIZE_ERROR(4400,"认证错误"),
    UNAUTHORIZED_ERROR(4401, "未认证(签名错误)"),
    AUTHORIZE_SUCCESS(4402,"认证通过"),
    AUTHORIZE_FAIL(4403,"认证失败"),
    //特设 用户枚举[4700-4799]
    USER_LOGIN_ERROR(4700,"用户登录类型错误"), //[概括性地描述，概括范围 4500-4599]
    NOT_LOGIN_NO_ACCESS(4701, "未登录不能访问"),
    USERNAME_ERROR_OR_PASSWORD_ERROR(4702, "用户名或密码错误"),
    VERIFICATION_CODE_ERROR(4703, "验证码错误"),//手机验证码、邮箱验证码、图形图像识别验证码等

    /**
     * @description 操作失败，且由服务器级故障(自身服务器或第三方服务器)引起
     * 5000-5999
     */
    SERVER_INTERNAL_ERROR(5000,"服务器内部错误"),//[概括性地描述，概括范围 5001-5999]
    SERVER_MAINTAINING_ERROR(5001, "服务器维护中"),
    //特设 网络类型故障枚举[5200-5299]
    SERVER_NET_ERROR(5200, "网络故障类错误"),//[概括性地描述，概括范围 5200-5299]
    //特设 数据库类型故障枚举[5300-5399]
    SERVER_DB_ERROR(5300, "数据库类错误"),//[概括性地描述，概括范围 5300-5399]
    //特设 IO类型故障枚举[5400-5499]
    SERVER_IO_ERROR(5400, "文件或数据读写类错误"),//[概括性地描述，概括范围 5400-5499]
    //特设 第三方服务器枚举[5500-5599]
    THIRD_SYSTEM_ERROR(5500, "第三方服务器故障引起的操作失败"),//[概括性地描述，概括范围 5500-5599]

    /**
     * @description 未知类型错误
     * 6000-6999
     */
    UNKNOWN_ERROR(6000, "未知型错误");//[概括性地描述，概括范围 6001-6999]

    /**
     * @property Status Code(状态码)
     * @initData default Value is -1
     */
    public int code=-1;

    /**
     * @description 对状态码的业务解释
     */
    public String significance;

    ResultCode(int code, String significance) {
        this.code = code;
        this.significance = significance;
    }

    public static String getSignificance(ResultCode code){
        return code.significance;
    }
}
