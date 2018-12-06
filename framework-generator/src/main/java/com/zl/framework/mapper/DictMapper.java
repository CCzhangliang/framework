package com.zl.framework.mapper;

import com.zl.framework.entity.Dict;

import java.util.List;
/**
* @desc: 数据字典持久层访问 接口  
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/

public interface DictMapper {

     /**
    * 查询所有数据字典的信息   
    * @desc:  查询所有数据字典的信息 接口   
    * @author: lollapalooza
    * @return :返回数据字典的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<Dict> listAllDict();


    /**
    * 根据属性条件查询数据字典的信息   
    * @desc:  根据属性条件查询数据字典的信息 接口  
    * @author: lollapalooza
    * @param record :数据字典的属性参数 
    * @return : 返回数据字典的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<Dict> listDictByCondition(Dict record);


    /**
    * 新增数据字典的信息   
    * @desc:  新增数据字典的信息  接口  
    * @author: lollapalooza
    * @param record :数据字典的实体类 
    * @return : 返回插入状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int insertDict(Dict record);


    /**
    * 根据PrimaryKey修改数据字典的信息   
    * @desc:  根据PrimaryKey修改数据字典的信息  接口  
    * @author: lollapalooza
    * @param record :数据字典的实体类 
    * @return : 返回修改状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int updateDictByPrimaryKey(Dict record);


    /**
    * 根据PrimaryKey删除数据字典的信息   
    * @desc:  根据PrimaryKey删除数据字典的信息  接口  
    * @author: lollapalooza
    * @param id : 主键数值 
    * @return : 返回删除状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int deleteDictByPrimaryKey(Long id);
}


