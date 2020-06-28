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
package cn.magic.date;

import java.sql.Time;
import java.util.Calendar;

import cn.magic.TypeConvertException;

/**
 * 日历转换
 * 
 * @author chris
 */

public class DateTimeConverter extends UtilDateConverter{
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof Time){
			return value;
		}else if(value instanceof java.util.Date){
			return new Time(((java.util.Date)value).getTime());
		}else if(value instanceof Number){
			Number numValue =(Number)value;
			return new Time(numValue.longValue());
		}else if(value instanceof Calendar){
			Calendar calendar =(Calendar)value;
			return new Time(calendar.getTime().getTime());
		}else if(value instanceof String){
      return new Time(this.stringToDate((String)value).getTime());
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: java.sql.Time");
		}
	}
}
