package io.skerna.commons.logger

import android.graphics.Color
import timber.log.Timber

/**
 * @author Ronald Cárdenas
 **/
class ViewConsoleFactory:LogDelegateFactory{
    override fun createDelegate(name: String): LogDelegate {
        // In your Application or wherever you register your trees
        val consoleTree = ConsoleTree.Builder()
            .debugColor(Color.CYAN)
            .infoColor(Color.GREEN)
            .warnColor(Color.YELLOW)
            .errorColor(Color.RED)
            .build()
        Timber.plant(consoleTree)
        return ViewConsoleDelegate()
    }
}