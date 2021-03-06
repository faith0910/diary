package com.shine.response;

import com.shine.enums.Code;


/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 13:28:55
 */




    public class Result<T> {
        /**
         * 1.status状态值：代表本次请求response的状态结果。
         */
        private Integer status;
        /**
         * 2.response描述：对本次状态码的描述。
         */
        private String desc;
        /**
         * 3.data数据：本次返回的数据。
         */
        private T data;

        /**
         * 成功，创建ResResult：没data数据
         */
        public static Result suc() {
            Result result = new Result();
            result.setResultCode(Code.SUCCESS);
            return result;
        }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
         * 成功，创建ResResult：有data数据
         */
        public static Result suc(Object data) {
            Result result = new Result();
            result.setResultCode(Code.SUCCESS);
            result.setData(data);
            return result;
        }

        /**
         * 失败，指定status、desc
         */
        public static Result fail(Integer status, String desc) {
            Result result = new Result();
            result.setStatus(status);
            result.setDesc(desc);
            return result;
        }

        /**
         * 失败，指定ResultCode枚举
         */
        public static Result fail(Code resultCode) {
            Result result = new Result();
            result.setResultCode(resultCode);
            return result;
        }

        /**
         * 把Code枚举转换为ResResult
         * @param code
         */
        private void setResultCode(Code code) {
            this.status = code.code();
            this.desc = code.message();
        }
    }





