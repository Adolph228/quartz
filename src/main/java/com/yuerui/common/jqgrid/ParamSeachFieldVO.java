/**
 * 
 */
package com.yuerui.common.jqgrid;

import java.io.Serializable;

import lombok.Data;


public @Data class ParamSeachFieldVO implements Serializable {
	private static final long serialVersionUID = -1965858666004581674L;
	/** 查询列名 */
	private String searchField;
	/** 查询数据 */
	private String searchValue;
	/** 查询数据 (boolean类型)*/
	private Boolean searchValueBoolean;
	/**
	 * 查询方式 eq：等于(默认)；like：包含 
	 * */
	private String searchOper = JqGridParam.operType.eq.name();
	
	public ParamSeachFieldVO(){}
	
	/**
	 * 等同条件
	 * @param searchField			字段
	 * @param searchValue		值
	 */
	public ParamSeachFieldVO(String searchField, String searchValue){
		this.searchField = searchField;
		this.searchValue = searchValue;
	}
	
	/**
	 * 外传条件
	 * @param searchField		字段
	 * @param searchValue	值
	 * @param searchOper		查看JqGridParam.operType
	 */
	public ParamSeachFieldVO(String searchField, String searchValue, String searchOper ){
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.searchOper = searchOper;
	}
}
