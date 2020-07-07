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
package cn.magic;

import cn.magic.base.*;
import cn.magic.blob.BlobConverter;
import cn.magic.clob.ClobConverter;
import cn.magic.date.*;
import cn.magic.list.*;
import cn.magic.map.*;
import cn.magic.math.BigDecimalConverter;
import cn.magic.math.BigIntegerConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象转换工厂
 * 
 * @author Chris
 */

public class TypeConvertFactory {
	private ConcurrentHashMap<Object,TypeConverter> converterMap;
	public TypeConvertFactory(){
		this.converterMap= new ConcurrentHashMap();
		registerDefaultConverter();
	}

	/**
	 * 移除某类型的转换器
	 *
	 * @param type 目标类型
	 */
	public void removeTypeConverter(Class type){ this.converterMap.remove(type); }

	/**
	 * 判断是否存在某类型的转换器
	 *
	 * @param typeKey 目标类型
	 * @return 存在返回true,否则为false
	 */
	public boolean containsTypeConverter(Object typeKey){
		return this.converterMap.containsKey(typeKey);
	}

	/**
	 * 获取类型转换器
	 *
	 * @param typeKey 目标类型
	 * @return 类型转换器
	 */
	public TypeConverter getTypeConverter(Object typeKey){
	  return(TypeConverter)this.converterMap.get(typeKey);
	}

	/**
	 * 增加类型转换器
	 *
	 * @param typeKey 目标类型
	 * @param converter 类型转换器
	 */
	public void addTypeConverter(Object typeKey,TypeConverter converter){
		this.converterMap.put(typeKey,converter);
	}

	/**
	 * 类型转换
	 *
	 * @param source　需要转换的值
	 * @param typeKey　转换的目标类型
	 * @return　转换后的类型值
	 * @throws TypeConvertException 类型转换器不存在,转换失败,不支持转换
	 */
	public Object convert(Object source,Object typeKey)throws TypeConvertException {
		TypeConverter converter = (TypeConverter)converterMap.get(typeKey);
	  if(converter==null)
		 throw new TypeConvertException("Not found matched type converter for class["+typeKey+"]");
	  else
	   return converter.convert(source);
	}

	/**
	 * 初始化默认转换器
	 */
	private void registerDefaultConverter(){
		TypeConverter converter = new BoolConverter();
		this.addTypeConverter(Boolean.class,converter);
		this.addTypeConverter(boolean.class,converter);

		converter = new ByteConverter();
		this.addTypeConverter(Byte.class,converter);
		this.addTypeConverter(byte.class,converter);

		converter = new ShortConverter();
		this.addTypeConverter(Short.class,converter);
		this.addTypeConverter(short.class,converter);

		converter = new IntegerConverter();
		this.addTypeConverter(Integer.class,converter);
		this.addTypeConverter(int.class,converter);

		converter = new LongConverter();
		this.addTypeConverter(Long.class,converter);
		this.addTypeConverter(long.class,converter);

		converter = new FloatConverter();
		this.addTypeConverter(Float.class,converter);
		this.addTypeConverter(float.class,converter);

		converter = new DoubleConverter();
		this.addTypeConverter(Double.class,converter);
		this.addTypeConverter(double.class,converter);

		converter = new CharConverter();
		this.addTypeConverter(char.class,converter);
		this.addTypeConverter(Character.class,converter);
		this.addTypeConverter(byte[].class, new BytesConverter());

		this.addTypeConverter(Object.class,new TypeConverter());
		this.addTypeConverter(BigInteger.class,new BigIntegerConverter());
		this.addTypeConverter(BigDecimal.class,new BigDecimalConverter());
		this.addTypeConverter(String.class,new StringConverter());


		this.addTypeConverter(Date.class,new DateConverter());
		this.addTypeConverter(Time.class,new DateTimeConverter());
		this.addTypeConverter(Timestamp.class,new DateTimestampConverter());

		this.addTypeConverter(Calendar.class,new CalendarConverter());
		this.addTypeConverter(java.util.Date.class,new UtilDateConverter());

		this.addTypeConverter(Blob.class,  new BlobConverter());
		this.addTypeConverter(Clob.class, new ClobConverter());

		//放入List类型的转换器
		this.addTypeConverter(Stack.class,new StackConverter());
		this.addTypeConverter(Vector.class,new VectorConverter());
		this.addTypeConverter(LinkedList.class,new LinkedListConverter());

		converter = new ArrayListConverter();
		this.addTypeConverter(ArrayList.class,converter);
		this.addTypeConverter(AbstractList.class,converter);
		this.addTypeConverter(List.class,converter);
		this.addTypeConverter(Collection.class,converter);
		//放入List类型的转换器

		//放入Set类型的转换器
		this.addTypeConverter(HashSet.class,new HashSetConverter());
		converter = new TreeSetConverter();
		this.addTypeConverter(TreeSet.class,converter);
		this.addTypeConverter(AbstractSet.class,converter);
		this.addTypeConverter(Set.class,converter);
		//放入Set类型的转换器

		this.addTypeConverter(Iterator.class,new IteratorConverter());
		this.addTypeConverter(Enumeration.class,new EnumerationConverter());
		this.addTypeConverter(Object[].class,new ObjectArrayConverter());

		//放入Map类型的转换器
		this.addTypeConverter(Hashtable.class,new HashtableConverter());
		this.addTypeConverter(Properties.class,new PropertiesConverter());
		this.addTypeConverter(WeakHashMap.class,new WeakHashMapConverter());
		converter = new TreeMapConverter();
		this.addTypeConverter(TreeMap.class,converter);
		this.addTypeConverter(SortedMap.class,converter);

		converter = new HashMapConverter();
		this.addTypeConverter(HashMap.class,converter);
		this.addTypeConverter(Map.class,converter);
		//放入Map类型的转换器
	}
}
