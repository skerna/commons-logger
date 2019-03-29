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


import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory
import org.apache.logging.log4j.message.FormattedMessage
import org.apache.logging.log4j.message.Message
import org.apache.logging.log4j.spi.ExtendedLogger
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper


/**
 * A [LogDelegate] which delegates to Apache Log4j 2
 *
 */
class Log4j2LogDelegate internal constructor(name: String) : LogDelegate {

    internal val logger:ExtendedLoggerWrapper

    override val isWarnEnabled: Boolean
        get() = logger.isWarnEnabled || LoggerContext.isWarnEnabled()

    override val isInfoEnabled: Boolean
        get() = logger.isInfoEnabled || LoggerContext.isInfoEnabled()

    override val isDebugEnabled: Boolean
        get() = logger.isDebugEnabled || LoggerContext.isDebugEnabled()

    override val isTraceEnabled: Boolean
        get() = logger.isTraceEnabled || LoggerContext.isTraceEnabled()

    init {
        configurateDefaultLogger()
        val _logger = LogManager.getLogger(name) as ExtendedLogger
        logger = ExtendedLoggerWrapper(_logger,name,_logger.getMessageFactory())
    }

    private fun configurateDefaultLogger() {

    }

    override fun fatal(message: Any) {
        log(Level.FATAL, message)
    }

    override fun fatal(message: Any, t: Throwable) {
        log(Level.FATAL, message, t)
    }

    override fun error(message: Any) {
        log(Level.ERROR, message)
    }

    override fun error(message: Any, vararg params: Any) {
        log(Level.ERROR, message.toString(), *params)
    }

    override fun error(message: Any, t: Throwable) {
        log(Level.ERROR, message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        log(Level.ERROR, message.toString(), t, *params)
    }

    override fun warn(message: Any) {
        log(Level.WARN, message)
    }

    override fun warn(message: Any, vararg params: Any) {
        log(Level.WARN, message.toString(), *params)
    }

    override fun warn(message: Any, t: Throwable) {
        log(Level.WARN, message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        log(Level.WARN, message.toString(), t, *params)
    }

    override fun info(message: Any) {
        logger.info(message)
    }

    override fun info(message: Any, vararg params: Any) {
        log(Level.INFO, message.toString(), *params)
    }

    override fun info(message: Any, t: Throwable) {
        log(Level.INFO, message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        log(Level.INFO, message.toString(), t, *params)
    }

    override fun debug(message: Any) {
        log(Level.DEBUG, message)
    }

    override fun debug(message: Any, vararg params: Any) {
        log(Level.DEBUG, message.toString(), *params)
    }

    override fun debug(message: Any, t: Throwable) {
        log(Level.DEBUG, message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        log(Level.DEBUG, message.toString(), t, *params)
    }

    override fun trace(message: Any) {
        log(Level.TRACE, message)
    }

    override fun trace(message: Any, vararg params: Any) {
        log(Level.TRACE, message.toString(), *params)
    }

    override fun trace(message: Any, t: Throwable) {
        log(Level.TRACE, message.toString(), t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        log(Level.TRACE, message.toString(), t, *params)
    }

    private fun log(level: Level, message: Any, t: Throwable? = null) {
        if (message is Message) {
            logger.logIfEnabled(FQCN, level, null, message, t)
        } else {
            logger.logIfEnabled(FQCN, level, null, message, t)
        }
    }

    private fun log(level: Level, message: String, vararg params: Any) {
        logger.logIfEnabled(FQCN, level,null, message, *params)
    }

    private fun log(level: Level, message: String, t: Throwable, vararg params: Any) {
        logger.logIfEnabled(FQCN, level, null,FormattedMessage(message, *params), t)
    }

    override fun unwrap(): Any? {
        return logger
    }

    companion object {
        internal val FQCN = Logger::class.java.canonicalName
    }


}
