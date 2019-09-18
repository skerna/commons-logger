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
        LoggerFactory.setLogDelegateFactory(Log4j2LogDelegateFactory())
        val log4j2 = Log4j2LogDelegate("name")
        val fluentLogger = LoggerFactory.logger("name")
        val measuredLog4j2 = measureTime {
            for(cycle in 0..15000){
                log4j2.info("logdata $cycle ${factorial(cycle)}" )
            }
        }
        LoggerContext.level().enableInfo(false,BenchmarkJVM::class)
        val measuredFluentLogger = measureTime {
            for(cycle in 0..15000){
                fluentLogger.atInfo()
                        .log { "logdata $cycle ${factorial(cycle)}" }
            }
        }

        val debugStatus = false
        val warnStatus = true
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

        assertEquals(true,measuredLog4j2 > measuredFluentLogger,"Expected log4j2 slow render string")
        assertEquals(true, measureWarn >  measuredFluentLogger, "Expected warn enabled [measureWarn] must be bigger than [measuredFluentLogger] ")

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