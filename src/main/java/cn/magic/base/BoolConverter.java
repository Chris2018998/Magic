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
package cn.magic.base;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;

/**
 * 布尔类型转换
 * 
 * @author chris
 */
public class BoolConverter extends TypeConverter {
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof Boolean){
			 return value;
		}else if(value instanceof String){
			String strValue =((String)value).trim();
			if(strValue.equalsIgnoreCase("Y") || strValue.equalsIgnoreCase("TRUE")){
			 return Boolean.TRUE;
			}else{
			 	try {
					long testValue = Long.parseLong(strValue);
					return (testValue > 0) ? Boolean.TRUE : Boolean.FALSE;
				} catch (NumberFormatException e) {
				}
				return Boolean.FALSE;
			}
		}else if(value instanceof Character){
			Character charValue =(Character)value;
			if(charValue.toString().equalsIgnoreCase("Y")){
				return Boolean.TRUE;
			}else{
				try {
					char testValue = charValue.charValue();
					return (testValue > 0) ? Boolean.TRUE : Boolean.FALSE;
				} catch (NumberFormatException e) {
				}
				return Boolean.FALSE;
			}
		}else if(value instanceof Number){
			Number numValue =(Number)value;
			return (numValue.intValue()>0)?Boolean.TRUE:Boolean.FALSE;
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: Boolean.class");
		}
	}
}
