package io.skerna.commons.logger

import java.io.Serializable
import java.util.concurrent.locks.*
import org.apache.logging.log4j.core.*
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.appender.AppenderLoggingException
import org.apache.logging.log4j.core.config.plugins.*
import org.apache.logging.log4j.core.layout.MessageLayout
import org.apache.logging.log4j.core.layout.PatternLayout
import java.util.ArrayList


@Plugin(name = "TestAppender", category = "Core", elementType = "apender", printObject = true)
class TestAppender  constructor(name: String, filter: Filter?, layout: Layout<out Serializable>?) : AbstractAppender(name, filter, layout) {
    var messages: MutableList<LogEvent> = ArrayList()
        get() = field

    override fun append(event: LogEvent) {
        messages.add(event)
    }

    fun getLastMessageFormatted():String{
        if(messages.isEmpty()){
            return ""
        }
        return messages.last().message.formattedMessage
    }
    fun getLastMessageFormat():String{
        if(messages.isEmpty()){
            return ""
        }
        return messages.last().message.format
    }

    fun getLastMessage(): LogEvent {
        return messages.last()
    }

    companion object {
        private val serialVersionUID = 8047713135100613185L

        @JvmStatic
        @PluginFactory
        fun createAppender(@PluginAttribute("name") name: String,
                           @PluginElement("Layout") layout: Layout<out Serializable>?,
                           @PluginElement("Filter") filter: Filter?,
                           @PluginAttribute("otherAttribute") otherAttribute: String?): TestAppender? {
            var layout = layout
            if (name == null) {
                AbstractLifeCycle.LOGGER.error("No name provided for TestAppender")
                return null
            }
            if (layout == null) {
                layout = PatternLayout.createDefaultLayout()
            }
            return TestAppender(name, filter, layout)
        }
    }
}