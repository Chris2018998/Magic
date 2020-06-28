/*
 * Copyright Chris2018998
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.magic.map;

import java.util.HashMap;
import java.util.Map;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;

/**
 * HashMap转换器
 * 
 * @author Liao
 */
public class HashMapConverter extends TypeConverter {
	public Object convert(Object value)throws TypeConvertException {
		if(value==null){
			return null;
	  }else if(value instanceof HashMap){
			return value;
		}else if(value instanceof Map){
			Map oldMap = (Map)value;
			Map newMap = new HashMap();
			newMap.putAll(oldMap);
		  return newMap;
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type:"+HashMap.class.getName());
		}
	}
}
