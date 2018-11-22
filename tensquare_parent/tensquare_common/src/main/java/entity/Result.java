package entity;

import java.io.Serializable;

/**
 * @propram:tensquare
 * @description:各个微服务查询到的结果,将result转换成json形式返回给前端
 */

public class Result implements Serializable {
  private  boolean flag;//返回成功的标记
  private Integer code;//返回的状态码
  private String message;//返回提示信息
  private Object data;//返回的数据


    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
