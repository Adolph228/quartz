package com.yuerui.common.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yuerui.common.database.CommonMapper;
import com.yuerui.util.StringUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 《基础Service支持类》
 * <li>基于通用Mapper 增、删、改、查、根据Example查询列表、根据Example统计单表数量
 */
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<D extends CommonMapper<T>, T> implements IBaseService<T> {
	private final String SPLIT_CHAR = ",";
	/**
	 * 持久层对象
	 */
	@Autowired(required=false)
	protected D dao;
	
	public T getById(Long id) {
		if(id==null || id==0){
			return null;
		}
		
		T t =  dao.selectByPrimaryKey(id);
		
		return t;
	}
	public void deleteById(Long id){
		if(id==null || id==0){
			return ;
		}
		dao.deleteByPrimaryKey(id);
	}
	protected void setUpdateInfo(T entity,Long loginStaffId) {
		Method setUpdateDate;
		Method setUpdateBy;
		try {
			setUpdateDate = this.getMethod(entity, "setUpdateDate", Date.class);
			if(setUpdateDate != null) {
				setUpdateDate.invoke(entity, new Date());
			}
			
			if (loginStaffId != null) {
				setUpdateBy = this.getMethod(entity, "setUpdateBy", Long.class);
				if(setUpdateBy != null) {
					setUpdateBy.invoke(entity, loginStaffId);
				}
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private void setIsValid(T entity, boolean bln){
		Method setIsValid;
		try {
			setIsValid = this.getMethod(entity, "setIsValid", Boolean.class);
			if(setIsValid != null) {
				setIsValid.invoke(entity, bln);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private Method getMethod(T entity,String methodStr,Class<?> paramType){
		Method method = null;
		for(Class<? extends Object> clazz = entity.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				if(paramType !=null){
					method = clazz.getDeclaredMethod(methodStr, paramType) ;
				}else{
					method = clazz.getDeclaredMethod(methodStr) ;
				}
				return method;
			} catch (Exception e) {
				//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}
		return method;
	}
	
	private void setCreateInfo(T entity,Long createBy){
		Method setCreateDate = null;
		Method setCreateBy =null;
		try {
			
			setCreateDate = this.getMethod(entity, "setCreateDate", Date.class);
			if(setCreateDate != null) {
				setCreateDate.invoke(entity, new Date());
			}
			
			//设置有效状态
			setIsValid(entity, true);
			
			if (createBy != null) {
				setCreateBy = this.getMethod(entity, "setCreateBy", Long.class);
				if(setCreateBy != null) {
					setCreateBy.invoke(entity, createBy);
				}
			}			
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增数据（返回entity.id）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public T add(T entity) {
		dao.insertSelective(entity);
		return entity;
	}
	
	/**
	 * 新增数据（返回entity.id）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public T add(T entity,Long loginStaffId) {
		this.setCreateInfo(entity, loginStaffId);
		this.setUpdateInfo(entity, loginStaffId);
		
		dao.insertSelective(entity);		
		
		return entity;
	}
	
	/**
	 * 保存数据（必须带有关键字段）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void update(T entity){
		dao.updateByPrimaryKeySelective(entity);		
	}
	
	/**
	 * 保存数据（必须带有关键字段）
	 * @param entity
	 * @param loginStaffId 登录系统的员工ID
	 */
	@Transactional(readOnly = false)
	public void update(T entity,Long loginStaffId) {
		this.setUpdateInfo(entity, loginStaffId);
		dao.updateByPrimaryKeySelective(entity);
	}
	
	@Transactional(readOnly = false)
	public void updateByPrimaryKey(T entity,Long loginStaffId) {
		this.setUpdateInfo(entity, loginStaffId);
		dao.updateByPrimaryKey(entity);
	}
	
	/**
	 * 删除数据（物理）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(T entity,Long loginStaffId) {
		
		if(entity == null){
			return;
		}
		
		this.setUpdateInfo(entity, loginStaffId);
		this.setIsValid(entity, false);
		
		//提交更新
		dao.updateByPrimaryKeySelective(entity);
	}
	
	/**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param ids 如 "1,2,3,4"
     * @return
     */
	public List<T> getListByIds(String ids){
		
		if(StringUtils.isEmpty(ids) ){
			return Collections.emptyList();
		}
		ids = ids.trim();
		ids.replaceAll(" ", "");
		StringBuffer idBuffer= new StringBuffer();
		String[] idArray = ids.split(SPLIT_CHAR);
		for(String idStr:idArray){
			if(StringUtils.isLong(idStr)){
				idBuffer.append(idStr).append(SPLIT_CHAR);
			}
		}
		if(idBuffer.length()>0){
			idBuffer.deleteCharAt(idBuffer.length()-1);
			return dao.selectByIds(idBuffer.toString());
		}else{
			return Collections.emptyList();
		}
		
	}
	protected T getOneByExample(Example example){
		List<T> list = dao.selectByExampleAndRowBounds(example, new RowBounds(0, 1));
		if(list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	/**
	 * 根据Example条件查询列表数据
	 * @param example
	 * @return
	 */
	public List<T> getListByExample(Example example) {
		return dao.selectByExample(example);
	}
	
	/**
	 * 根据Example条件查询数量
	 * @param example
	 * @return
	 */
	public int getCountByExample(Example example) {
		return dao.selectCountByExample(example);
	}
}
