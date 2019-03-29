package io.skerna.commons.logger

import io.skerna.commons.logger.LoggerFactory.logger
import org.apache.logging.log4j.core.LoggerContext
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author Ronald Cárdenas
 * project: skerna-reactorLogger created at 26/03/19
 **/

class Log4j2LogDelegateTest{
    private lateinit var appender: TestAppender
    private lateinit var reactorLogger:Log4j2LogDelegate

    @Before
    fun configure(){
        val context = LoggerContext.getContext(false)
        val logger = context.getLogger("io.skerna.commons.logger.Slf4jTest")
        appender = logger.appenders.get("TestAppender") as TestAppender
        reactorLogger = Log4j2LogDelegate("io.skerna.commons.logger.Slf4jTest")
    }


    @Test
    fun withArguments(){
        reactorLogger.info("Test {}","1")
        assertEquals("Test 1", appender.getLastMessageFormatted())

        reactorLogger.info("Test {}","2")
        assertEquals("Test 2", appender.getLastMessageFormatted())

        reactorLogger.info("Test {}","3")
        assertEquals("Test 3", appender.getLastMessageFormatted())
    }

    @Test
    fun testError(){
        reactorLogger.error("Error",IllegalStateException("aaa"))
        assertTrue(appender.getLastMessage().message.throwable.javaClass.equals(IllegalStateException::class.java))


        reactorLogger.error("Error",IllegalStateException("bbb"))
        assertTrue(appender.getLastMessage().message.throwable.javaClass.equals(IllegalStateException::class.java))


        reactorLogger.error("Error",IllegalStateException("ccc"))
        assertTrue(appender.getLastMessage().message.throwable.message.equals("ccc"))
    }
}