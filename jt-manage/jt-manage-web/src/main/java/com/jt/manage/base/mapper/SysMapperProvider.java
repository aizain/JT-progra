package com.jt.manage.base.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;

/**
 * 继承通用Mapper类
 * 进行方法的扩充
 * 
 * @author zain
 */
public class SysMapperProvider extends MapperProvider {
	
	public SysMapperProvider (Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	public SqlNode deleteByIDS(MappedStatement ms) {
		 Class<?> entityClass = getSelectReturnType(ms);
		 Set<EntityHelper.EntityColumn> entityColumns = 
				 EntityHelper.getPKColumns(entityClass);
		 EntityHelper.EntityColumn column = null;
		 for(EntityHelper.EntityColumn entityColumn : entityColumns) {
			 column = entityColumn;
			 break;
		 }
		 
		 List<SqlNode> sqlNodes = new ArrayList<>();
		 // 开始拼sql
		 SqlBuilder.BEGIN();
		 // delete from table
		 SqlBuilder.DELETE_FROM(tableName(entityClass));
		 // 得到sql
		 String sql = SqlBuilder.SQL();
		 // 静态SQL
		 sqlNodes.add(new StaticTextSqlNode(
				 sql + " WHERE " + column.getColumn() + " IN "));
		 // 构造foreach sql
		 SqlNode foreach = new ForEachSqlNode(
				 ms.getConfiguration(), new StaticTextSqlNode("#{" + column.getProperty()
				 		+ "}"), "ids", "index", column.getProperty(), "(", ")", ",");
		 sqlNodes.add(foreach);
		 return new MixedSqlNode(sqlNodes);
	}
	
}