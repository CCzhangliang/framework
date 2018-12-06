package com.zl.framework.mapper;

import com.zl.framework.entity.TradeRightLimit;

import java.util.List;
/**
* @desc: 开户白名单持久层访问 接口  
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/

public interface TradeRightLimitMapper {

     /**
    * 查询所有开户白名单的信息   
    * @desc:  查询所有开户白名单的信息 接口   
    * @author: lollapalooza
    * @return :返回开户白名单的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<TradeRightLimit> listAllTradeRightLimit();


    /**
    * 根据属性条件查询开户白名单的信息   
    * @desc:  根据属性条件查询开户白名单的信息 接口  
    * @author: lollapalooza
    * @param record :开户白名单的属性参数 
    * @return : 返回开户白名单的信息
    * @createTime: 2018-10-30 10:55:35
    */
    List<TradeRightLimit> listTradeRightLimitByCondition(TradeRightLimit record);


    /**
    * 新增开户白名单的信息   
    * @desc:  新增开户白名单的信息  接口  
    * @author: lollapalooza
    * @param record :开户白名单的实体类 
    * @return : 返回插入状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int insertTradeRightLimit(TradeRightLimit record);


    /**
    * 根据PrimaryKey修改开户白名单的信息   
    * @desc:  根据PrimaryKey修改开户白名单的信息  接口  
    * @author: lollapalooza
    * @param record :开户白名单的实体类 
    * @return : 返回修改状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int updateTradeRightLimitByPrimaryKey(TradeRightLimit record);


    /**
    * 根据PrimaryKey删除开户白名单的信息   
    * @desc:  根据PrimaryKey删除开户白名单的信息  接口  
    * @author: lollapalooza
    * @param id : 主键数值 
    * @return : 返回删除状态信息
    * @createTime: 2018-10-30 10:55:35
    */
    int deleteTradeRightLimitByPrimaryKey(Long id);
}


