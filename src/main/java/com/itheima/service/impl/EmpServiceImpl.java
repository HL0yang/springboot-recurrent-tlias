package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        Integer totalPage = empMapper.getTotal();

        Integer first = (page-1)*pageSize;
        List<Emp> emps = empMapper.list(first,pageSize,name,gender,begin,end);

        PageBean pageBean = new PageBean(totalPage,emps);

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    @Override
    public Emp selectById(Integer id) {
        Emp emp = empMapper.selectById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }

    @Override
    public Emp login(Emp emp) {
        Emp emp1 =  empMapper.selectIn(emp);

        return emp1;
    }
}
