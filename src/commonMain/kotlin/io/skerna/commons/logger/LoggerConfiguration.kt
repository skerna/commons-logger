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

import kotlin.jvm.JvmStatic

class LoggerConfiguration {
    public var level:Level ; private  set
    public var infoEnabled: Boolean = true; private  set
    public var debugEnabled: Boolean = false ;private  set
    public var warnEnabled: Boolean = false; private  set
    public var traceEnabled: Boolean = false;private  set
    private var exceptionHandler:ExceptionHandler?=null


    constructor(level: Level, infoEnabled: Boolean=true, debugEnabled: Boolean=false, warnEnabled: Boolean=false, traceEnabled: Boolean=false) {
        this.level = level
        this.infoEnabled = infoEnabled
        this.debugEnabled = debugEnabled
        this.warnEnabled = warnEnabled
        this.traceEnabled = traceEnabled
    }

    fun setLevel(level: Level) = apply {
        this.level = level
    }

    fun enableDebug(debugEnabled: Boolean) = apply {
        this.debugEnabled = debugEnabled
    }

    fun enableInfo(infoEnabled: Boolean) = apply {
        this.infoEnabled = infoEnabled
    }

    fun enableWarn(warnEnabled: Boolean) = apply {
        this.warnEnabled = warnEnabled
    }

    fun enableTrace(traceEnabled: Boolean) = apply {
        this.traceEnabled = traceEnabled
    }
    fun enableAll() = apply {
        this.infoEnabled = true
        this.warnEnabled = true
        this.debugEnabled = true
        this.traceEnabled =true
    }

    fun isEnabledLevel(level: Level) : Boolean {
        var enabledByWeight = this.level.peso < level.peso
        var enabledByLevel = false;
//        when(level){
//            Level.INFO ->  enabledByLevel = true
//            Level.DEBUG -> enabledByLevel = true
//            Level.WARNING -> enabledByLevel = true
//            Level.ERROR -> enabledByLevel = true
//            else -> enabledByLevel = false
//        }
        var enabled = enabledByWeight
        return enabled
    }

    public companion object{
        val instanceGlobalContext = LoggerConfiguration(level = Level.INFO)
    }
}