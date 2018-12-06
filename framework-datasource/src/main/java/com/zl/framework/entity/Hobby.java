package com.zl.framework.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @desc: 
* @author: lollapalooza
* @createTime: 2018-10-30 11:21:31
* @version: v1.0
*/
public class Hobby implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
    * @desc: 
    * @author: lollapalooza
    */
	private String hobby;

	/**
    * @desc: 
    * @author: lollapalooza
    */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
