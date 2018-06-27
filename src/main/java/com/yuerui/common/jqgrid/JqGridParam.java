package com.yuerui.common.jqgrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;


public @Data class JqGridParam implements Serializable {
	private static final long serialVersionUID = 1L;

	// 和JqGrid组件相关的参数属性
	/**
	 * 是否是搜索请求
	 */
	private boolean _search = false;

	/**
	 * 搜索随机数
	 */
	private long nd;

	/** 用于排序的列名 */
	private String sidx;

	/**
	 * 排序的方式 asc desc
	 */
	private String sord;

	/** 过滤属性 */
	private String filters;
	
	/**
	 * 查询关键词列表
	 */
	private List<ParamSeachFieldVO> postDatas = new ArrayList<ParamSeachFieldVO>();

	/** 总页数 */
	private long total;
	/** 页码 */
	private int page;
	/** 记录数 */
	private long records;
	/** 每页几条记录 */
	private int rows;

	// 自定义的参数，企业编号
	private Long cid;
	
	// 自定义的参数，id
	private Long id;
	
	// 自定义的参数，外键id
	private Long keyId;
	
	// 自定义的参数，阶段
	private Byte stage;
	
	// 自定义的参数，状态
	private Byte state;
	
	
	/**
	 * 职业监管系统用户的登录的Id
	 */
	private Long loginUserId;
	
	private Long orgId;

	public JqGridParam() {
		// this.rows = 10;
		// this.page = 1;
	}

	
	public static enum sordType {
		asc, desc
	};
	
	public static enum operType {
		eq, like,notEq,isNull,notNull
	};
	
}
