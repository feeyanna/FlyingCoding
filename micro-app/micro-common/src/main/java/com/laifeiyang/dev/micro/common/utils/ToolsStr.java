package com.laifeiyang.dev.micro.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolsStr {

	public static List<Integer> getnumlist(String str){
		Pattern p = Pattern.compile("[0-9\\.]+");
		Matcher m = p.matcher(str);
		List<Integer> listnum=new ArrayList<>();
		while(m.find()){
			listnum.add(new Integer(m.group()).intValue());
		}
		return listnum;
	}
	
	public static List<String[]> getippart(String ippart){
		List<String[]> list=new ArrayList<>();
		String[] st=ippart.split("-");
		if(st.length==1){
			String[] str=st[0].split("\\.");
			if(str.length!=4)return list;
			else list.add(str);
		}else if(st.length==2){
			String[] str1=st[0].split("\\.");
			String[] str2=st[1].split("\\.");
			if(str1.length!=4||str2.length!=4)return list;
			else{
				list.add(str1);
				list.add(str2);
			}
		}else return list;
		return list;
	}
	public StringBuffer creatsql(String fx,String zf){
		List<String[]> listfx=this.listfx(fx);
		List<String[]> listzf=this.listzf(zf);
		if(!zf.equals("")){
			
		}
		return null;
	}
	public static List<String[]> listzf(String zf){
		List<String[]> listzf=new ArrayList<>();
		if(zf.equals(""))return listzf;
		String[] zifu=zf.split(":");
		for(int i=0;i<zifu.length;i++){
			listzf.add(zifu[i].split(","));
		}
		return listzf;
	}
	public static List<String[]> listfx(String fx){
		List<String[]> listfx=new ArrayList<>();
		if(fx.equals(""))return listfx;
		String[] fuxuan=fx.split(":");
		if(fuxuan.length==1){
			listfx.add(fuxuan[0].split(","));
			return listfx;
		}
		int i=0,t=0;
		while(i<fuxuan.length-1){//遍历所有复选字段
			t=i;
			StringBuffer sb=new StringBuffer();
			sb.append(fuxuan[i].split(",")[0]+","+fuxuan[i].split(",")[1]);//ss[0]为字段ID，ss[1]为值，第一个值为字段ID
			boolean flag=false;
			for(int j=i+1;j<fuxuan.length;j++){
				if(fuxuan[j].split(",")[0].equals(fuxuan[i].split(",")[0])){
					sb.append(","+fuxuan[j].split(",")[1]);//加入取值
					flag=true;
					t=j;
				}
			}//for fuxuan
			if(flag==false&&i+1==fuxuan.length-1){
				listfx.add(fuxuan[i+1].split(","));//得到List,字段ID与对应值sc[0],为ID，sc[1]以上为值				
			}
			String[] sc=sb.toString().split(",");
			listfx.add(sc);//得到List,字段ID与对应值sc[0],为ID，sc[1]以上为值
			i=t+1;
			}//遍历所有复选字段
		return listfx;
	}
	
	public static List<String[]> listsz(String sz){
		List<String[]> listsz=new ArrayList<>();
		if(sz.length()==0)return listsz;
		String[] shuzi=sz.split(":");
		for(int i=0;i<shuzi.length;i++){
			listsz.add(shuzi[i].split(","));
		}
		return listsz;
	}
	
	public static List<String[]> listriqi(String riqi){
		List<String[]> listsz=new ArrayList<>();
		if(riqi.length()==0)return listsz;
		String[] rq=riqi.split(";");//日期用;避免和：重叠
		for(int i=0;i<rq.length;i++){
			listsz.add(rq[i].split(","));
		}
		return listsz;
	}

	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public static String removeNumberOfString(String str) {
		Pattern p1 = Pattern.compile("[\\d]");
		Matcher matche1 = p1.matcher(str);
		String result1 = matche1.replaceAll("");
		return result1;
	}
	
	public static String getFieldCode() {
		String k=KeyProvider.getPrimaryKey();
		k=removeNumberOfString(k).replaceAll("-", "");
		if(k.length()>30) {
			k=k.substring(0,29);
		}
		return k;
	}
	
}
