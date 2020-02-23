/*
 * Copyright (c)  2020  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.skerna.commons.logger

interface LogDelegate:LogLevels.Debug,
        LogLevels.Info,
        LogLevels.Warning,
        LogLevels.Error,
        LogLevels.Fatal,
        LogLevels.Trace{

    val isWarnEnabled: Boolean

    val isInfoEnabled: Boolean

    val isDebugEnabled: Boolean

    val isTraceEnabled: Boolean

    fun log(level:Level, message:String)

    fun log(level:Level,message: String, exception: Throwable)

    fun log(level:Level,message: String, vararg params:Any)

    fun log(level:Level,exception: Throwable, message: String, vararg params: Array<Any>)

    fun getLoggerName():String

    fun isLoggable(level: Level):Boolean
    /**
     * @return the underlying framework logger object, null in the default implementation
     */
    fun unwrap(): Any

    fun setExceptionHandler(exceptionHandler: ExceptionHandler)

    fun onException(exception: Exception)

}
