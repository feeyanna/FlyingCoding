
package com.laifeiyang.dev.micro.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *     公共ID-NAME-VO对象
 * </p>
 */
@ApiModel("ID-NAME-VO")
public class CommonIdName {

	@ApiModelProperty("id")
	private String id;
	
	@ApiModelProperty("名称")
	private String name;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CommonIdName(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}


	public CommonIdName() {
		super();
	}


	@Override
	public String toString() {
		return "CommonIdName [id=" + id + ", name=" + name + "]";
	}


	
	
}
