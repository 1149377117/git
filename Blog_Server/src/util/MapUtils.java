package util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * map工具类，进行对象转换
 * @author Administrator
 *
 */
public class MapUtils {
	 
	public static <T> T map2Bean(Map<String, String> map,Class<T> c){
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
		return JSON.parseObject(itemJSONObj.toJSONString(), c);
	}
	
	public static <T> List<T> map2List(List<Map<String, String>> list,Class<T> c){
		String st = JSONArray.toJSONString(list);
		return JSONArray.parseArray(st,c);
	}
	
}
