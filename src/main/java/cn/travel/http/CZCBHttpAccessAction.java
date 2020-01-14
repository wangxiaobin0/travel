package cn.travel.http;

import javax.naming.Context;

/**
 * Http请求
 */
public class CZCBHttpAccessAction {
    /**
     * 默认接收超时间隔：65秒
     */
    private static final int DEFAULT_TIMEOUT = 65 * 1000;
    /**
     * 网银错误代码：打包数据异常
     */
    private static final String ERROR_CODE_PACK = "EBLN0010";
    /**
     * 网银错误代码：解包错误
     */
    private static final String ERROR_CODE_UNPACK = "EBLN0014";
    /**
     * 网关交易代码
     */
    private String hostCode;
    /**
     * 交易版本
     */
    private String version;
    /**
     * 发送网关数据的打包格式名称
     */
    private String sendFormatName;
    /**
     * 接收到的数据的解包格式名称
     */
    private String receiveFormatName;
    /**
     * context中存放返回交易代码的字段
     */
    public static final String FIELD_HOST_RETURN_CODE = "hostReturnCode";
    /**
     * context中存放返回错误信息的字段
     */
    public static final String FIELD_HOST_ERROR_MESSAGE = "hostErrorMessage";
    /**
     * 是否抛出异常
     */
    private boolean throwException = true;
    /**
     * 上送流水号
     */
    private String sequenceNo="0000000";
    /**
     * 上送流水号长度
     */
    private static int DELIVER_SQE_LENGTH = 8;

    public String execute( Context context ) throws Exception {
        //获取连接地址
        String host = "127.0.0.1";
        //获取端口
        String port = "80";

        //发送数据
        String sendMessage = null;
        return "0";
    }
}
