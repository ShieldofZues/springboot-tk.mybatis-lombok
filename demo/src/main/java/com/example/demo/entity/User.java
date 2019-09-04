package com.example.demo.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.util.Severitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author shieldofzues
 * @Description User
 * @Date 15:50 2016/5/23
 **/
@ApiModel(value = "com.example.demo.entity.User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "id主键")
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "name")
    @ApiModelProperty(value = "name姓名", required = false, allowEmptyValue = true, dataType = "String")
    //分组校验，校验异常可由全局异常捕获
    @NotEmpty(message = "用户token不能为空", groups = {Severitys.Insert.class})
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    @ApiModelProperty(value = "age年龄")
    private Byte age;

    /**
     * 性别
     */
    @Column(name = "sex")
    @ApiModelProperty(value = "sex性别")
    private Byte sex;

    /**
     * 个人信息
     */
    @Column(name = "info")
    @ApiModelProperty(value = "info个人信息")
    private String info;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    @ApiModelProperty(value = "gmtCreate创建时间")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    @ApiModelProperty(value = "gmtModified修改时间")
    private Date gmtModified;
}