/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */


package com.yuerui.biz.service;

import com.github.pagehelper.PageInfo;
import com.yuerui.common.jqgrid.JqGridParam;
import com.yuerui.common.service.IBaseService;
import com.yuerui.model.SysUser;


 

//TODO 注册到dobbo服务中心接口定义
//IBaseService接口定义以下接口：
//1.getById(Long id)					根据ID获取实体
//2.getByEntity(T entity)				根据实体条件获取一条实体数据
//3.getListByExample(Example example)	根据example条件查询列表
//4.getCountByExample(Example example)	根据example条件统计数量
//5.add(T entity)						新增一条数据，并返回实体（带ID）
//6.update(T entity)					更新记录
//7.delete(T entity)					物理删除记录

public interface ISysUserService extends IBaseService<SysUser>{

	/**
	 * 根据用户名，获取用户实体
	 * 
	 * @param username 用户名
	 * @return SysUser
	 */
	SysUser getUserByuserame(String username);

	PageInfo<SysUser> getUserListByGridParam(JqGridParam gparam);



}
