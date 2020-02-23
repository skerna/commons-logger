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

abstract class AbstractLoggerDelegate(val name: String,
                                      val loggerConfiguration: LoggerConfiguration
) : LogDelegate, LogLevels.ALL {
    private var exceptionHandler: ExceptionHandler? = null


    override fun log(level: Level, message: String) {
        when (level) {
            Level.INFO -> info(message)
            Level.DEBUG -> debug(message)
            Level.ERROR -> error(message)
            Level.WARNING -> warn(message)
            Level.TRACE -> trace(message)
            Level.SEVERE -> fatal(message)
            else -> {
                throw IllegalStateException("Unknow level required")
            }
        }
    }

    override fun log(level: Level, message: String, exception: Throwable) {
        when (level) {
            Level.INFO -> info(message, exception)
            Level.DEBUG -> debug(message, exception)
            Level.ERROR -> error(message, exception)
            Level.WARNING -> warn(message, exception)
            Level.TRACE -> trace(message, exception)
            Level.SEVERE -> fatal(message, exception)
            else -> {
                throw IllegalStateException("Unknow level required")
            }
        }
    }

    override fun log(level: Level, message: String, vararg params: Any) {
        when (level) {
            Level.INFO -> info(message, *params)
            Level.DEBUG -> debug(message, *params)
            Level.ERROR -> error(message, *params)
            Level.WARNING -> warn(message, *params)
            Level.TRACE -> trace(message, *params)
            Level.SEVERE -> fatal(message)
            else -> {
                throw IllegalStateException("Unknow level required")
            }
        }
    }

    override fun log(level: Level, exception: Throwable, message: String, vararg params: Array<Any>) {
        when (level) {
            Level.INFO -> info(message, exception, *params)
            Level.DEBUG -> debug(message, exception, *params)
            Level.ERROR -> error(message, exception, *params)
            Level.WARNING -> warn(message, exception, *params)
            Level.TRACE -> trace(message, exception, *params)
            Level.SEVERE -> fatal(message, exception)
            else -> {
                throw IllegalStateException("Unknow level required")
            }
        }
    }

    override fun setExceptionHandler(exceptionHandler: ExceptionHandler) {
        this.exceptionHandler = exceptionHandler
    }

    override fun onException(exception: Exception) {
        exceptionHandler?.onException(exception)
    }

    override fun getLoggerName(): String {
        return this.name
    }

    override fun isLoggable(level: Level): Boolean {
        if (level == Level.INFO && isInfoEnabled) {
            return true
        } else if (level == Level.WARNING && isWarnEnabled) {
            return true
        } else if (level == Level.DEBUG && isDebugEnabled) {
            return true
        } else if (level == Level.TRACE && isTraceEnabled) {
            return true
        } else if (level == Level.ERROR) {
            return true;
        } else if (level == Level.SEVERE) {
            return true;
        } else if (level == Level.FINER) {
            return true;
        } else if (level == Level.FINEST) {
            return true;
        }
        return false
    }
}