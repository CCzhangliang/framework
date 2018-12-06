package com.zl.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @desc: 
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
    * @desc: 姓名
    * @author: lollapalooza
    */
	private String name;

	/**
    * @desc: 
    * @author: lollapalooza
    */
	private String workAddress;

	/**
    * @desc: 
    * @author: lollapalooza
    */
	private BigDecimal val;

	/**
    * @desc: 
    * @author: lollapalooza
    */
	private BigDecimal rea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	public BigDecimal getRea() {
		return rea;
	}

	public void setRea(BigDecimal rea) {
		this.rea = rea;
	}

}
