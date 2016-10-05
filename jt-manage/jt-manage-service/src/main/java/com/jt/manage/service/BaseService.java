package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.base.mapper.SysMapper;

@Service
public abstract class BaseService<T> {
	
	@Autowired
	private SysMapper<T> sysMapper;
	
	//传统写法，Spring4以下版本
	//public abstract SysMapper<T> getSysMapper();
	
	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Object id) {
		return this.sysMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据条件查询
	 * 多条件之间是 and 关系
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryListByWhere(T t) {
		return this.sysMapper.select(t);
	}
	
	/**
	 * 根据条件查询单条数据
	 * 
	 * @param t
	 * @return
	 */
	public T queryByWhere(T t) {
		List<T> list = queryListByWhere(t);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> queryAll() {
		return this.sysMapper.select(null);
	}
	
	/**
	 * 新增数据
	 * 使用全部字段
	 * 
	 * @param t
	 */
	public void save(T t) {
		this.sysMapper.insert(t);
	}
	
	/**
	 * 新增数据
	 * 使用不为null的字段
	 * 
	 * @param t
	 */
	public void saveSelection(T t) {
		this.sysMapper.insertSelective(t);
	}
	
	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteById(Object id) {
		return this.sysMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据ids删除
	 * 
	 * @param ids
	 * @return
	 */
	public Integer deleteByIds(Object[] ids) {
		return this.sysMapper.deleteByIDS(ids);
	}
	
	/**
	 * 根据条件删除
	 * 
	 * @param t
	 * @return
	 */
	public Integer deleteByWhere(T t) {
		return this.sysMapper.delete(t);
	}
	
	/**
	 * 更新数据
	 * 使用全部字段
	 * 
	 * @param t
	 * @return
	 */
	public Integer update(T t) {
		return this.sysMapper.updateByPrimaryKey(t);
	}
	
	/**
	 * 更新数据
	 * 使用不为null的字段
	 * 
	 * @param t
	 * @return
	 */
	public Integer updateSelection(T t) {
		return this.sysMapper.updateByPrimaryKeySelective(t);
	}
}
