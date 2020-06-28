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

import java.util.Calendar;
import java.util.Date;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;

/**
 * Integer转换
 * 
 * @author chris
 */

public class IntegerConverter extends TypeConverter {
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof String){
		 return Integer.valueOf((String)value);
		}else if(value instanceof Character){
			return Integer.valueOf((int)((Character)value).charValue());
		}else if(value instanceof Number){
			return Integer.valueOf(((Number)value).intValue());
		}else if(value instanceof Date){
			return Integer.valueOf(Long.valueOf(((Date)value).getTime()).intValue());
		}else if(value instanceof Calendar){
			return Integer.valueOf(Long.valueOf(((Calendar)value).getTime().getTime()).intValue());
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: Integer.class");
		}
	}
}
