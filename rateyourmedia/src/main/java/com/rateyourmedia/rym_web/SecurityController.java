package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_entity.AccountType;
import com.rateyourmedia.rym_entity.User;
import com.rateyourmedia.rym_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("singleton")
public class SecurityController {
    @Autowired
    private UserService userService;

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(){
        return "start";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showloginPage(Model model){
        User user= new User();
        model.addAttribute("User",user);
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegistration(@ModelAttribute("User") User user, Model model){
        user.setAccountType(AccountType.STANDARD);
        userService.registerUser(user);
        return login();
    }
}
