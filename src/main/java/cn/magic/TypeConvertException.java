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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 转换异常
 * 
 * @author chris
 */
public class TypeConvertException extends Exception {
	private Throwable cause;
	public TypeConvertException(String message){
		this(message,null);
	}
	public TypeConvertException(Throwable cause) {
		this(null,cause);
	}
	public TypeConvertException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}
	public Throwable getCauseException() {
		return cause;
	}
	public void printStackTrace() {
		this.printStackTrace(System.err);
	}
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		if(cause!=null){
			s.print("Caused by: ");
			cause.printStackTrace(s);
		}
	}
	public void printStackTrace(PrintWriter w) {
		super.printStackTrace(w);
		if(cause!=null){
			w.print("Caused by: ");
			cause.printStackTrace(w);
		}
	}
}
