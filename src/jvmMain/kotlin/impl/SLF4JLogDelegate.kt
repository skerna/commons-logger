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

package io.skerna.slog.impl


import io.skerna.slog.LogDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.helpers.MessageFormatter
import org.slf4j.spi.LocationAwareLogger
import org.slf4j.spi.LocationAwareLogger.*


class SLF4JLogDelegate : LogDelegate {

    private val logger: Logger

    override val isWarnEnabled: Boolean
        get() = logger.isWarnEnabled

    override val isInfoEnabled: Boolean
        get() = logger.isInfoEnabled

    override val isDebugEnabled: Boolean
        get() = logger.isDebugEnabled

    override val isTraceEnabled: Boolean
        get() = logger.isTraceEnabled

    internal constructor(name: String) {
        logger = LoggerFactory.getLogger(name)
    }

    constructor(logger: Any) {
        this.logger = logger as Logger

    }

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
        log(ERROR_INT, message, null, *params)
    }

    override fun error(message: Any, t: Throwable) {
        log(ERROR_INT, message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        log(ERROR_INT, message, t, *params)
    }

    override fun warn(message: Any) {
        log(WARN_INT, message)
    }

    override fun warn(message: Any, vararg params: Any) {
        log(WARN_INT, message, null, *params)
    }

    override fun warn(message: Any, t: Throwable) {
        log(WARN_INT, message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        log(WARN_INT, message, t, *params)
    }

    override fun info(message: Any) {
        log(INFO_INT, message)
    }

    override fun info(message: Any, vararg params: Any) {
        log(INFO_INT, message, null, *params)
    }

    override fun info(message: Any, t: Throwable) {
        log(INFO_INT, message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        log(INFO_INT, message, t, *params)
    }

    override fun debug(message: Any) {
        log(DEBUG_INT, message)
    }

    override fun debug(message: Any, vararg params: Any) {
        log(DEBUG_INT, message, null, *params)
    }

    override fun debug(message: Any, t: Throwable) {
        log(DEBUG_INT, message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        log(DEBUG_INT, message, t, *params)
    }

    override fun trace(message: Any) {
        log(TRACE_INT, message)
    }

    override fun trace(message: Any, vararg params: Any) {
        log(TRACE_INT, message, null, *params)
    }

    override fun trace(message: Any, t: Throwable) {
        log(TRACE_INT, message, t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        log(TRACE_INT, message, t, *params)
    }


    private fun log(level: Int, message: Any? = null, t: Throwable? = null, vararg params: Any) {
        val msg = message?.toString() ?: "NULL"


        // We need to compute the right parameters.
        // If we have both parameters and an error, we need to build a new array [params, t]
        // If we don't have parameters, we need to build a new array [t]
        // If we don't have error, it's just params.
        var parameters = params

        if (params.isNotEmpty() && t != null) {

            parameters = arrayOf<Any>(params.size + 1)
            System.arraycopy(params, 0, parameters, 0, params.size)
            parameters[params.size] = t

        } else if (params.isEmpty() && t != null) {

            parameters = arrayOf<Any>(t)

        }

        if (logger is LocationAwareLogger) {
            // make sure we don't format the objects if we don't log the line anyway
            if (level == TRACE_INT && logger.isTraceEnabled() ||
                    level == DEBUG_INT && logger.isDebugEnabled() ||
                    level == INFO_INT && logger.isInfoEnabled() ||
                    level == WARN_INT && logger.isWarnEnabled() ||
                    level == ERROR_INT && logger.isErrorEnabled()) {
                val ft = MessageFormatter.arrayFormat(msg, parameters)
                logger.log(null, FQCN, level, ft.message, null, ft.throwable)
            }
        } else {
            when (level) {
                TRACE_INT -> logger.trace(msg, parameters)
                DEBUG_INT -> logger.debug(msg, parameters)
                INFO_INT -> logger.info(msg, parameters)
                WARN_INT -> logger.warn(msg, parameters)
                ERROR_INT -> logger.error(msg, parameters)
                else -> {
                    throw IllegalStateException("Unknown log level $level")
                }
            }
        }
    }

    override fun unwrap(): Any? {
        return logger
    }

    companion object {
        private val FQCN = Logger::class.java.canonicalName
    }
}
