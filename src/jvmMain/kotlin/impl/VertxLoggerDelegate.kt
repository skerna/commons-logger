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
import io.vertx.core.logging.LoggerFactory
import io.vertx.core.logging.Logger as VLogger

class VertxLoggerDelegate : LogDelegate {

    private val logger: VLogger

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
        this.logger = logger as VLogger

    }

    override fun fatal(message: Any) {
        logger.fatal(message)
    }

    override fun fatal(message: Any, t: Throwable) {
        logger.fatal(message, t)
    }

    override fun error(message: Any) {
        logger.error(message)
    }

    override fun error(message: Any, vararg params: Any) {
        logger.error(message, params)
    }

    override fun error(message: Any, t: Throwable) {
        logger.error(message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        logger.error(message, t, params)
    }

    override fun warn(message: Any) {
        logger.warn(message)
    }

    override fun warn(message: Any, vararg params: Any) {
        logger.warn(message, params)
    }

    override fun warn(message: Any, t: Throwable) {
        logger.warn(message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        logger.warn(message, t, params)
    }

    override fun info(message: Any) {
        logger.info(message)
    }

    override fun info(message: Any, vararg params: Any) {
        logger.info(message, params)
    }

    override fun info(message: Any, t: Throwable) {
        logger.info(message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        logger.info(message, t, params)
    }

    override fun debug(message: Any) {
        logger.debug(message)
    }

    override fun debug(message: Any, vararg params: Any) {
        logger.debug(message, params)
    }

    override fun debug(message: Any, t: Throwable) {
        logger.debug(message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        logger.debug(message, t, params)
    }

    override fun trace(message: Any) {
        logger.trace(message)
    }

    override fun trace(message: Any, vararg params: Any) {
        logger.trace(message, params)

    }

    override fun trace(message: Any, t: Throwable) {
        logger.trace(message, t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        logger.trace(message, t, params)
    }

    override fun unwrap(): Any? {
        return logger
    }

}
