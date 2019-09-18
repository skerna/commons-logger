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

import io.skerna.commons.logger.Level


/**
 * A backend API for determining metadata associated with a log statement.
 *
 *
 * Some metadata is expected to be available for all log statements (such as the log level or a
 * timestamp) whereas other data is optional (class/method name for example). As well providing the
 * common logging metadata, customized loggers can choose to add arbitrary key/value pairs to the
 * log data. It is up to each logging backend implementation to decide how it interprets this data
 * using the hierarchical key. See [Metadata].
 */
interface LogData {

    fun level():Level

    fun loggerName():String

    fun message():String

    fun arguments():Array<Any>

    fun wasForced():Boolean

    fun exception():Throwable?

}
