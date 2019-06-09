package com.bw.movie.view;


import com.bw.movie.bean.Result;

/**
 * @Author：刘京源
 * @E-mail： 1179348728@qq.com
 * @Date： 2019/6/8
 * @Description：描述信息
 */
public interface Data_CallBck<T> {

    void success(T t);
    void  fail(Result result);

}
