package io.skerna.commons.logger

abstract class AbstractLoggerDelegate(val name:String):LogDelegate,LogLevels.ALL {
    private var exceptionHandler:ExceptionHandler?=null


    override fun log(level: Level, message: String) {
        when(level){
            Level.INFO -> info(message)
            Level.DEBUG -> debug(message)
            Level.ERROR -> error(message)
            Level.WARNING -> warn(message)
        }
    }

    override fun log(level: Level, message: String, exception: Throwable) {
        when(level){
            Level.INFO -> info(message,exception)
            Level.DEBUG -> debug(message,exception)
            Level.ERROR -> error(message,exception)
            Level.WARNING -> warn(message,exception)
        }
    }

    override fun log(level: Level, message: String, vararg params: Any) {
        when(level){
            Level.INFO -> info(message,params)
            Level.DEBUG -> debug(message,params)
            Level.ERROR -> error(message,params)
            Level.WARNING -> warn(message,params)
        }
    }

    override fun log(level: Level, exception: Throwable, message: String, vararg params: Array<Any>) {
        when(level){
            Level.INFO -> info(message,exception,params)
            Level.DEBUG -> debug(message,exception,params)
            Level.ERROR -> error(message,exception,params)
            Level.WARNING -> warn(message,exception,params)
        }
    }

    override fun setExceptionHandler(exceptionHandler: ExceptionHandler) {
        this.exceptionHandler = exceptionHandler
    }

    override fun onException(exception: Exception) {
        exceptionHandler?.onException(exception)
    }

    override fun getLoggerName(): String {
        return this.name
    }
}