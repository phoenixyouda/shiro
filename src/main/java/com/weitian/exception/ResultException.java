package com.weitian.exception;

import com.weitian.ResultRnum.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/11/21.
 */
@Data
public class ResultException extends  RuntimeException {
    private String msg;
    private Integer code;
    public ResultException(ResultEnum resultEnum){
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
    }
    public ResultException( String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

}
