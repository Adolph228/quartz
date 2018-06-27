package com.yuerui.common.service;

import java.util.List;

public interface IBaseService<T> {
	
	
	T add(T entity);
	
	T add(T entity,Long addBy);
	
	
	void update(T entity);
	
	void update(T entity,Long updateBy);
	
	
	void updateByPrimaryKey(T entity,Long updateBy);
	
	
	void delete(T entity,Long deleteBy);
	
	
	void deleteById(Long id);
	
	
	List<T> getListByIds(String ids);

	T getById(Long id);
}
