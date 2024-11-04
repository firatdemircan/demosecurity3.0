package org.demircan.demosecurity.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result <T>{

    private T t;
    private String message;
    private Boolean result;


    public Result(T t, String message, Boolean result) {
        this.t = t;
        this.message = message;
        this.result = result;
    }

    public Result(String message, Boolean result) {
        this.message = message;
        this.result = result;
    }
}
