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
import java.util.TreeSet;

import cn.magic.TypeConvertException;

/**
 * TreeSet转换器
 * 
 * @author Liao
 */
public class TreeSetConverter extends AbstractConverter{
	public Object convert(Object value)throws TypeConvertException {
		if(value==null){
			return null;
	  }else if(value instanceof TreeSet){
			return value;
		}else{
		  Collection set = new TreeSet();
		  this.convert(set,value);
		  return set;
		}
	}
}