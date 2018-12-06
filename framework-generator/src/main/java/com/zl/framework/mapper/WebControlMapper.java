package com.zl.framework.mapper;

import com.zl.framework.entity.WebControl;

import java.util.List;
/**
* @desc: 前端访问限制持久层访问 接口  
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/

public interface WebControlMapper {

     /**
    * 查询所有前端访问限制的信息   
    * @desc:  查询所有前端访问限制的信息 接口   
    * @author: lollapalooza
    * @return :返回前端访问限制的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<WebControl> listAllWebControl();


    /**
    * 根据属性条件查询前端访问限制的信息   
    * @desc:  根据属性条件查询前端访问限制的信息 接口  
    * @author: lollapalooza
    * @param record :前端访问限制的属性参数 
    * @return : 返回前端访问限制的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<WebControl> listWebControlByCondition(WebControl record);


    /**
    * 新增前端访问限制的信息   
    * @desc:  新增前端访问限制的信息  接口  
    * @author: lollapalooza
    * @param record :前端访问限制的实体类 
    * @return : 返回插入状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int insertWebControl(WebControl record);


    /**
    * 根据PrimaryKey修改前端访问限制的信息   
    * @desc:  根据PrimaryKey修改前端访问限制的信息  接口  
    * @author: lollapalooza
    * @param record :前端访问限制的实体类 
    * @return : 返回修改状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int updateWebControlByPrimaryKey(WebControl record);


    /**
    * 根据PrimaryKey删除前端访问限制的信息   
    * @desc:  根据PrimaryKey删除前端访问限制的信息  接口  
    * @author: lollapalooza
    * @param id : 主键数值 
    * @return : 返回删除状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int deleteWebControlByPrimaryKey(Long id);
}


