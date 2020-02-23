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

import kotlin.math.log


/**
 * Base class for the fluent logger API. This class is a factory for instances of a fluent logging
 * API, used to build log statements via method chaining.
 *
 * @param <API> the logging API provided by this logger.
</API> */
abstract class AbstractLogger<API : LoggingApi<API>>  protected constructor(internal val backend: LogDelegate) {
    // ---- IMPLEMENTATION DETAIL (only visible to the base logging context) ----
    /**
     * Returns the non-null name of this logger (Flogger does not currently support anonymous
     * loggers).
     */
    // IMPORTANT: Flogger does not currently support the idea of an anonymous logger instance
    // (but probably should). The issue here is that in order to allow the FluentLogger instance
    // and the LoggerConfig instance to share the same underlying logger, while allowing the
    // backend API to be flexible enough _not_ to admit the existence of the JDK logger, we will
    // need to push the LoggerConfig API down into the backend and expose it from there.
    // See b/14878562
    // TODO(dbeaumont): Make anonymous loggers work with the config() method and the LoggerConfig API.
    protected val name: String
        get() = backend.getLoggerName()


    // ---- PUBLIC API ----

    /**
     * Returns a fluent logging API appropriate for the specified log level.
     *
     *
     * If a logger implementation determines that logging is definitely disabled at this point then
     * this method is expected to return a "no-op" implementation of the logging API, which will
     * result in all further calls made for the log statement to being silently ignored.
     *
     *
     * Typically an implementation of this method in a concrete subclass would look like:
     * <pre>`return isLoggable(level) ? new Context(level): NO_OP;
    `</pre> *
     * where `NO_OP` is a singleton, no-op instance of the logging API.
     */
    abstract fun at(level: Level): API

    /** A convenience method for at([Level.SEVERE]).  */
    fun atSevere(): API {
        return at(Level.SEVERE)
    }

    fun atSevere(function:API.()->Unit){
        function(atSevere())
    }

    /**
     * A convenience method for at([Level.ERROR])
     */
    fun atError():API{
        return at(Level.ERROR)
    }

    fun atError(function:API.()->Unit){
        function(atError())
    }

    /** A convenience method for at([Level.WARNING]).  */
    fun atWarning(): API {
        return at(Level.WARNING)
    }

    fun atWarning(function:API.()->Unit){
        function(atWarning())
    }

    /** A convenience method for at([Level.INFO]).  */
    fun atInfo(): API {
        return at(Level.INFO)
    }

    fun atInfo(function:API.()->Unit){
        function(atInfo())
    }

    fun atDebug():API{
        return at(Level.DEBUG)
    }

    fun atDebug(function:API.()->Unit){
        function(atDebug())
    }

    /** A convenience method for at([Level.CONFIG]).  */
    fun atConfig(): API {
        return at(Level.CONFIG)
    }

    fun atConfig(function:API.()->Unit){
        function(atConfig())
    }

    /** A convenience method for at([Level.FINE]).  */
    fun atTrace(): API {
        return at(Level.TRACE)
    }

    fun atTrace(function:API.()->Unit){
        function(atTrace())
    }

    /** A convenience method for at([Level.FINER]).  */
    fun atFiner(): API {
        return at(Level.FINER)
    }

    fun atFiner(function:API.()->Unit){
        function(atFiner())
    }

    /** A convenience method for at([Level.FINEST]).  */
    fun atFinest(): API {
        return at(Level.FINEST)
    }

    fun atFinest(function:API.()->Unit){
        function(atFinest())
    }

    /**
     * Returns whether the given level is enabled for this logger. Users wishing to guard code with a
     * check for "loggability" should use `logger.atLevel().isEnabled()` instead.
     */
    protected fun isLoggable(level: Level): Boolean {
        return backend.isLoggable(level)
    }

}
