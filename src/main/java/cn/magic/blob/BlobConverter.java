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
package cn.magic.blob;

import cn.magic.TypeConvertException;
import cn.magic.TypeConverter;
import cn.magic.util.BitUtil;
import cn.magic.util.CloseUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Blob
 * 
 * @author chris
 */
public class BlobConverter extends TypeConverter {
	public Object convert(Object value)throws TypeConvertException {
		if(value ==null){
			return null;
		}else if(value instanceof byte[]){
			return value;
		}else if(value instanceof String){
		  return ((String)value).getBytes();
		}else if(value instanceof Byte){
			return new byte[]{((Byte)value).byteValue()};
		}else if(value instanceof Short){
			return BitUtil.shortToByte(((Short)value).shortValue());
		}else if(value instanceof Integer){
			return BitUtil.intToByte(((Integer)value).intValue());
		}else if(value instanceof Long){
			return BitUtil.longToByte(((Long)value).longValue());
		}else if(value instanceof Float){
			return BitUtil.floatToByte(((Float)value).floatValue());
		}else if(value instanceof Double){
			return BitUtil.doubleToByte(((Double)value).doubleValue());
		}else if(value instanceof Character){
			return new byte[]{(byte)(((Character)value).charValue())};
		}else if(value instanceof BigInteger){
			return BitUtil.longToByte(((BigInteger)value).longValue());
		}else if(value instanceof BigDecimal){
			return BitUtil.doubleToByte(((BigDecimal)value).doubleValue());
		}else if(value instanceof Date){
			long longValue =((Date)value).getTime();
			return BitUtil.longToByte(longValue);
		}else if(value instanceof Calendar){
			long longValue =((Calendar)value).getTime().getTime();
			return BitUtil.longToByte(longValue);
		}else if(value instanceof Blob){
			try {
				Blob blob =(Blob)value;
				int len = (int)blob.length();
				if(len>=1)
					return blob.getBytes(1,len);
				else
					return null;
			}catch(SQLException e) {
				throw new TypeConvertException("Fail to get byte array from blob:"+e.getMessage(),e);
			}
		}else if(value instanceof Serializable){
			ByteArrayOutputStream byteStream=null;
			ObjectOutputStream objectStream=null;
			byteStream=new ByteArrayOutputStream();
			try {
				objectStream=new ObjectOutputStream(byteStream);
				objectStream.writeObject(value);
				objectStream.flush();
				return byteStream.toByteArray();
			} catch (IOException e) {
				 throw new TypeConvertException("Can't get serializable byte array: "+ e.getMessage(),e);
			}finally{
				CloseUtil.close(byteStream);
				CloseUtil.close(objectStream);
			}
		}else{
			throw new TypeConvertException("Doesn't support object conversion from type: "+ value.getClass().getName() + " to type: String");
		}
	}
}
