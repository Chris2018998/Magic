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

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;
import cn.magic.util.Symbols;

/**
 * 日历转换
 * 
 * @author chris
 */

public class UtilDateConverter extends TypeConverter {
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof Date){
			return value;
		}else if(value instanceof Number){
			Number numValue =(Number)value;
			return new Date(numValue.longValue());
		}else if(value instanceof Calendar){
			Calendar calendar =(Calendar)value;
			return calendar.getTime();
		}else if(value instanceof String){
			java.util.Date date= this.stringToDate((String)value);
			if(date==null)
				throw new TypeConvertException("Error Date content");
			else
				return date;
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: Date.class");
		}
	}
	
	/** 
   * 字符串转换为java.util.Date<br> 
   * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br> 
   * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br> 
   * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br> 
   * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br> 
   * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br> 
   * @param time String 字符串<br> 
   * @return Date 日期<br> 
   */ 
  protected Date stringToDate(String time){ 
    SimpleDateFormat formatter=null; 
    if(time.indexOf(":")>0){//带有时间
	    int tempPos=time.indexOf("AD"); 
	    time=time.trim(); 
	    formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z"); 
	    if(tempPos>-1){ 
	      time=time.substring(0,tempPos)+ "公元"+time.substring(tempPos+"AD".length());//china 
	      formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z"); 
	    }else if((time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR)==2) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	    }else if((time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR)==4) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	    }else if((time.indexOf(Symbols.LEFT_FILE_SEP_CHAR)==2) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("dd\\MM\\yyyy HH:mm:ss"); 
	    }else if((time.indexOf(Symbols.LEFT_FILE_SEP_CHAR)==4) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("yyyy\\MM\\dd HH:mm:ss"); 
	    }else if((time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR)==2) &&(time.indexOf("am")>-1) || (time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("dd/MM/yyyy KK:mm:ss a"); 
	    }else if((time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR)==4) &&(time.indexOf("am")>-1) || (time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss a"); 
	    }else if((time.indexOf(Symbols.LEFT_FILE_SEP_CHAR)==2) &&(time.indexOf("am")>-1) || (time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("dd\\MM\\yyyy KK:mm:ss a"); 
	    }else if((time.indexOf(Symbols.LEFT_FILE_SEP_CHAR)==4) &&(time.indexOf("am")>-1) || (time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("yyyy\\MM\\dd KK:mm:ss a");
	      
	    }else if((time.indexOf("-")==2) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
	    }else if((time.indexOf("-")==4) &&(time.indexOf(Symbols.Space)>-1)){ 
	      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	      
	    }else if((time.indexOf("-")==2) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("dd-MM-yyyy KK:mm:ss a"); 
	    }else if((time.indexOf("-")==4) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a"); 
	    } 
    }else{
      if(time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR)>0){
    		int pos = time.indexOf(Symbols.RIGHT_FILE_SEP_CHAR);
	      if(pos==2){
	      	formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	      }else if(pos==4){
	      	formatter = new SimpleDateFormat("yyyy/MM/dd"); 
	      }
	     }else if(time.indexOf(Symbols.LEFT_FILE_SEP_CHAR)>0){
    		int pos = time.indexOf(Symbols.LEFT_FILE_SEP_CHAR);
	      if(pos==2){
	      	formatter = new SimpleDateFormat("dd\\MM\\yyyy"); 
	      }else if(pos==4){
	      	formatter = new SimpleDateFormat("yyyy\\MM\\dd"); 
	      }
      }else if(time.indexOf("-")>0){
    		int pos = time.indexOf("-");
    	  if(pos==2){
	      	formatter = new SimpleDateFormat("dd-MM-yyyy"); 
	      }else if(pos==4){
	      	formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	      }
    	}else if(time.trim().length()==8){
    		try {
					formatter = new SimpleDateFormat("yyyyMMdd"); 
					formatter.parse(time);
				} catch (ParseException e) {
					formatter = new SimpleDateFormat("ddMMyyyy"); 
				}
      }else if(time.trim().length()==14){ 
	      formatter = new SimpleDateFormat("yyyyMMddHHmmss"); 
    	}
    }
    
    if(formatter!=null)
      return formatter.parse(time, new ParsePosition(0)); 
    else
    	return null;
  }  
}
