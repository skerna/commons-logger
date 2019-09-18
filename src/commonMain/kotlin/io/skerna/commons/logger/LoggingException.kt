package io.skerna.commons.logger

/**
 * Exception thrown when a log statement cannot be emitted correctly. This exception should only be
 * thrown by logger backend implementations which have opted not to handle specific issues.
 *
 *
 * Typically a logger backend would only throw `LoggingException` in response to issues in
 * test code or other debugging environments. In production code, the backend should be configured
 * to emit a modified log statement which includes the error information.
 *
 *
 * See also [LoggerBackend.handleError].
 */
class LoggingException : RuntimeException {

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
