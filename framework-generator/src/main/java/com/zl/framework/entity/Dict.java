package com.zl.framework.entity;

import java.io.Serializable;

/**
* @desc: 数据字典
* @author: lollapalooza
* @createTime: 2018-10-30 10:55:35
* @version: v1.0
*/
public class Dict implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
    * @desc: 字典类
    * @author: lollapalooza
    */
	private String dictClass;

	/**
    * @desc: 字典值
    * @author: lollapalooza
    */
	private Integer dictValue;

	/**
    * @desc: 字典中文名称
    * @author: lollapalooza
    */
	private String dictNameCn;

	/**
    * @desc: 字典英文名称
    * @author: lollapalooza
    */
	private String dictNameEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictClass() {
		return dictClass;
	}

	public void setDictClass(String dictClass) {
		this.dictClass = dictClass;
	}

	public Integer getDictValue() {
		return dictValue;
	}

	public void setDictValue(Integer dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictNameCn() {
		return dictNameCn;
	}

	public void setDictNameCn(String dictNameCn) {
		this.dictNameCn = dictNameCn;
	}

	public String getDictNameEn() {
		return dictNameEn;
	}

	public void setDictNameEn(String dictNameEn) {
		this.dictNameEn = dictNameEn;
	}

}
