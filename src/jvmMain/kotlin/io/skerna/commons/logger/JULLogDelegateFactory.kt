package io.skerna.commons.logger

import java.io.IOException
import java.util.logging.LogManager

class JULLogDelegateFactory : LogDelegateFactory {
    companion object {
        fun loadConfig() {
            try {
                JULLogDelegateFactory::class.java.classLoader.getResourceAsStream("skerna-default-jul-logging.properties").use { `is` ->
                    if (`is` != null) {
                        LogManager.getLogManager().readConfiguration(`is`)
                    }
                }
            } catch (ignore: IOException) {
            }
        }

        init {
            // Try and load vert.x JUL default logging config from classpath
            if (System.getProperty("java.util.logging.config.file") == null) {
                loadConfig()
            }
        }
    }


    override fun createDelegate(name: String, configuration: LoggerConfiguration): JULLogDelegate {
        return JULLogDelegate(name,configuration)
    }
}