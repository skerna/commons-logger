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
import io.skerna.slog.LoggerContext

class ConsoleLogDelegate constructor(name: String) : LogDelegate {
    private val name = name

    override val isWarnEnabled: Boolean
        get() = LoggerContext.isWarnEnabled()
    override val isInfoEnabled: Boolean
        get() = LoggerContext.isInfoEnabled()
    override val isDebugEnabled: Boolean
        get() = LoggerContext.isDebugEnabled()
    override val isTraceEnabled: Boolean
        get() = LoggerContext.isTraceEnabled()

    override fun fatal(message: Any) {
        console.error(formatMessage(message))
    }

    override fun fatal(message: Any, t: Throwable) {
        console.error(formatMessage(message), t)
    }

    override fun error(message: Any) {
        console.error(formatMessage(message))
    }

    override fun error(message: Any, vararg params: Any) {
        console.error(formatMessage(message), params)
    }

    override fun error(message: Any, t: Throwable) {
        console.error(formatMessage(message), t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        console.error(formatMessage(message), t, params)
    }

    override fun warn(message: Any) {
        console.warn(formatMessage(message))
    }

    override fun warn(message: Any, vararg params: Any) {
        console.warn(formatMessage(message), params)
    }

    override fun warn(message: Any, t: Throwable) {
        console.warn(formatMessage(message), t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        console.warn(formatMessage(message), t, params)
    }

    override fun info(message: Any) {
        console.info(formatMessage(message))
    }

    override fun info(message: Any, vararg params: Any) {
        console.info(formatMessage(message), params)
    }

    override fun info(message: Any, t: Throwable) {
        console.info(formatMessage(message), t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        console.info(formatMessage(message), t, params)
    }

    override fun debug(message: Any) {
        console.log(formatMessage(message))
    }

    override fun debug(message: Any, vararg params: Any) {
        console.log(formatMessage(message), params)
    }

    override fun debug(message: Any, t: Throwable) {
        console.log(formatMessage(message), t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        console.log(formatMessage(message), t, message)
    }

    override fun trace(message: Any) {
        console.log(formatMessage(message))
    }

    override fun trace(message: Any, vararg params: Any) {
        console.log(formatMessage(message), params)
    }

    override fun trace(message: Any, t: Throwable) {
        console.log(formatMessage(message, t), t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        console.log(formatMessage(message, t), t, params)
    }

    private fun formatMessage(message: Any): String {
        return "${this.name} : $message"
    }

    private fun formatMessage(message: Any, t: Throwable): String {
        return "${this.name} : $message, cause $t"
    }

}