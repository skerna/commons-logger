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

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Benchmark {
    private val logger = LoggerFactory.logger("test")

    @Test
    @ExperimentalTime
    @JsName("benchmark1500")
    fun `benchmark test 1500 mensajes usando print delegate`(){
        LoggerContext.level().enableInfo(false,Benchmark::class)
                .ok()
        val measureTimeM1 = measureTime {
            for(cycle in 0..1500){
                logger.atInfo()
                        .log{ "action loggin" }
            }
        }
        LoggerContext.level().enableInfo(true,Benchmark::class)
                .ok()
        val measureTimeM2 = measureTime {
            for(cycle in 0..1500){
                logger.atInfo()
                        .log("action loggin" )
            }
        }
        val measureTimeM3 = measureTime {
            for(cycle in 0..1500){
                logger.atInfo { log("action $cycle") }
            }
        }
        println("TIME A $measureTimeM1" )
        println("TIME B $measureTimeM2" )
        println("TIME C $measureTimeM3" )
    }

    @ExperimentalTime
    @JsName("TestFact")
    fun `Test factories with benchmark`(){
        val factories = LoggProvider.getLoggersFactories()
        for (factory in factories) {
            //`test factory`(factory)
        }
    }

    @ExperimentalTime
    @JsName("testFactory")
    fun `test factory`(factory: LogDelegateFactory){
        LoggerContext.level().enableInfo(false,Benchmark::class)

        LoggerFactory.setLogDelegateFactory(factory)
        val logger = LoggerFactory.logger<Benchmark>()

        val measureTime = measureTime {
            for(cycle in 0..1500){
                logger.atInfo(){
                    log("action loggin" )
                }
            }
        }
        println(measureTime)
    }


}