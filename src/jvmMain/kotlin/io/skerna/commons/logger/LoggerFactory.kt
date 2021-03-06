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

import io.skerna.commons.sansi.cyan
import io.skerna.commons.sansi.yellow
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

const val VERTX_DELEGATE_LOGSYS_PROP = "vertx.logger-delegate-factory-class-name"

actual object LoggerFactory {

    @Volatile
    private var delegateFactory: LogDelegateFactory? = null

    private val loggers = ConcurrentHashMap<String, Logger>()

    init {
        initialise()
    }

    @Synchronized
    actual fun initialise() {
        val delegateFactory: LogDelegateFactory

        // If a system property is specified then this overrides any delegate factory which is set
        // programmatically - this is primarily of use so we can configure the logger delegate on the client side.
        // call to System.getProperty is wrapped in a try block as it will fail if the client runs in a secured
        // environment
        var className: String? = PrintDelegateFactory::class.java.name
        try {
            className = System.getProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME)
        } catch (e: Exception) {
            println("Warning: se trato de acceder a la propiedad $LOGGER_DELEGATE_FACTORY_CLASS_NAME  ${e.message}".yellow())
        }
        if (className != null) {
            val loader = Thread.currentThread().contextClassLoader
            //val loader = this.javaClass.classLoader
            try {
                val clz = loader.loadClass(className)
                delegateFactory = clz.getConstructor().newInstance() as LogDelegateFactory
            } catch (e: Exception) {
                throw IllegalArgumentException("Error instantiating transformer class \"$className\"", e)
            }

        } else {
            delegateFactory = PrintDelegateFactory()
        }
        configureTargetLogger(delegateFactory)
        LoggerFactory.delegateFactory = delegateFactory
    }

    /**
     * permite confugurar el delegate Log system para vertx
     */
    private fun configureTargetLogger(delegate: LogDelegateFactory) {
        if (System.getProperty(VERTX_DELEGATE_LOGSYS_PROP).isNullOrEmpty()) {
            when (delegate) {
                is PrintDelegateFactory -> {
                    System.setProperty(VERTX_DELEGATE_LOGSYS_PROP, "io.vertx.core.logging.Log4j2LogDelegateFactory")
                }
                is SLF4JLogDelegateFactory -> {
                    System.setProperty(VERTX_DELEGATE_LOGSYS_PROP, "io.vertx.core.logging.SLF4JLogDelegateFactory")
                }
                else -> {
                    System.setProperty(VERTX_DELEGATE_LOGSYS_PROP, "io.vertx.core.logging.JULLogDelegateFactory")
                }
            }
        }
        println("Vertx Logger intialize from SKERNA ${System.getProperty(VERTX_DELEGATE_LOGSYS_PROP)}".cyan())
    }

    @JvmStatic
    actual inline fun logger(clazz: KClass<*>): Logger {
        val javaClass = clazz.java
        return logger(javaClass)
    }

    @JvmStatic
    fun logger(clazz: Class<*>): Logger {
        val name = if (javaClass.isMemberClass)
            javaClass.enclosingClass.canonicalName
        else
            clazz.canonicalName
        return logger(name)
    }

    /**
     * returns new logger with specific configuration
     * if logger doest not exists a new logger is created using name and configuration
     * pass as parameters
     * @param name
     * @return Logger
     */
    @JvmStatic
    actual fun logger(name: String): Logger {
        return logger(name,LoggerConfiguration.instanceGlobalContext)
    }

    /**
     * returns new logger with specific configuration
     * if logger doest not exists a new logger is created using name and configuration
     * pass as parameters
     * @param name
     * @param configuration
     * @return Logger
     */
    actual fun logger(name: String, configuration: LoggerConfiguration): Logger {
        var logger: Logger? = loggers[name]

        if (logger == null) {
            val delegate = delegateFactory!!.createDelegate(name,configuration)

            logger = Logger(delegate)

            val oldLogger = (loggers).putIfAbsent(name, logger)

            if (oldLogger != null) {
                logger = oldLogger
            }
        }
        return logger
    }


    /**
     * removeLogger, delete logger from holder
     * @param name
     */
    @JvmStatic
    actual fun removeLogger(name: String) {
        loggers.remove(name)
    }



    @JvmStatic
    actual fun setLogDelegateFactory(delegateFactory: LogDelegateFactory) {
        //  configureTargetLogger(delegateFactory)
        System.setProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME,delegateFactory.javaClass.canonicalName)
        initialise()
    }

    actual inline fun <reified T> logger(): Logger {
        return logger(T::class)
    }

}
