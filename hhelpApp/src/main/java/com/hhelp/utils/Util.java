package com.hhelp.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.GregorianCalendar;
import java.util.Properties;


public class Util {

    public static final String HOST = "smtp.qq.com";
    public static final String SENDER_EMAIL = "1376417539@qq.com";
    public static final String PASSWORD = "jxstphkjsexyijgb";

    /**
     * 发送邮件工具类
     * @param content 邮件正文
     * @param recv_email 收件人邮箱
     * @return
     */
    public static boolean sendMail(String content, String recv_email) throws Exception{
        Properties prop = new Properties();
        prop.put("mail.smtp.host", HOST);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.debug", "false");
        Session session = Session.getInstance(prop);

        //设置邮件内容
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject("HHELP验证码");
        //正文内容
        mimeMessage.setContent(content, "text/html;charset=UTF-8");
        InternetAddress send_addr = new InternetAddress(SENDER_EMAIL);
        mimeMessage.setFrom(send_addr);
        mimeMessage.setRecipients(Message.RecipientType.TO, recv_email);
        Transport transport = session.getTransport();
        mimeMessage.saveChanges();

        System.out.println("正在发送邮件");
        //发送邮件
        transport.connect(HOST, SENDER_EMAIL, PASSWORD);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("发送成功");
        return true;

    }


    /**
     * 生成四位验证码
     * @return
     */
    public static String getVerCode(){
        long seed = new GregorianCalendar().getTimeInMillis();
        String vercode = Long.toString(seed);
        vercode = vercode.substring(vercode.length()-5, vercode.length() - 1);
        return vercode;
    }

    public static JSONArray getJsonArray(int status,String errmsg, JSONArray jsonArray){
        JSONArray res = new JSONArray();
        JSONObject errTip = new JSONObject();
        errTip.put("errno", status);
        errTip.put("errmsg", errmsg);
        res.add(errTip);
        if (jsonArray != null && !jsonArray.isEmpty())
            res.add(jsonArray);
        return res;
    }

    public static void main(String[] args){
//        sendMail("2264", "2324604240@qq.com");
        System.out.println(getVerCode());
    }
}
