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

import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class TestLevels {
    val logger:Logger
    init{
        LoggerConfiguration.instanceGlobalContext.enableAll()
        logger = LoggerFactory.logger<TestLevels>();
    }

    private fun getLoggers(name:String):List<Logger>{
        return LoggProvider.getLoggersFactories().map {
            val delegate  = it.createDelegate(name, LoggerConfiguration.instanceGlobalContext)
            val logger = Logger(delegate)
            logger
        }
    }
    @ExperimentalTime
    @Test
    fun testInfo(){
        getLoggers("test").forEach { logger ->
            val time = measureTime { logger.atInfo().log("Info Test {}","ronald") }
            println("Time -> " + time)
        }
    }
    @ExperimentalTime
    @Test
    fun testDebug(){
        getLoggers("test").forEach { logger ->
            val time = measureTime { logger.atDebug().log("Debug Test {}","ronald") }
            println("Time -> " + time)
        }
    }
    @ExperimentalTime
    @Test
    fun testWarn(){
        getLoggers("test").forEach { logger ->
            val time = measureTime { logger.atWarning().log("Warn Test {}","ronald") }
            println("Time -> " + time)
        }
    }
    @ExperimentalTime
    @Test
    fun testTrace(){
        getLoggers("test").forEach { logger ->
            val time = measureTime { logger.atError().log("Trace Test {}","ronald") }
            println("Time -> " + time)
        }
    }
    @ExperimentalTime
    @Test
    fun testError() {
        getLoggers("test").forEach { logger ->
            val time = measureTime { logger.atError { log("test") } }
            println("Time -> " + time)
        }
    }
}