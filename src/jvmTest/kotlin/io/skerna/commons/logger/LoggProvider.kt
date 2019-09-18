package io.skerna.commons.logger

actual object LoggProvider {
    actual fun getLoggersFactories(): Array<LogDelegateFactory> {
        return arrayOf(
                Log4j2LogDelegateFactory()
        )
    }
}