
package io.skerna.commons.logger

import java.util.logging.*
import java.util.logging.Level
import java.util.logging.Logger

class JULLogDelegate internal constructor(name: String,
                                          configuration: LoggerConfiguration) : AbstractLoggerDelegate(name,configuration) {
    private val logger: Logger

    override val isWarnEnabled: Boolean
        get() = logger.isLoggable(Level.WARNING) || LoggerContext.isWarnEnabled()

    override val isInfoEnabled: Boolean
        get() = logger.isLoggable(Level.INFO)  || LoggerContext.isInfoEnabled()

    override val isDebugEnabled: Boolean
        get() = (logger.isLoggable(Level.FINE)  || LoggerContext.isDebugEnabled())

    override val isTraceEnabled: Boolean
        get() = logger.isLoggable(Level.FINEST)  || LoggerContext.isTraceEnabled()

    override fun fatal(message: Any) {
        log(Level.SEVERE, message)
    }

    override fun fatal(message: Any, t: Throwable) {
        log(Level.SEVERE, message, t)
    }

    override fun error(message: Any) {
        log(Level.SEVERE, message)
    }

    override fun error(message: Any, vararg params: Any) {
        log(Level.SEVERE, message, null, *params)
    }

    override fun error(message: Any, t: Throwable) {
        log(Level.SEVERE, message, t)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        log(Level.SEVERE, message, t, *params)
    }

    override fun warn(message: Any) {
        log(Level.WARNING, message)
    }

    override fun warn(message: Any, vararg params: Any) {
        log(Level.WARNING, message, null, *params)
    }

    override fun warn(message: Any, t: Throwable) {
        log(Level.WARNING, message, t)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        log(Level.WARNING, message, t, *params)
    }

    override fun info(message: Any) {
        log(Level.INFO, message)
    }

    override fun info(message: Any, vararg params: Any) {
        log(Level.INFO, message, null, *params)
    }

    override fun info(message: Any, t: Throwable) {
        log(Level.INFO, message, t)
    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        log(Level.INFO, message, t, *params)
    }

    override fun debug(message: Any) {
        log(Level.FINE, message)
    }

    override fun debug(message: Any, vararg params: Any) {
        log(Level.FINE, message, null, *params)
    }

    override fun debug(message: Any, t: Throwable) {
        log(Level.FINE, message, t)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        log(Level.FINE, message, t, *params)
    }

    override fun trace(message: Any) {
        log(Level.FINEST, message)
    }

    override fun trace(message: Any, vararg params: Any) {
        log(Level.FINEST, message, null, *params)
    }

    override fun trace(message: Any, t: Throwable) {
        log(Level.FINEST, message, t)
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
        log(Level.FINEST, message, t, *params)
    }

    private fun log(level: Level, message: Any, t: Throwable? = null, vararg params: Any): kotlin.Unit
    {
        if (!logger.isLoggable(level)) {
            return
        }
        val msg = message?.toString() ?: "NULL"
        val record = LogRecord(level, msg)
        record.loggerName = logger.name
        if (t != null) {
            record.thrown = t
        } else if (params.isNotEmpty() && params[params.size - 1] is Throwable) {
            // The exception may be the last parameters (SLF4J uses this convention).
            record.thrown = params[params.size - 1] as Throwable
        }
        // This will disable stack trace lookup inside JUL. If someone wants location info, they can use their own formatter
        // or use a different logging framework like sl4j, or log4j
        record.sourceClassName = null
        record.parameters = params
        logger.log(record)
    }

    override fun unwrap(): Any {
        return logger
    }

    init {
        logger = Logger.getLogger(name)
        logger.useParentHandlers = false
        val handler = ConsoleHandler()
        val formatter: Formatter = JulLogFormater()
        handler.setFormatter(formatter)
        logger.addHandler(handler)
    }
}