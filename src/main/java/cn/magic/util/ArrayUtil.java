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
package cn.magic.util;

import java.lang.reflect.Array;

/**
 * A Util class for Array
 *
 * @author Chris Liao
 * @version 1.0
 */

public class ArrayUtil {
  public static boolean isArray(Class clazz) {
    return clazz.isArray();
  }
  public static boolean isArray(Object object) {
    return object.getClass().isArray();
  }
  public static Class getArrayType(Class array) {
    return array.getComponentType();
  }
  public static Class getArrayType(Object array) {
    return array.getClass().getComponentType();
  }
  public static int getArraySize(Object array) {
    return Array.getLength(array);
  }
  public static Object createArray(Class type, int size) {
    return Array.newInstance(type, size);
  }
  public static Object getObject(Object arry, int index) {
    return Array.get(arry, index);
  }
  public static void setObject(Object arry,int index,Object value) {
    Array.set(arry, index, value);
  }
}