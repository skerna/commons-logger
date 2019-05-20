package io.skerna.commons.logger

/**
 * @author Ronald Cárdenas
 **/
interface AnsiWritter{
    /**
     * Write on ansi writter format
     */
    fun write(message: String)
}