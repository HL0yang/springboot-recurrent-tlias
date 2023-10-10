package com.itheima.service;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService{
    public List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept select(Integer id);

    void update(Dept dept);
}
