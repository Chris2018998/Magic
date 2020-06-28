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

import java.util.Calendar;
import java.util.Date;

import cn.magic.TypeConvertException;

/**
 * 日历转换
 * 
 * @author chris
 */

public class CalendarConverter extends UtilDateConverter{
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof Calendar){
			return value;
		}else if(value instanceof Date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date)value);
			return calendar;
		}else if(value instanceof Number){
			Number numValue =(Number)value;
			long time= numValue.longValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(time));
			return calendar;
		}else if(value instanceof String){
	    Date date = this.stringToDate((String)value);
      Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: Calendar.class");
		}
	}
}
