package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {


//    @Select("select * from emp where limit #{first},#{pageSize}")
    List<Emp> list(Integer first, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    @Select("select count(*) from emp")
    Integer getTotal();

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectIn(Emp emp);
}
