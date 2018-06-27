package com.yuerui.common.database;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;

public interface CommonMapper<T>  extends BaseMapper<T>, ExampleMapper<T>,
										  RowBoundsMapper<T>, IdsMapper<T>, 
										  MySqlMapper<T>, ConditionMapper<T> {

}
