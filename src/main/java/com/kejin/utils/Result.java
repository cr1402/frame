package com.kejin.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)//可以链式操作，默认是false
public class Result<T>{
    private int code;

    private String message;

    private T result ;

}
