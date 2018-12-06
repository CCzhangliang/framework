package com.zl.framework.entity;

import java.io.Serializable;

/**
* @desc: 前端访问限制
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/
public class WebControl implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
    * @desc: API类型: 1:公开行情接口 2:用户私有接口
    * @author: lollapalooza
    */
	private Integer webType;

	/**
    * @desc: 访问限制次数
    * @author: lollapalooza
    */
	private Integer controlLimitNum;

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

	public Integer getWebType() {
		return webType;
	}

	public void setWebType(Integer webType) {
		this.webType = webType;
	}

	public Integer getControlLimitNum() {
		return controlLimitNum;
	}

	public void setControlLimitNum(Integer controlLimitNum) {
		this.controlLimitNum = controlLimitNum;
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
