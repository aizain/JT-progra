package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

/**
 * 商品类目
 * @author zain
 * 16/11/06
 */
@Service
public class ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 根据父id查询
	 * @param parentId
	 * @return
	 */
	public List<ItemCat> queryItemCat(Long parentId) {
		//return itemCatMapper.queryItemCat(parentId);
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}

	/**
	 * 查询全部
	 * 
	 * 前台系统需要json串，
	 * 分三级目录，每层目录都有自己的结构
	 * 
	 * @return
	 */
    public ItemCatResult queryWebAll() {
        ItemCat itemCat = new ItemCat();
        itemCat.setStatus(1); // 状态1为正常
        List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
        ItemCatResult result = new ItemCatResult();
        
        // 转为map存储，key为父节点，value为数据集合。 父id - 父id拥有的iCat的List
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<>();
        for(ItemCat iCat : itemCatList) {
            if(!itemCatMap.containsKey(iCat.getParentId())) {
                itemCatMap.put(iCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(iCat.getParentId()).add(iCat);
        }
        
        // 封装一级对象 取出父id为0的
        List<ItemCat> itemCatListLv1 = itemCatMap.get(0L);
        for(ItemCat iCat : itemCatListLv1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + iCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            
            result.getItemCatDatas().add(itemCatData);
            if(!iCat.getIsParent()) {
                continue;
            }
            
            // 封装二级对象
            List<ItemCat> itemCatListLv2 = itemCatMap.get(iCat.getId());
            List<ItemCatData> itemCatDataList = new ArrayList<>();
            itemCatData.setItems(itemCatDataList);
            for(ItemCat iCatLv2 : itemCatListLv2) {
                ItemCatData itemCatDataLv2 = new ItemCatData();
                itemCatDataLv2.setName(iCatLv2.getName());
                itemCatDataLv2.setUrl("/products/" + iCatLv2.getId() + ".html");
                itemCatDataList.add(itemCatDataLv2);
                if(iCatLv2.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatListLv3 = itemCatMap.get(iCatLv2.getId());
                    List<String> itemCatDataLv3 = new ArrayList<>();
                    itemCatDataLv2.setItems(itemCatDataLv3);
                    for(ItemCat iCatLv3 : itemCatListLv3) {
                        itemCatDataLv3.add("/products/" + iCatLv3.getId() + ".html|" + iCatLv3.getName());
                    }
                }
            }
            if(result.getItemCatDatas().size() >= 14) {
                break;
            }
        }
        return result;
    }
	
}
