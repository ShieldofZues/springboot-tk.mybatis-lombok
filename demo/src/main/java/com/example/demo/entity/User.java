package com.example.demo.entity;

import com.example.demo.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value="com.example.demo.entity.User")
@Data
@Builder
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="id主键")
    private Integer id;

    /**
     * 个人信息
     */
    @Column(name = "info")
    @ApiModelProperty(value="info个人信息")
    private String info;

    /**
     * 性别
     */
    @Column(name = "sex")
    @ApiModelProperty(value="sex性别")
    private Byte sex;

    /**
     * 年龄
     */
    @Column(name = "age")
    @ApiModelProperty(value="age年龄")
    private Byte age;

    /**
     * 姓名
     */
    @Column(name = "name")
    @ApiModelProperty(value="name姓名")
    private String name;
}