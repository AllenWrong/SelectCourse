package com.course.dao;

import java.util.ArrayList;

/**
 * 表结构的访问对象设计标准
 * @author Thingcor
 *
 */
public interface TableDao {
	/***/
	public boolean doCreate(Object object) throws Exception;
	/***/
	public boolean doUpdate(Object object) throws Exception;
	/***/
	public boolean doDelete(Object object) throws Exception;
	/***/
	public ArrayList<?> findAll() throws Exception;
	/***/
	public Object findByMainKey(String mainKey) throws Exception;
}
