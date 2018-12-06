package com.zl.framework.mapper.slave;

import com.zl.framework.entity.Hobby;

import java.util.List;

/**
* @desc: 持久层访问 接口  
* @author: lollapalooza
* @createTime: 2018-10-30 11:21:31
* @version: v1.0
*/

public interface HobbyMapper {

     /**
    * 查询所有的信息   
    * @desc:  查询所有的信息 接口   
    * @author: lollapalooza
    * @return :返回的信息
    * @createTime: 2018-10-30 11:21:31
    */
    List<Hobby> listAllHobby();


    /**
    * 根据属性条件查询的信息   
    * @desc:  根据属性条件查询的信息 接口  
    * @author: lollapalooza
    * @param record :的属性参数 
    * @return : 返回的信息
    * @createTime: 2018-10-30 11:21:31
    */
    List<Hobby> listHobbyByCondition(Hobby record);


    /**
    * 新增的信息   
    * @desc:  新增的信息  接口  
    * @author: lollapalooza
    * @param record :的实体类 
    * @return : 返回插入状态信息
    * @createTime: 2018-10-30 11:21:31
    */
    int insertHobby(Hobby record);


    /**
    * 根据PrimaryKey修改的信息   
    * @desc:  根据PrimaryKey修改的信息  接口  
    * @author: lollapalooza
    * @param record :的实体类 
    * @return : 返回修改状态信息
    * @createTime: 2018-10-30 11:21:31
    */
    int updateHobbyByPrimaryKey(Hobby record);


    /**
    * 根据PrimaryKey删除的信息   
    * @desc:  根据PrimaryKey删除的信息  接口  
    * @author: lollapalooza
    * @param id : 主键数值 
    * @return : 返回删除状态信息
    * @createTime: 2018-10-30 11:21:31
    */
    int deleteHobbyByPrimaryKey(Long id);
}


