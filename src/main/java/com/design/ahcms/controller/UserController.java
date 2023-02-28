package com.design.ahcms.controller;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.User;
import com.design.ahcms.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public long save(@RequestBody User user){
        long id=userService.saveUser(user);
        log.info("id:"+id);
        return id;
    }
}
