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

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * tool to close database connection,Statement,ResultSet.
 * 
 * @author Chris.Liao
 */

public final class CloseUtil {
	public static boolean isNull(String value) {
		return (value == null || value.trim().length() == 0);
	}

	public static void oclose(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void oclose(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (Throwable e) {
		}
	}

	public static void oclose(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (Throwable e) {
		}
	}
	
	/**
	 * close InputStreamt
	 */
	public static void close(Reader reader) {
		try {
			if(reader!=null)
			 reader.close();
		} catch (Throwable e){}
	}
	
	/**
	 * close InputStreamt
	 */
	public static void close(Writer writer) {
		try {
			if(writer!=null)
			 writer.close();
		} catch (Throwable e){}
	}
	
	/**
	 * close InputStreamt
	 */
	public static void close(InputStream inputStream) {
		try {
			if(inputStream!=null)
			 inputStream.close();
		} catch (Throwable e) {
		}
	}

	/**
	 * close outputStream
	 */
	public static void close(OutputStream outputStream) {
		try {
			if(outputStream!=null)
			outputStream.close();
		} catch (Throwable e){}
	}

	public static boolean equals(String v1,String v2) {
		if(v1!=null){
			return v1.equals(v2);
		}else if(v2!=null){
			return v2.equals(v1);
		}else{
			return true;
		}
	}
}
