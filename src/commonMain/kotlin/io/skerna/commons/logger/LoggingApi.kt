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
 * The basic logging API. An implementation of this API (or an extension of it) will be
 * returned by any fluent logger, and forms the basis of the fluent call chain.
 *
 *
 * In typical usage each method in the API, with the exception of the terminal `log()`
 * statements, will carry out some simple task (which may involve modifying the context of the log
 * statement) and return the same API for chaining. The exceptions to this are:
 *
 *  * Methods which return a NoOp implementation of the API in order to disable logging.
 *  * Methods which return an alternate API in order to implement context specific grammar (though
 * these alternate APIs should always return the original logging API eventually).
 *
 * A hypothetical example of a context specific grammar might be:
 * <pre>`logger.at(WARNING).whenSystem().isLowOnMemory().log("");
`</pre> *
 * In this example the `whenSystem()` method would return its own API with several context
 * specific methods (`isLowOnMemory()`, `isThrashing()` etc...), however each of these
 * sub-APIs must eventually return the original logging API.
 */
// NOTE: new methods to this interface should be coordinated with google-java-format
interface LoggingApi<API : LoggingApi<API>> {

    /**
     * Returns true if logging is enabled at the level implied for this API, according to the current
     * logger backend. For example:
     * <pre>`if (logger.atFine().isEnabled()) {
     * // Do non-trivial argument processing
     * logger.atFine().log("Message: %s", value);
     * }
    `</pre> *
     *
     *
     * Note that if logging is enabled for a log level, it does not always follow that the log
     * statement will definitely be written to the backend (due to the effects of other methods in
     * the fluent chain), but if this method returns `false` then it can safely be assumed that
     * no logging will occur.
     *
     *
     * This method is unaffected by additional methods in the fluent chain and should only ever be
     * invoked immediately after the level selector method. In other words, the expression:
     * <pre>`logger.atFine().every(100).isEnabled()`</pre>
     * is incorrect because it will always behave identically to:
     * <pre>`logger.atFine().isEnabled()`</pre>
     *
     *
     * <h3>Implementation Note</h3>
     * By avoiding passing a separate `Level` at runtime to determine "loggability", this API
     * makes it easier to coerce bytecode optimizers into doing "dead code" removal on sections
     * guarded by this method.
     *
     *
     * If a proxy logger class is supplied for which:
     * <pre>`logger.atFine()`</pre>
     * unconditionally returns the "NoOp" implementation of the API (in which `isEnabled()`
     * always returns `false`), it becomes simple for bytecode analysis to determine that:
     * <pre>`logger.atFine().isEnabled()`</pre>
     * always evaluates to `false` .
     */
    val isEnabled: Boolean

    fun log(lazyAction: ()->String)

    fun logPair(lazyAction: ()-> Pair<String,Throwable>)

    fun logTriple(lazyAction: ()-> Triple<String,Throwable,Array<Any>>)

    fun log(message: String)

    fun log(message: String, vararg params: Any)

    fun log(message: String, t: Throwable)

    fun log(message: String, t: Throwable, vararg params: Any)
}
