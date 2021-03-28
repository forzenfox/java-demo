package com.dylan.web.demo.controller;


import com.dylan.web.demo.model.auto.User;
import com.dylan.web.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoulei
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUser")
    public User getUser(){
        return userService.getById(1);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.findAllUser();
    }
}
