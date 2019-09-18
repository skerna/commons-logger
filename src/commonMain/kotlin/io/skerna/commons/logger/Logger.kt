package io.skerna.commons.logger

class Logger: AbstractLogger<Logger.Api> {
    interface Api: LoggingApi<Api>

    private val NO_OOP = Noop(false);

    constructor(backend: LogDelegate) : super(backend)


    override fun at(level: Level): Api {
        var isLoggable = isLoggable(level);
        if(!isLoggable){
            return NO_OOP
        }else{
            return LoggerContext(level= level)
        }
    }

    /**
     * LoggerContext logger response cuando el log esta activado
     */
    inner class LoggerContext(
            val level: Level,
            val wasForced:Boolean = false
    ): Api {
        override val isEnabled: Boolean
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun log(lazyAction: () -> String) {
            val message = lazyAction()
            backend.log(level,message)
        }

        override fun logPair(lazyAction: () -> Pair<String, Throwable>) {
            val (message, exception) = lazyAction()
            backend.log(level,message,exception)
        }

        override fun logTriple(lazyAction: () -> Triple<String, Throwable, Array<Any>>) {
            val (message, exception,args) = lazyAction()
            backend.log(level,message,exception,args)
        }

        override fun log(message: String) {
            backend.log(level,message)
        }

        override fun log(message: String, vararg params: Any) {
            backend.log(level,message,params)
        }

        override fun log(message: String, t: Throwable) {
            backend.log(level,message,t)
        }

        override fun log(message: String, t: Throwable, vararg params: Any) {
            backend.log(level,message,t,params)
        }
    }

    /**
     * Noop class si el logger no esta habilitado ninguna accion es ejecutada
     * @param isEnabled
     */
    inner class Noop(override val isEnabled: Boolean): Api {

        override fun log(lazyAction: () -> String) {}

        override fun logPair(lazyAction: () -> Pair<String, Throwable>) {}

        override fun logTriple(lazyAction: () -> Triple<String, Throwable, Array<Any>>) {}

        override fun log(message: String) {}

        override fun log(message: String, vararg params: Any) {}

        override fun log(message: String, t: Throwable) {}

        override fun log(message: String, t: Throwable, vararg params: Any) {}
    }


}