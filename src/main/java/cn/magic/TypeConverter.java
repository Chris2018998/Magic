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

/**
 * 类型转换器
 * 
 * @author Chris
 */

public class TypeConverter {

	/**
	 *  将对象转换为目标类型，如将结果转换为当前类型
	 *
	 * @param source 需要转换的类型
	 * @return 返回目标类型值
	 * @throws TypeConvertException 转换失败或不支持转换
	 */
	public Object convert(Object source)throws TypeConvertException {
		return source;
	}
}
