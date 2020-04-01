package io.skerna.commons.logger

import io.skerna.commons.sansi.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Formatter
import java.util.logging.Level
import java.util.logging.LogRecord
class JulLogFormater : Formatter() {
    // Here you can configure the format of the output and
    // its color by using the ANSI escape codes defined above.
    // format is called for every console log message
    override fun format(record: LogRecord): String {
        var stringBuilder = StringBuilder()
        val message = record.message
        val messageArgsBuilder = StringBuilder()
        for (parameter in record.parameters) {
            messageArgsBuilder.append(parameter.toString())
        }
        val messageArgs = messageArgsBuilder.toString()
        stringBuilder.append(calcDate(record.millis).yellow())
                .append(" ${getLevel(record.level)}")
                .append(" [${Thread.currentThread().name.lightMagenta()}]")
                .append(" - ${record.sourceClassName?:"unknow".lightCyan()}")
                .append(" ")
                .append(message)
                .append(" $messageArgs")
                .append("\n")
        return stringBuilder.toString()
    }

    private fun calcDate(millisecs: Long): String {
        val date_format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val resultdate = Date(millisecs)
        return date_format.format(resultdate)
    }

    private fun getLevel(level:Level): String {
        return when(level){
            Level.INFO -> level.toString().green()
            Level.WARNING-> level.toString().yellow()
            Level.FINE-> level.toString().cyan()
            Level.SEVERE-> level.toString().red()
            Level.FINEST-> level.toString().blue()
            else-> level.toString()
        }
    }


}