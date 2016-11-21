package com.jt.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ContentCategoryMapper;
import com.jt.manage.pojo.ContentCategory;

/**
 * 处理内容分类Dao
 * @author zain
 * 16/11/13
 */
@Service
public class ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;
    
    /**
     * 查询全部内容分类
     * @param contentCategory 
     * @return
     */
    public List<ContentCategory> queryList(Long pId) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(pId);
        return contentCategoryMapper.select(contentCategory);
    }

    /**
     * 新增一条数据
     * @param contentCategory
     * @return
     */
    public SysResult create(ContentCategory contentCategory) {
        contentCategory.setStatus(1); // 1正常 2删除
        contentCategory.setIsParent(false); // 新增节点都是叶子
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(contentCategory.getCreated());
        contentCategoryMapper.insert(contentCategory);
        
        // 如果在叶子节点上新增，当前这个叶子就成为父节点
        ContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        if(!parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }
        
        return SysResult.ok(contentCategory);
    }

    /**
     * 修改一条数据
     * @param contentCategory
     * @return
     */
    public SysResult update(ContentCategory contentCategory) {
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return SysResult.ok();
    }

    /**
     * 删除一条数据
     * @param contentCategory
     * @return
     */
    public SysResult delete(ContentCategory contentCategory) {
        contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
        
        // 删除的是分支，分支下的所有的节点都被删除，级联删除
        // 递归：参数pId
        List<Long> deleteIds = new ArrayList<>();
        findContentCategoty(deleteIds, contentCategory.getId());
        contentCategoryMapper.deleteByIDS(deleteIds.toArray());
        
        
        // 如果这个节点已经没有其他的兄弟节点，就设置当前节点的父节点的isParent = false
        // 删除完，查询当前父节点下是否还有记录，如果记录大于1代表还有兄弟，不动，反之设置isParent = false
        ContentCategory param = new ContentCategory();
        contentCategory.setParentId(contentCategory.getParentId());
        List<ContentCategory> oList = contentCategoryMapper.select(param);
        
        if (null != oList && oList.size() == 0) {
            // 更改父节点的isParent = false
            ContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
            parentContentCategory.setIsParent(false);
            parentContentCategory.setUpdated(new Date());
            contentCategoryMapper.updateByPrimaryKeySelective(parentContentCategory);
        }
        
        return SysResult.ok();
    }
    
    /**
     *  查询节点下的所有子节点
     * @param deleteIds
     * @param pId
     */
    private void findContentCategoty(List<Long> deleteIds, Long pId) {
        ContentCategory cc = new ContentCategory();
        cc.setParentId(pId);
        List<ContentCategory> oList = contentCategoryMapper.select(cc);
        
        // 有子节点
        if (oList != null && oList.size() > 0) {
            for(int i=0; i<oList.size(); i++) {
                ContentCategory contentCategory = oList.get(i);
                deleteIds.add(contentCategory.getId());
                
                findContentCategoty(deleteIds, contentCategory.getId());
            }
        } 
    }

}
