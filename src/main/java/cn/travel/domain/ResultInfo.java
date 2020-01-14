package cn.travel.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用于封装后端返回前端数据对象
 */
public class ResultInfo implements Serializable {
    private String errorMsg;//发生异常的错误消息
    private String errorCode;//发生异常的错误消息
    private Object data;//后端返回结果数据对象


    //无参构造方法
    public ResultInfo() {
    }

    public ResultInfo(String errorCode, String errorMsg, Object data) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
