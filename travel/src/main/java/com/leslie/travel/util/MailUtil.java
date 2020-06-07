package com.leslie.travel.util;
/**
 * 邮件工具类
 */
public class MailUtil extends Thread{
    //邮箱验证码
    private String email;
    private String text;
    private String title = "旅游网验证码";
    @Override
    public void run() {
        System.out.println("正在发送验证邮件。。。");
        System.out.println(email+text+title);
        if(MailUtils.sendMail(email, text, title)) {
            System.out.println("邮件发送成功");
        }
    }
    public MailUtil(String emailaddress,String code){
        this.email = emailaddress;
        this.text = "您的旅游网找回密码的验证码为：\n"+code;
    }
}