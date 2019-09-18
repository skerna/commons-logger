package io.skerna.commons.logger

expect object LoggProvider{
    fun getLoggersFactories():Array<LogDelegateFactory>
}