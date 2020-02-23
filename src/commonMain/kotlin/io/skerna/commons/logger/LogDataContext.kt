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

import io.skerna.commons.logger.LogData
import io.skerna.commons.logger.Level

class LogDataContext: LogData {

    private val loggerName:String
    private val level:Level
    private val message:String
    private val arguments:Array<Any>
    private val wasForced:Boolean
    private var exception:Throwable?=null

    constructor(loggerName: String,
                level: Level,
                message: String,
                arguments: Array<Any>,
                wasForced: Boolean,
                exception: Throwable?=null) {
        this.loggerName = loggerName
        this.level = level
        this.message = message
        this.arguments = arguments
        this.wasForced = wasForced
        this.exception = exception
    }


    override fun level(): Level {
        return this.level;
    }

    override fun loggerName(): String {
        return this.loggerName
    }

    override fun arguments(): Array<Any> {
        return this.arguments
    }

    override fun wasForced(): Boolean {
        return this.wasForced
    }

    override fun message(): String {
        return this.message
    }

    override fun exception(): Throwable? {
        return this.exception
    }
}