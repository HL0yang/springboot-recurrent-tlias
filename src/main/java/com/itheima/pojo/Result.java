package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //定义属性
    private Integer code;
    private String msg;
    private Object data;
    //定义静态方法
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

}
