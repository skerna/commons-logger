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


import io.skerna.commons.logger.LogDelegate
import io.skerna.commons.logger.LoggerContext
import java.util.logging.Level
import java.util.logging.LogRecord

/**
 * A [LogDelegate] which delegates to java.util.logging
 */
class JULLogDelegate internal constructor(name: String) : LogDelegate {
    private val logger: java.util.logging.Logger

    override val isWarnEnabled: Boolean
        get() = logger.isLoggable(Level.WARNING) || LoggerContext.isWarnEnabled()

    override val isInfoEnabled: Boolean
        get() = logger.isLoggable(Level.INFO) || LoggerContext.isInfoEnabled()

    override val isDebugEnabled: Boolean
        get() = logger.isLoggable(Level.FINE) || LoggerContext.isDebugEnabled()

    override val isTraceEnabled: Boolean
        get() = logger.isLoggable(Level.FINEST) || LoggerContext.isTraceEnabled()

    init {
        logger = java.util.logging.Logger.getLogger(name)
    }

    override fun fatal(message: Any) {
        log(Level.SEVERE, message)
    }

    override fun fatal(message: Any, t: Throwable) {
        log(Level.SEVERE, message, t)
    }

    override fun error(message: Any) {
        log(Level.SEVERE, message)
    }

    override fun error(message: Any, vararg params: Any) {
        log(Level.SEVERE, message, null, *params)
    }

    override fun error(message: Any, t: Throwable) {
        log(Level.SEVERE, message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        log(Level.SEVERE, message, t, *params)
    }

    override fun warn(message: Any) {
        log(Level.WARNING, message)
    }

    override fun warn(message: Any, vararg params: Any) {
        log(Level.WARNING, message, null, *params)
    }

    override fun warn(message: Any, t: Throwable) {
        log(Level.WARNING, message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        log(Level.WARNING, message, t, *params)
    }

    override fun info(message: Any) {
        log(Level.INFO, message)
    }

    override fun info(message: Any, vararg params: Any) {
        log(Level.INFO, message, null, *params)
    }

    override fun info(message: Any, t: Throwable) {
        log(Level.INFO, message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        log(Level.INFO, message, t, *params)
    }

    override fun debug(message: Any) {
        log(Level.FINE, message)
    }

    override fun debug(message: Any, vararg params: Any) {
        log(Level.FINE, message, null, *params)
    }

    override fun debug(message: Any, t: Throwable) {
        log(Level.FINE, message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        log(Level.FINE, message, t, *params)
    }

    override fun trace(message: Any) {
        log(Level.FINEST, message)
    }

    override fun trace(message: Any, vararg params: Any) {
        log(Level.FINEST, message, null, *params)
    }

    override fun trace(message: Any, t: Throwable) {
        log(Level.FINEST, message, t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        log(Level.FINEST, message, t, *params)
    }

    private fun log(level: Level, message: Any?, t: Throwable? = null, vararg params: Any) {
        if (!logger.isLoggable(level)) {
            return
        }
        val msg = message?.toString() ?: "NULL"
        val record = LogRecord(level, msg)
        record.loggerName = logger.name
        if (t != null) {
            record.thrown = t
        } else if (params.size != 0 && params[params.size - 1] is Throwable) {
            // The exception may be the last parameters (SLF4J uses this convention).
            record.thrown = params[params.size - 1] as Throwable
        }
        // This will disable stack trace lookup inside JUL. If someone wants location info, they can use their own formatter
        // or use a different logging framework like sl4j, or log4j
        record.sourceClassName = null
        record.parameters = params
        logger.log(record)
    }

    override fun unwrap(): Any? {
        return logger
    }
}
