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

import io.skerna.commons.sansi.cyan
import io.skerna.commons.sansi.red
import io.skerna.commons.sansi.yellow

class PrintLogDelegate constructor(name: String) : LogDelegate {
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
        println(formatMessage(message).red())
    }

    override fun fatal(message: Any, t: Throwable) {
        println(formatMessage(message, t).red())
    }

    override fun error(message: Any) {
        println(formatMessage(message).red())
    }

    override fun error(message: Any, vararg params: Any) {
        println("$message ${params.contentToString()}".red())
    }

    override fun error(message: Any, t: Throwable) {
        println(formatMessage(message, t).red())
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        println(formatMessage("$message ${params.contentToString()}", t).red())
    }

    override fun warn(message: Any) {
        println(formatMessage(message).yellow())
    }

    override fun warn(message: Any, vararg params: Any) {
        println("$message ${params.contentToString()}".yellow())

    }

    override fun warn(message: Any, t: Throwable) {
        println(formatMessage(message, t).yellow())
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        println(formatMessage("$message ${params.contentToString()}", t).yellow())
    }

    override fun info(message: Any) {
        println(formatMessage(message).cyan())
    }

    override fun info(message: Any, vararg params: Any) {
        println("$message ${params.contentToString()}".cyan())
    }

    override fun info(message: Any, t: Throwable) {
        println(formatMessage(message, t).cyan())
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        println(formatMessage("$message ${params.contentToString()}", t).cyan())
    }


    override fun debug(message: Any) {
        println(formatMessage(message).cyan())
    }

    override fun debug(message: Any, vararg params: Any) {
        println("$message ${params.contentToString()}".cyan())
    }

    override fun debug(message: Any, t: Throwable) {
        println(formatMessage(message, t).cyan())
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        println(formatMessage("$message ${params.contentToString()}", t).cyan())
    }

    override fun trace(message: Any) {
        println(formatMessage(message).cyan())
    }

    override fun trace(message: Any, vararg params: Any) {
        println("$message ${params.contentToString()}".cyan())
    }

    override fun trace(message: Any, t: Throwable) {
        println(formatMessage(message, t).cyan())
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        println(formatMessage("$message ${params.contentToString()}", t).cyan())
    }


    private fun formatMessage(message: Any): String {
        return "${this.name} : $message"
    }

    private fun formatMessage(message: Any, t: Throwable): String {
        return "${this.name} : $message, cause $t"
    }

}