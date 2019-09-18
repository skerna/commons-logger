/*
 * Copyright (c)  2019  SKERNA
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
 */

package io.skerna.commons.logger


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.spi.LocationAwareLogger.*


class SLF4JLogDelegate : LogDelegate {
    private val FQCN = Logger::class.java.canonicalName
    protected val logger: Logger

    constructor(name: String) {
        logger = LoggerFactory.getLogger(name)
    }

    override val isWarnEnabled: Boolean
        get() = logger.isWarnEnabled || LoggerContext.isWarnEnabled()

    override val isInfoEnabled: Boolean
        get() = logger.isInfoEnabled || LoggerContext.isInfoEnabled()

    override val isDebugEnabled: Boolean
        get() = logger.isDebugEnabled || LoggerContext.isDebugEnabled()

    override val isTraceEnabled: Boolean
        get() = logger.isTraceEnabled || LoggerContext.isTraceEnabled()

    override fun fatal(message: Any) {
        log(ERROR_INT, message)
    }

    override fun fatal(message: Any, t: Throwable) {
        log(ERROR_INT, message, t)
    }

    override fun error(message: Any) {
        log(ERROR_INT, message)
    }

    override fun error(message: Any, vararg params: Any) {
        log(ERROR_INT, message, null, params)
    }

    override fun error(message: Any, t: Throwable) {
        log(ERROR_INT, message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        log(ERROR_INT, message, t, params)
    }

    override fun warn(message: Any) {
        log(WARN_INT, message)
    }

    override fun warn(message: Any, vararg params: Any) {
        log(WARN_INT, message, null, params)
    }

    override fun warn(message: Any, t: Throwable) {
        log(WARN_INT, message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        log(WARN_INT, message, t, params)
    }

    override fun info(message: Any) {
        log(INFO_INT, message)
    }

    override fun info(message: Any, vararg params: Any) {
        log(INFO_INT, message, null, params)
    }

    override fun info(message: Any, t: Throwable) {
        log(INFO_INT, message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        log(INFO_INT, message, t, params)
    }

    override fun debug(message: Any) {
        log(DEBUG_INT, message)
    }

    override fun debug(message: Any, vararg params: Any) {
        log(DEBUG_INT, message, null, params)
    }

    override fun debug(message: Any, t: Throwable) {
        log(DEBUG_INT, message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        log(DEBUG_INT, message, t, params)
    }

    override fun trace(message: Any) {
        log(TRACE_INT, message)
    }

    override fun trace(message: Any, vararg params: Any) {
        log(TRACE_INT, message, null, params)
    }

    override fun trace(message: Any, t: Throwable) {
        log(TRACE_INT, message, t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        log(TRACE_INT, message, t, params)
    }

    private fun log(level: Int, message: Any) {
        log(level, message, null)
    }

    private fun log(level: Int, message: Any, t: Throwable?) {
        log(level, message, t, arrayOf(""))
    }

    private fun log(level: Int, message: Any?, t: Throwable?, params:Array<out Any?>) {
        val msg = message?.toString() ?: "NULL"
        
        if (!params.isEmpty() && t != null) {
            when (level) {
                TRACE_INT -> logger.trace(msg, *params,t)
                DEBUG_INT -> logger.debug(msg, *params,t)
                INFO_INT -> logger.info(msg, *params,t)
                WARN_INT -> logger.warn(msg, *params,t)
                ERROR_INT -> logger.error(msg, *params,t)
                else -> throw IllegalArgumentException("Unknown log level $level")
            }
        } else if (params.isEmpty() && t != null) {
            when (level) {
                TRACE_INT -> logger.trace(msg, t)
                DEBUG_INT -> logger.debug(msg, t)
                INFO_INT -> logger.info(msg, t)
                WARN_INT -> logger.warn(msg, t)
                ERROR_INT -> logger.error(msg, t)
                else -> throw IllegalArgumentException("Unknown log level $level")
            }
        }else{
            when (level) {

                TRACE_INT -> logger.trace(msg,*params)
                DEBUG_INT -> logger.debug(msg,*params)
                INFO_INT -> logger.info(msg,*params)
                WARN_INT -> logger.warn(msg,*params)
                ERROR_INT -> logger.error(msg,*params)
                else -> throw IllegalArgumentException("Unknown log level $level")
            }
        }


    }
    fun String?.securedMessage(): String {
        if(isNullOrBlank()){
            return "NULL";
        }
        return this!!;
    }
    override fun unwrap(): Any? {
        return logger
    }
}
