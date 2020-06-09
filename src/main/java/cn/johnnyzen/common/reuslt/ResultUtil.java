package cn.johnnyzen.common.reuslt;

/**
 * @IDE: Created by IntelliJ IDEA.
 * @Author: Zengtai(千千寰宇)
 * @Date: 2018/9/30  21:03:59
 * @modifiedDate 2020-06-09 09:41
 * @modifiedAuthor Zengtai
 * @modifiedInfo 对Result的新增字段operationExplain进行方法的兼容适配
 */

public class ResultUtil {

    public static Result success(Object data){
        return template(ResultCode.SUCCESS, "operate success!", data);
    }

    public static Result success(String message){
        return template(ResultCode.SUCCESS, message, null);
    }

    public static Result success(String message, Object data){
        return template(ResultCode.SUCCESS, message, data);
    }

    public static Result success() {
        return success("operate success!");
    }

    public static Result error(ResultCode code, String msg) {
        return template(code, msg, null);
    }

    public static Result error(ResultCode code, String msg, String operationExplain) {
        return template(code, msg, null, operationExplain);
    }

    public static Result template(ResultCode code, String message, Object data,String operationExplain){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setOperationExplain(operationExplain);
        return  result;
    }

    public static Result template(ResultCode code, String message, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return  result;
    }
}
