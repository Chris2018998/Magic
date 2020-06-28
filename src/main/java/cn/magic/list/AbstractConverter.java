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
package cn.magic.list;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;
import cn.magic.util.ArrayUtil;
import cn.magic.util.StringUtil;
import cn.magic.util.Symbols;

/**
 * List转换器
 * 
 * @author Liao批
 */

public abstract class AbstractConverter extends TypeConverter{
	/**
	 * @param list　需要转换的类型
	 * @param value 转换为目标类型集合
	 * @throws TypeConvertException 转换失败或不支持转换
	 */
	protected void convert(Collection list,Object value)throws TypeConvertException {
		if(value instanceof Collection){
		 list.addAll((Collection)value);
		}else if(value instanceof Iterator){
			Iterator itor = (Iterator)value;
			while(itor.hasNext())
			 list.add(itor.next());
		}else if(value instanceof Enumeration){
			Enumeration enumer =(Enumeration)value;
			while(enumer.hasMoreElements())
				list.add(enumer.nextElement());
		}else if(value instanceof String){
			String text =(String)value;
			String[]stringArray=new String[]{text};
			if(text.indexOf(",")!=-1)
			 stringArray=StringUtil.split(text,",");
			else if(text.indexOf("|")!=-1)
			 stringArray=StringUtil.split(text,"\\|");
		  else if(text.indexOf(Symbols.Space)!=-1)
			 stringArray=StringUtil.split(text,Symbols.Space);
			for(int i=0,n=stringArray.length;i<n;i++)
				list.add(stringArray[i]);
		}else if(ArrayUtil.isArray(value)){
			int arraySize = ArrayUtil.getArraySize(value);
			for(int i=0;i<arraySize;i++)
				list.add(ArrayUtil.getObject(value,i));
		}else{
			throw new TypeConvertException(new StringBuilder("Doesn't support object conversion from type: ").append(value.getClass().getName()).append(" to type:"+list.getClass().getName()).toString());
		}
	}
}
