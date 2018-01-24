package com.venti.util;


import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.venti.constant.SMSConstant;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * 短信发送工具
 */
public class SMSUtil {

    /**
     * 短信单发
     * @param templateId 短信模板号
     * @param params 模板内容参数{1},{2}...
     * @param mobile 手机号
     * @return 状态码
     */
    public static SmsSingleSenderResult send2One(int templateId, ArrayList<String> params, String mobile) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(SMSConstant.APPID, SMSConstant.APPKEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", mobile,
                    templateId, params, SMSConstant.SMS_SIGN, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 短信群发
     * @param templateId 短信模板号
     * @param params 模板内容参数{1},{2}...
     * @param mobiles 手机号
     * @return 状态码
     */
    public static SmsMultiSenderResult send2Multi(int templateId, ArrayList<String> params, ArrayList<String> mobiles) {
        try {
            SmsMultiSender msender = new SmsMultiSender(SMSConstant.APPID, SMSConstant.APPKEY);
            SmsMultiSenderResult result = msender.sendWithParam("86", mobiles,
                    templateId, params, SMSConstant.SMS_SIGN, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }
}
