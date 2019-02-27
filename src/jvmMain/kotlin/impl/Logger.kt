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


class Logger(
        /**
         * @return the delegate instance sending operations to the underlying logging framework
         */
        val delegate: LogDelegate) {

    val isWarnEnabled: Boolean
        get() = delegate.isWarnEnabled

    val isInfoEnabled: Boolean
        get() = delegate.isInfoEnabled

    val isDebugEnabled: Boolean
        get() = delegate.isDebugEnabled

    val isTraceEnabled: Boolean
        get() = delegate.isTraceEnabled

    fun fatal(message: Any) {
        delegate.fatal(message)
    }

    fun fatal(message: Any, t: Throwable) {
        delegate.fatal(message, t)
    }

    fun error(message: Any) {
        delegate.error(message)
    }

    fun error(message: Any, t: Throwable) {
        delegate.error(message, t)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun error(message: Any, vararg objects: Any) {
        delegate.error(message, *objects)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun error(message: Any, t: Throwable, vararg objects: Any) {
        delegate.error(message, t, *objects)
    }

    fun warn(message: Any) {
        delegate.warn(message)
    }

    fun warn(message: Any, t: Throwable) {
        delegate.warn(message, t)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun warn(message: Any, vararg objects: Any) {
        delegate.warn(message, *objects)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun warn(message: Any, t: Throwable, vararg objects: Any) {
        delegate.warn(message, t, *objects)
    }

    fun info(message: Any) {
        delegate.info(message)
    }

    fun info(message: Any, t: Throwable) {
        delegate.info(message, t)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun info(message: Any, vararg objects: Any) {
        delegate.info(message, *objects)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun info(message: Any, t: Throwable, vararg objects: Any) {
        delegate.info(message, t, *objects)
    }

    fun debug(message: Any) {
        delegate.debug(message)
    }

    fun debug(message: Any, t: Throwable) {
        delegate.debug(message, t)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun debug(message: Any, vararg objects: Any) {
        delegate.debug(message, *objects)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun debug(message: Any, t: Throwable, vararg objects: Any) {
        delegate.debug(message, t, *objects)
    }

    fun trace(message: Any) {
        delegate.trace(message)
    }

    fun trace(message: Any, t: Throwable) {
        delegate.trace(message, t)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun trace(message: Any, vararg objects: Any) {
        delegate.trace(message, *objects)
    }

    /**
     * @throws UnsupportedOperationException if the logging backend does not support parameterized messages
     */
    fun trace(message: Any, t: Throwable, vararg objects: Any) {
        delegate.trace(message, t, *objects)
    }
}
