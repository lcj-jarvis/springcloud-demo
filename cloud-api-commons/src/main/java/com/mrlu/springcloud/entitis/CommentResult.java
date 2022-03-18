package com.mrlu.springcloud.entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author 简单de快乐
 * @date 2021-10-08 22:16
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class CommentResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
