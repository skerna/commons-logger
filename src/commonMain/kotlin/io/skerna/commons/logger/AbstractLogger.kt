/*
 * Copyright (C) 2012 The Flogger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.skerna.commons.logger


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

    /** A convenience method for at([Level.WARNING]).  */
    fun atWarning(): API {
        return at(Level.WARNING)
    }

    /** A convenience method for at([Level.INFO]).  */
    fun atInfo(): API {
        return at(Level.INFO)
    }

    fun atDebug():API{
        return at(Level.DEBUG)
    }
    /** A convenience method for at([Level.CONFIG]).  */
    fun atConfig(): API {
        return at(Level.CONFIG)
    }

    /** A convenience method for at([Level.FINE]).  */
    fun atTrace(): API {
        return at(Level.TRACE)
    }

    /** A convenience method for at([Level.FINER]).  */
    fun atFiner(): API {
        return at(Level.FINER)
    }

    /** A convenience method for at([Level.FINEST]).  */
    fun atFinest(): API {
        return at(Level.FINEST)
    }

    /**
     * Returns whether the given level is enabled for this logger. Users wishing to guard code with a
     * check for "loggability" should use `logger.atLevel().isEnabled()` instead.
     */
    protected fun isLoggable(level: Level): Boolean {
        return backend.isLoggable(level)
    }

}
