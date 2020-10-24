package com.duya.shopping.utils;

/*
错误码可能是String、Integer、Long 等类型，也可能是enum类型。因此这里使用泛型来代替，错误码类型，可以提高灵活性
。
 */

public interface CodeMessage<C>{
    C getCode();
    String getMessage();
}
