/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */


package com.yuerui.biz.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuerui.biz.dao.SysUserMapper;
import com.yuerui.biz.service.ISysUserService;
import com.yuerui.common.jqgrid.JqGridParam;
import com.yuerui.common.jqgrid.ParamSeachFieldVO;
import com.yuerui.common.service.BaseServiceImpl;
import com.yuerui.model.SysUser;
import com.yuerui.util.StringUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;




//TODO 若是想注册到dobbo服务中心，请自己创建Interfere文件并在Service中实现
//BaseService接口实现以下接口：
//1.getById(Long id)					根据ID获取实体
//2.getByEntity(T entity)				根据实体条件获取一条实体数据
//3.getListByExample(Example example)	根据example条件查询列表
//4.getCountByExample(Example example)	根据example条件统计数量
//5.add(T entity)						新增一条数据，并返回实体（带ID）
//6.update(T entity)					更新记录
//7.delete(T entity)					物理删除记录
@Service
@Transactional(readOnly=true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService{

	public SysUser getUserByuserame(String username) {
		//读取有效的用户
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("username", username).andEqualTo("isValid", true);
		
		return this.getOneByExample(example);
	}

	public PageInfo<SysUser> getUserListByGridParam(JqGridParam gparam) {
		//搜索条件为空时
		if(gparam==null){
			return new PageInfo<SysUser>();	//Collections.emptyList();
		}
		
		//当cid=null时，返回空列表
		if(gparam.getCid()==null || gparam.getCid()==0){
			return  new PageInfo<SysUser>();
		}
		
		Example example = new Example(SysUser.class);
		//企业编号与状态条件(默认条件）
		Criteria c =  example.createCriteria();
		c.andEqualTo("cid", gparam.getCid()).andEqualTo("isValid", true);
		//遍历加入搜索条件
		if(gparam.getPostDatas()!=null){
			for(ParamSeachFieldVO sField : gparam.getPostDatas()){
				if(sField.getSearchOper().equals(JqGridParam.operType.eq.toString())){
					//等于条件
					c.andEqualTo(sField.getSearchField(), sField.getSearchValue());
				}else if(sField.getSearchOper().equals(JqGridParam.operType.like.toString())){
					//like条件
					c.andLike(sField.getSearchField(), "%" + sField.getSearchValue() + "%" );
				}
			}
		}
		
		// 排序
		if (StringUtils.isNotEmpty(gparam.getSidx()))
			example.setOrderByClause(gparam.getSidx() + " " + gparam.getSord());
		else		
			example.setOrderByClause("update_date desc");
		
		
		PageHelper.startPage((int) gparam.getPage(), (int) gparam.getRows());
		return  new PageInfo<SysUser>(this.getListByExample(example));
	}
}
