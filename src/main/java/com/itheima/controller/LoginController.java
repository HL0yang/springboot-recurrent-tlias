package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录操作");

        Emp emp1 = empService.login(emp);
        //如果emp1不为空，那么就说明数据库中有此员工信息，service层返回了该员工的信息，这时就可以生成JWT令牌，下发JWT令牌
        if(emp1!=null){
            Map<String,Object> Claims  = new HashMap<>();
            Claims.put("id",emp1.getId());
            Claims.put("name",emp1.getName());
            Claims.put("username",emp1.getUsername());

            //使用创建的JWT工具类生成JWT令牌
            String jwt = JwtUtils.generateJwt(Claims);
            //向前端返回这个jwt令牌，前端接收到之后，每次都会在请求头里的token中携带这个JWT令牌
            return Result.success(jwt);
        }

        return Result.error("NOT_LOGIN");
    }

}
