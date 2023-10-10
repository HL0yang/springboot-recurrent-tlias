package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat LocalDate begin,
                       @DateTimeFormat LocalDate end){
        //输出日志信息
        log.info("准备查询员工列表信息");

        PageBean pageBean =  empService.list(page,pageSize,name,gender,begin,end);

        return Result.success(pageBean);
    }

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("准备删除员工,员工编号为：{}",ids);

        empService.delete(ids);

        return Result.success();

    }

    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        log.info("准备添加员工，员工信息：{}",emp);

        empService.add(emp);

        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result selectById(Integer id){

        log.info("准备查询id为{}号的员工",id);

        Emp emp = empService.selectById(id);

        return Result.success(emp);
    }


    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp)
    {
        log.info("准备修改员工：{}",emp);

        empService.update(emp);

        return Result.success();
    }

}
