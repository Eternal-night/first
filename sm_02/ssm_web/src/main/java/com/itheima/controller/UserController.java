package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //允许上传文件的类型：image/jpeg
    private String[] allowType={"image/jpeg"};
    //登录操作
    @RequestMapping("/login")
    public String login(User user, HttpSession session, String ck, HttpServletResponse response) throws Exception {
        boolean flag = userService.findLogin(user);
        if(flag){//登录成功
            session.setAttribute("user",user);

            //创建Cookie
            Cookie cookieName=new Cookie("username",user.getUsername());
            Cookie cookiePwd=new Cookie("pwd",user.getPwd());
            //ck是记住复选框的值，默认值1，如果是1则表现记住，否则不记住
            if("1".equals(ck)){
                //设置Cookie存活时间
                cookieName.setMaxAge(7*24*60*60);
                cookiePwd.setMaxAge(7*24*60*60);
            }else{
                //设置Cookie存活时间
                cookieName.setMaxAge(0);
                cookiePwd.setMaxAge(0);
            }
            //设置Cookie存储路径
            cookieName.setPath("/");
            cookiePwd.setPath("/");

            response.addCookie(cookieName);
            response.addCookie(cookiePwd);







            return "index1";
        }else{//登录失败
            return "loginError";
        }

    }

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",required = false,defaultValue = "3") Integer pageSize,
                          User user,Model model)throws Exception{
        //判断pageStart如果为null,必须给pageStart一个初始值0
        List<User> list=userService.findAll(pageNum,pageSize,user);//0 3

        int total=userService.findTotal(user);

        int pages=total%pageSize==0?(total/pageSize):(total/pageSize)+1;

        model.addAttribute("userList",list);
        model.addAttribute("pageNum",pageNum);//当前页的页码带回前端
        model.addAttribute("total",total);//总记录数
        model.addAttribute("pages",pages);//总页数
        model.addAttribute("user",user);
        return "list";
    }

    @RequestMapping("/saveUser")
    public String saveUser(MultipartFile picName, User user, HttpServletRequest request) throws Exception {
        System.out.println(user.getUsername());
        System.out.println(user.getSex());
        System.out.println("获取文件的类型:"+picName.getContentType());
        System.out.println("获取文件名称:"+picName.getOriginalFilename());
        System.out.println("获取上传文件的大小:"+picName.getSize());

        String fileName=picName.getOriginalFilename();

        String path=request.getSession().getServletContext().getRealPath("/images/");
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //设置允许上传的文件类型
        String contentType = picName.getContentType();
        if(!Arrays.asList(allowType).contains(contentType)){
            throw new RuntimeException("你上传的文件类型不符合要求！");
        }

       String uuid= UUID.randomUUID().toString().replace("-","");
        fileName=uuid+"_"+fileName;
        //实现文件的上传
        picName.transferTo(new File(file,fileName));

        user.setPic(fileName);
        userService.saveUser(user);
        return "redirect:/user/findAll";
    }
    @RequestMapping("/findById")
    public String findById(Model model){
        User u=userService.findById(15);
        model.addAttribute("user",u);
        return "detail";
    }
}
