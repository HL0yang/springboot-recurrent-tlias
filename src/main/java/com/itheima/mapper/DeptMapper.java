package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from tlias.dept where id = #{id}")
    void delete(Integer id);

    @Insert("insert into tlias.dept (id, name, create_time, update_time) values " +
            "(#{id},#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept select(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
