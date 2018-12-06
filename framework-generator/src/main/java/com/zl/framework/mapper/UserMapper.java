package com.zl.framework.mapper;

import com.zl.framework.entity.User;

import java.util.List;
/**
* @desc: 持久层访问 接口  
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/

public interface UserMapper {

     /**
    * 查询所有的信息   
    * @desc:  查询所有的信息 接口   
    * @author: lollapalooza
    * @return :返回的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<User> listAllUser();


    /**
    * 根据属性条件查询的信息   
    * @desc:  根据属性条件查询的信息 接口  
    * @author: lollapalooza
    * @param record :的属性参数 
    * @return : 返回的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<User> listUserByCondition(User record);


    /**
    * 新增的信息   
    * @desc:  新增的信息  接口  
    * @author: lollapalooza
    * @param record :的实体类 
    * @return : 返回插入状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int insertUser(User record);


    /**
    * 根据PrimaryKey修改的信息   
    * @desc:  根据PrimaryKey修改的信息  接口  
    * @author: lollapalooza
    * @param record :的实体类 
    * @return : 返回修改状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int updateUserByPrimaryKey(User record);


    /**
    * 根据PrimaryKey删除的信息   
    * @desc:  根据PrimaryKey删除的信息  接口  
    * @author: lollapalooza
    * @param id : 主键数值 
    * @return : 返回删除状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int deleteUserByPrimaryKey(Long id);
}


