package com.jt.common.vo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * easyui-grid 表格响应实体
 * 
 * @author zain
 * 16/10/07
 */
public class EasyUIResult {

	//定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private Integer total; //总记录数
	private List<?> rows;  //当前页的集合
	
	public EasyUIResult(){}
	
	public EasyUIResult(Integer total, List<?> rows) {
	    this.total = total;
	    this.rows = rows;
	}
	
	public EasyUIResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

	
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
    
    /**
     * Object是集合转换
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static EasyUIResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if(data.isArray() && data.size() > 0) {
                list = MAPPER.readValue(data.traverse(), 
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new EasyUIResult(jsonNode.get("total").intValue(), list);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
