package com.laifeiyang.dev.micro.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class KeyProvider {

	public static String getPrimaryKey(){
		return UUID.randomUUID().toString();
	}
	
	public static String getflowcode(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
		return sdf.format(new Date());
	}

	public static String getKeyString(){
		return ToolsStr.removeNumberOfString(UUID.randomUUID().toString()).replaceAll("-", "");
	}
}
