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

import io.skerna.commons.sansi.yellow
import kotlin.reflect.KClass

expect val PLATFORM: String

/**
 * Singlenton Context Logging confguration
 */
object LoggerContext {
    private val maxMemory = 2000
    private var infoEnabled: Boolean = true
    private var debugEnabled: Boolean = true
    private var warnEnabled: Boolean = true
    private var traceEnabled: Boolean = true

    private val diagnosticBuilder: StringBuilder = StringBuilder()

    init {
        diagnosticBuilder.appendDiag("=================[ API LOG INIT  ]=================")
        diagnosticBuilder.appendDiag("Platform : $PLATFORM")
        diagnosticBuilder.appendDiag(("Klab Slog : Initializin logger Context with levels:"))
        diagnosticBuilder.appendDiag(generateMetaInfo("info", infoEnabled))
        diagnosticBuilder.appendDiag(generateMetaInfo("debug", debugEnabled))
        diagnosticBuilder.appendDiag(generateMetaInfo("warn", warnEnabled))
        diagnosticBuilder.appendDiag(generateMetaInfo("trace", traceEnabled))
        diagnosticBuilder.appendDiag("====================================================")
        diagnosticBuilder.appendDiag("=================[ API INTERACTION ]================")
    }

    fun isInfoEnabled(): Boolean = infoEnabled
    fun isDebugEnabled(): Boolean = debugEnabled
    fun isWarnEnabled(): Boolean = warnEnabled
    fun isTraceEnabled(): Boolean = traceEnabled

    fun getDiagnostic(): String = diagnosticBuilder.toString();

    fun getDiagnosticMemory(): String = "Current memory is : ${diagnosticBuilder.length}  / $maxMemory";

    fun isOverflowMemory(): Boolean = diagnosticBuilder.length >= maxMemory;

    private fun generateMetaInfo(level: String, status: Boolean): String = "Klab Slog: enable ${level.toUpperCase()} [$status]";

    private fun generateMetaInfo(level: String, status: Boolean, sourceCaller: KClass<*>): String {
        return "Klab Slog: enable ${level.toUpperCase()} [$status] from caller [${sourceCaller.simpleName}]"
    }

    private fun StringBuilder.appendDiag(content: String) {
        if (isOverflowMemory()) {
            println("${this@LoggerContext::class} WARNING diagnotic memory overflow ".yellow())
        }
        diagnosticBuilder.append(content + "\n")
    }

    public fun level():FlowConfig{
        return FlowConfig;
    }
    object FlowConfig {
        fun enableInfo(boolean: Boolean, sourceCaller: KClass<*>)  = apply {
            diagnosticBuilder.appendDiag(generateMetaInfo("info", boolean, sourceCaller))
            infoEnabled = boolean
        }

        fun enableDebug(boolean: Boolean, sourceCaller: KClass<*>) = apply {
            diagnosticBuilder.appendDiag(generateMetaInfo("debug", boolean, sourceCaller))
            debugEnabled = boolean
        }

        fun enableWarn(boolean: Boolean, sourceCaller: KClass<*>) = apply {
            diagnosticBuilder.appendDiag(generateMetaInfo("warn", boolean, sourceCaller))
            warnEnabled = boolean
        }

        fun enableTrace(boolean: Boolean, sourceCaller: KClass<*>) = apply{
            diagnosticBuilder.appendDiag(generateMetaInfo("trace", boolean, sourceCaller))
            traceEnabled = boolean
        }

        fun ok(): LoggerContext {
            return LoggerContext;
        }
    }
}