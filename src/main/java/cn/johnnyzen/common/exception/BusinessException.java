package cn.johnnyzen.common.exception;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo
 * @author: 千千寰宇
 * @date: 2020/6/9  20:11:24
 * @description: ...
 */

public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}
