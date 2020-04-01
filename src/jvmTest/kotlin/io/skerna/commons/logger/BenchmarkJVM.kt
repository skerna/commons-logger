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

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class BenchmarkJVM {

    @ExperimentalTime
    @Test
    fun `expected fluent api less time`(){
        val debugStatus = false
        val warnStatus = true
        LoggerFactory.setLogDelegateFactory(Log4j2LogDelegateFactory())
        val log4j2 = VertxLoggerDelegate("name", LoggerConfiguration.instanceGlobalContext)
        val fluentLogger = LoggerFactory.logger("name")
        val measuredLog4j2 = measureTime {
            for(cycle in 0..15000){
                log4j2.info("logdata $cycle ${factorial(cycle)}" )
            }
        }
        LoggerContext.level().enableInfo(false,BenchmarkJVM::class)
        val measuredFluentLogger = measureTime {
            for(cycle in 0..15000){
                fluentLogger.atInfo {
                 log("logdata $cycle ${factorial(cycle)}")
                }
            }
        }


        LoggerContext.level().enableDebug(debugStatus,BenchmarkJVM::class)

        val measureFluentDebuggDisabled = measureTime {
            for(cycle in 0..15000){
                fluentLogger.atDebug()
                        .log { "logdata $cycle ${factorial(cycle)}" }
            }
        }

        LoggerContext.level().enableDebug(warnStatus,BenchmarkJVM::class)
        val measureWarn = measureTime {
            for(cycle in 0..15000){
                fluentLogger.atDebug()
                        .log { "logdata $cycle ${factorial(cycle)}" }
            }
        }
        println("TIME LOG4j2 $measuredLog4j2" )
        println("TIME FluentLogger $measuredFluentLogger" )
        println("TIME Debug $debugStatus benchmark $measureFluentDebuggDisabled" )
        println("TIME Warn $debugStatus benchmark $measureWarn" )

        //assertEquals(true,measuredLog4j2 > measuredFluentLogger,"Expected log4j2 slow render string")
        //assertEquals(true, measureWarn >  measuredFluentLogger, "Expected warn enabled [measureWarn] must be bigger than [measuredFluentLogger] ")

    }

    @Test
    fun test(){
        var fact = factorial(7)
        assertEquals(fact,5040)
    }

    fun factorial(number: Int): Int {
        var result = 1;
        for(iter in number downTo 1){
            result =  result * iter
        }
        return  result;
    }
}