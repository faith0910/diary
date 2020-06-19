package com.nannan.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-05-26 10:58:25
 */

    @Data
    @Entity
    public class User {
        @Id
        private Integer id;

        private String userName;

        private String password;

        private String address;


    }
