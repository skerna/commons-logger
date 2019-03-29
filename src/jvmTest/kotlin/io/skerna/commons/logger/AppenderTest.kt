package io.skerna.commons.logger;

import org.apache.logging.log4j.core.Logger
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 25/03/19
 */
class AppenderTest{
    lateinit var appender:TestAppender
    lateinit var logger: Logger
    @Before
    fun setup(){
        val context = LoggerContext.getContext(false)
        logger = context.getLogger("io.skerna.commons.logger.Slf4jTest")
        println(logger.appenders)
        appender = logger.appenders.get("TestAppender") as TestAppender
    }

    @Test
    fun test(){
        val messageBase = "TEST";
        for (i in 0..100){
            val messageEvent = messageBase+"_"+i;
            logger.info(messageEvent)
            assertEquals(messageEvent,appender.getLastMessageFormat())
        }
    }

}
