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

import android.util.Log
import com.jraska.console.Console
import timber.log.Timber
import java.lang.IllegalStateException

/**
 * @author Ronald Cárdenas
 **/
class ALogDelegte(
        name: String,
        configuration: LoggerConfiguration,
        override val isDebugEnabled: Boolean = true,
        override val isInfoEnabled: Boolean= true,
        override val isTraceEnabled: Boolean= true,
        override val isWarnEnabled: Boolean= true
) :AbstractLoggerDelegate(name,configuration) {

    override fun debug(message: Any) {
        Timber.d(name,message.toString())
    }

    override fun debug(message: Any, vararg params: Any) {
        writeInConsole(DEBUG,message,null,params)
    }

    override fun debug(message: Any, t: Throwable) {
        writeInConsole(DEBUG,message,t,null)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(DEBUG,message,t,params)
    }

    override fun error(message: Any) {
        writeInConsole(ERROR,message,null,null)
    }

    override fun error(message: Any, vararg params: Any) {
        writeInConsole(ERROR,message,null,params)
    }

    override fun error(message: Any, t: Throwable) {
        writeInConsole(ERROR,message,t,null)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(ERROR,message,t,params)
    }

    override fun fatal(message: Any) {
        writeInConsole(ERROR,message,null,null)
    }

    override fun fatal(message: Any, t: Throwable) {
        writeInConsole(ERROR,message,t,null)
    }

    override fun info(message: Any) {
        writeInConsole(INFO,message,null,null)
    }

    override fun info(message: Any, vararg params: Any) {
        writeInConsole(INFO,message,null,params)
    }

    override fun info(message: Any, t: Throwable) {
        writeInConsole(INFO,message,t,null)

    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(INFO,message,t,params)
    }

    override fun trace(message: Any) {
    }

    override fun trace(message: Any, vararg params: Any) {
    }

    override fun trace(message: Any, t: Throwable) {
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
    }

    override fun warn(message: Any) {
        writeInConsole(INFO,message,null,null)
    }

    override fun warn(message: Any, vararg params: Any) {
        writeInConsole(INFO,message,null,params)
    }

    override fun warn(message: Any, t: Throwable) {
        writeInConsole(INFO,message,t,null)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(INFO,message,t,params)
    }


    fun writeInConsole(level:String,message: Any, t: Throwable?, vararg params: Any?) {
        /**if(t!=null){
        if(level.equals(DEBUG)){
        Timber.d(t, message.toString(), params)
        }else  if(level.equals(ERROR)){
        Timber.e(t, message.toString(), params)
        }else if(level.equals(INFO)){
        Timber.i(t, message.toString(), params)
        }else if(level.equals(WARNING)){
        Timber.w(t, message.toString(), params)
        }
        return
        }
        if(level.equals(DEBUG)){
        Timber.d(message.toString(), params)
        }else  if(level.equals(ERROR)){
        Timber.e(message.toString(), params)
        }else if(level.equals(INFO)){
        Timber.i(message.toString(), params)
        }else if(level.equals(WARNING)){
        Timber.w(message.toString(), params)
        }**/
        Console.writeLine("$message  ,${t?.message},${params.joinToString { "," }}")
        println("$level :$message  ,${t?.message},${params.joinToString { "," }}")
    }
    companion object Level {
        val DEBUG = "D"
        val ERROR="E"
        val INFO="I"
        val WARNING="W"
    }

    override fun unwrap(): Any {
        throw IllegalStateException("Thid method not return")
    }
}