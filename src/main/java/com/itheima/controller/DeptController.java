package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result deptList(){
        //输出日志信息，以便于排除错误
        log.info("查询部门列表");

        List<Dept> list =  deptService.list();

        //响应数据
        return Result.success(list);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("准备删除部门，部门id为{}",id);

        deptService.delete(id);


        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("添加部门，部门信息为{}",dept);

        deptService.add(dept);

        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result select(@PathVariable Integer id){
        log.info("根据id: {}查询部门信息",id);

        Dept dept = deptService.select(id);

        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门信息为：{}",dept);

        deptService.update(dept);

        return Result.success();
    }

}
