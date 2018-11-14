package com.qf.oa.controller;

import com.qf.oa.entity.Emp;
import com.qf.oa.entity.Mail;
import com.qf.oa.entity.Result;
import com.qf.oa.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/7  14:08
 * @Version 1.0
 */
@Controller
@RequestMapping("mailController")
public class MailController {
    @Autowired
    private IEmpService empService;
    @Autowired
    private JavaMailSender javaMailSender;
    @RequestMapping("mailPage")
    public String toPage(){
        return "mailPage";
    }
    @RequestMapping("getEmpByKeyword")
    @ResponseBody
    public Result getEmpByKeyword(String Keyword, HttpSession session){
        Emp emp = (Emp) session.getAttribute("user");
        List<Emp> empList = empService.getEmpByKeyword(Keyword, emp.getId());
        List<Result.Info> infos = new ArrayList<>();
        Result result = new Result();
        for (Emp emp1 : empList) {
            Result.Info info = new Result.Info();
            info.setValue(emp1.getName()+"["+emp1.getEmail()+"]");
            info.setData(emp1.getEmail());
            infos.add(info);
        }
        result.setSuggestions(infos);
        return result;
    }
    @RequestMapping("sendMail")
    public void sendMail(Mail mail, HttpServletResponse response) throws MessagingException, IOException {
        //1.创建邮件,通过注入JavaMailSender接口的实现类来创建邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //2.初始化邮件信息，包括标题，附件，收件人和邮件内容(附件通过mimeMessageHelper的第二个参数来进行设置)
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setTo(mail.getSendTo());
        mimeMessageHelper.setText(mail.getContent(),true);
        //发送附件
        mimeMessageHelper.addAttachment(mail.getFile().getOriginalFilename(), new InputStreamSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return mail.getFile().getInputStream();
            }
        });
        //3.设置发送方
        mimeMessageHelper.setFrom("lzq511511@sina.com");
        //4.发送邮件,通过实现类来发送
        javaMailSender.send(mimeMessage);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('发送邮箱至["+mail.getSendTo()+"]成功！');window.location='/mailController/mailPage';</script>");
    }
}
