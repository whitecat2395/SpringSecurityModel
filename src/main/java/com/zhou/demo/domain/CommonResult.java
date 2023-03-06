package com.zhou.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T      data;
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

    public CommonResult(Integer code, String message, T data) {
        this.data=data;
        this.code=code;
        this.message=message;
    }

}
