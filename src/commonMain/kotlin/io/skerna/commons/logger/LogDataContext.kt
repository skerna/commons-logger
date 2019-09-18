package io.skerna.commons.logger

import io.skerna.commons.logger.LogData
import io.skerna.commons.logger.Level

class LogDataContext: LogData {

    private val loggerName:String
    private val level:Level
    private val message:String
    private val arguments:Array<Any>
    private val wasForced:Boolean
    private var exception:Throwable?=null

    constructor(loggerName: String,
                level: Level,
                message: String,
                arguments: Array<Any>,
                wasForced: Boolean,
                exception: Throwable?=null) {
        this.loggerName = loggerName
        this.level = level
        this.message = message
        this.arguments = arguments
        this.wasForced = wasForced
        this.exception = exception
    }


    override fun level(): Level {
        return this.level;
    }

    override fun loggerName(): String {
        return this.loggerName
    }

    override fun arguments(): Array<Any> {
        return this.arguments
    }

    override fun wasForced(): Boolean {
        return this.wasForced
    }

    override fun message(): String {
        return this.message
    }

    override fun exception(): Throwable? {
        return this.exception
    }
}