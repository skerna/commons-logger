package io.skerna.commons.logger

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Benchmark {
    private val logger = LoggerFactory.logger("test")

    @ExperimentalTime
    @Test
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
                println("ACTION $cycle" )
            }
        }
        println("TIME A $measureTimeM1" )
        println("TIME B $measureTimeM2" )
        println("TIME C $measureTimeM3" )
    }

    @ExperimentalTime
    @Test
    fun `Test factories with benchmark`(){
        val factories = LoggProvider.getLoggersFactories()
        for (factory in factories) {
            `test factory`(factory)
        }
    }


    @ExperimentalTime
    fun `test factory`(factory: LogDelegateFactory){
        LoggerContext.level().enableInfo(false,Benchmark::class)

        LoggerFactory.setLogDelegateFactory(factory)
        val logger = LoggerFactory.logger<Benchmark>()

        val measureTime = measureTime {
            for(cycle in 0..1500){
                logger.atInfo()
                        .log("action loggin" )
            }
        }
        println(measureTime)
    }
}