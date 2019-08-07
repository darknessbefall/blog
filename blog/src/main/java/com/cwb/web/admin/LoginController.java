package com.cwb.web.admin;

import com.cwb.pojo.User;
import com.cwb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 默认访问admin时,自动跳转到登录界面
     */
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 验证用户名密码, 正确的话存储到session中
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);     //数据传回前端页面时将密码置位null
            session.setAttribute("user",user );
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名密码错误!");
            return "redirect:/admin";   //重定向到登录页面
        }
    }

    /**
     * 登出用户, 登出之后清空session
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
