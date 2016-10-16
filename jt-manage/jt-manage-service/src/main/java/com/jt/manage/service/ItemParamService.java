package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.mapper.ItemParamMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.pojo.ItemParam;

/**
 * 商品规格模版
 * 
 * @author zain
 * 16/10/16
 */
@Service
public class ItemParamService {
    
    @Autowired
    private ItemParamMapper itemParamMapper;
    
    /**
     * 查询商品规格模版列表
     * 分页
     * 
     * @param page
     * @param rows
     * @return
     */
    public EasyUIResult queryList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows); //先分页
        List<ItemParam> itemParamList = itemParamMapper.select(null); 
        PageInfo<ItemParam> pageInfo = new PageInfo<>(itemParamList);
        
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 查询
     * 商品规格模版的商品分类
     * 
     * @param id
     * @return
     */
    public SysResult queryItemCat(Long id) {
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(id);
        List<ItemParam> itemParamList = itemParamMapper.select(itemParam);
        
        //有可能会在list中存入空对象
        if(null != itemParamList && itemParamList.size() > 0) {
            return SysResult.ok(itemParamList.get(0));
        }
        
        return SysResult.ok();
    }

    /**
     * 保存规格模版
     * 
     * @param itemCatId
     * @param paramData
     * @return
     */
    public SysResult save(Long itemCatId, String paramData) {
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(itemParam.getCreated());
        
        itemParamMapper.insert(itemParam);
        return SysResult.ok();
    }

    /**
     * 批量删除规格模版
     * 
     * @param ids
     * @return
     */
    public SysResult delete(Long[] ids) {
        itemParamMapper.deleteByIDS(ids);
        return SysResult.ok();
    }
    
}
