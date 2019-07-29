package com.example.biglong.controller;

import com.example.biglong.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description 测试
 * @Author lilong
 * @Date 2019-04-25 20:10
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-thymeleaf") String name) {
        request.setAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model){
        model.addAttribute("uid","123456789");
        model.addAttribute("name","Jerry");
        model.addAttribute("name_style","<span style='color:red'>Jerry</span>");
        return "show";
    }

    @RequestMapping(value = "/message/member_show", method = RequestMethod.GET)
    public String memberShow(Model model) {
        User vo = new User();
        vo.setUid(12345678L);
        vo.setName("尼古拉丁.赵四");
        vo.setAge(59);
        vo.setSalary(1000.00);
        vo.setBirthday(new Date());
        model.addAttribute("member", vo);
        return "message/member_show";
    }

    @RequestMapping(value = "/user/map", method = RequestMethod.GET)
    public String map(Model model) {
        Map<String,User> allMembers = new HashMap<>();
        for (int x = 0; x < 10; x++) {
            User vo = new User();
            vo.setUid(101L + x);
            vo.setName("赵四 - " + x);
            vo.setAge(9);
            vo.setSalary(99999.99);
            vo.setBirthday(new Date());
            allMembers.put("mldn-" + x, vo);
        }
        model.addAttribute("allUsers", allMembers);
        return "user_map";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> allMembers = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            User vo = new User();
            vo.setUid(101L + x);
            vo.setName("赵四 - " + x);
            vo.setAge(9);
            vo.setSalary(99999.99);
            vo.setBirthday(new Date());
            allMembers.add(vo) ;
        }
        model.addAttribute("allUsers", allMembers);
        return "user_list";
    }

//    @RequestMapping("/test")
//    public String testFreemarker(Model model){
//        model.addAttribute("msg", "Hi, biglong, this is freemarker");
//        return "freemarker";
//    }
}