package io.skerna.commons.logger

import com.jraska.console.Console

/**
 * @author Ronald Cárdenas
 **/
class ViewConsoleDelegate(
    override val isDebugEnabled: Boolean = true,
    override val isInfoEnabled: Boolean= true,
    override val isTraceEnabled: Boolean= true,
    override val isWarnEnabled: Boolean= true

) :LogDelegate{

    override fun debug(message: Any) {
        writeInConsole(DEBUG,message,null,null)
    }

    override fun debug(message: Any, vararg params: Any) {
        writeInConsole(DEBUG,message,null,params)
    }

    override fun debug(message: Any, t: Throwable) {
        writeInConsole(DEBUG,message,t,null)
    }

    override fun debug(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(DEBUG,message,t,params)
    }

    override fun error(message: Any) {
        writeInConsole(ERROR,message,null,null)
    }

    override fun error(message: Any, vararg params: Any) {
        writeInConsole(ERROR,message,null,params)
    }

    override fun error(message: Any, t: Throwable) {
        writeInConsole(ERROR,message,t,null)
    }

    override fun error(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(ERROR,message,t,params)
    }

    override fun fatal(message: Any) {
        writeInConsole(ERROR,message,null,null)
    }

    override fun fatal(message: Any, t: Throwable) {
        writeInConsole(ERROR,message,t,null)
    }

    override fun info(message: Any) {
        writeInConsole(INFO,message,null,null)
    }

    override fun info(message: Any, vararg params: Any) {
        writeInConsole(INFO,message,null,params)
    }

    override fun info(message: Any, t: Throwable) {
        writeInConsole(INFO,message,t,null)

    }

    override fun info(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(INFO,message,t,params)
    }

    override fun trace(message: Any) {
    }

    override fun trace(message: Any, vararg params: Any) {
    }

    override fun trace(message: Any, t: Throwable) {
    }

    override fun trace(message: Any, t: Throwable, vararg params: Any) {
    }

    override fun warn(message: Any) {
        writeInConsole(INFO,message,null,null)
    }

    override fun warn(message: Any, vararg params: Any) {
        writeInConsole(INFO,message,null,params)
    }

    override fun warn(message: Any, t: Throwable) {
        writeInConsole(INFO,message,t,null)
    }

    override fun warn(message: Any, t: Throwable, vararg params: Any) {
        writeInConsole(INFO,message,t,params)
    }


    fun writeInConsole(level:String,message: Any, t: Throwable?, vararg params: Any?) {
        /**if(t!=null){
            if(level.equals(DEBUG)){
                Timber.d(t, message.toString(), params)
            }else  if(level.equals(ERROR)){
                Timber.e(t, message.toString(), params)
            }else if(level.equals(INFO)){
                Timber.i(t, message.toString(), params)
            }else if(level.equals(WARNING)){
                Timber.w(t, message.toString(), params)
            }
            return
        }
        if(level.equals(DEBUG)){
            Timber.d(message.toString(), params)
        }else  if(level.equals(ERROR)){
            Timber.e(message.toString(), params)
        }else if(level.equals(INFO)){
            Timber.i(message.toString(), params)
        }else if(level.equals(WARNING)){
            Timber.w(message.toString(), params)
        }**/
        Console.writeLine("$message  ,${t?.message},${params.joinToString { "," }}")
        println("$level :$message  ,${t?.message},${params.joinToString { "," }}")
    }
    companion object Level {
        val DEBUG = "D"
        val ERROR="E"
        val INFO="I"
        val WARNING="W"
    }
}