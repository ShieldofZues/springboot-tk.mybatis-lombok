package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Table(name = "admin_user")
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser implements Serializable {
	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "admin_user_id")
	@GeneratedValue(generator = "JDBC")
	private Integer adminUserId;

	/**
	 * 用户名
	 */
	@Column(name = "admin_user_name")
	private String adminUserName;

	/**
	 * 密码md5加密
	 */
	@Column(name = "admin_password")
	private String adminPassword;

	/**
	 * 电话号码
	 */
	@Column(name = "telephone_number")
	private String telephoneNumber;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Column(name = "gmt_modified")
	private Date gmtModified;

	/**
	 * 删除状态 1 有效  2 失效 用于伪删除
	 */
	@Column(name = "delete_status")
	private Byte deleteStatus;

	/**
	 * 待定 1 销售人员
	 */
	@Column(name = "role_id")
	private Byte roleId;

	private static final long serialVersionUID = 1L;

}