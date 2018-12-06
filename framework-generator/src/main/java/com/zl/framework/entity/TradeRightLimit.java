package com.zl.framework.entity;

import java.io.Serializable;

/**
* @desc: 开户白名单
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/
public class TradeRightLimit implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
    * @desc: 国家代码
    * @author: lollapalooza
    */
	private Integer countryCode;

	/**
    * @desc: 杠杆级别 0 无交易权限 1 一倍杠杆权限  5 五倍杠杆权限  10 十倍杠杆权限  20 二十倍杠杆权限
    * @author: lollapalooza
    */
	private Integer leverage;

	/**
    * @desc: 创建人
    * @author: lollapalooza
    */
	private String createId;

	/**
    * @desc: 创建时间
    * @author: lollapalooza
    */
	private Long createTime;

	/**
    * @desc: 更新人
    * @author: lollapalooza
    */
	private String updateId;

	/**
    * @desc: 更新时间
    * @author: lollapalooza
    */
	private Long updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getLeverage() {
		return leverage;
	}

	public void setLeverage(Integer leverage) {
		this.leverage = leverage;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
