package io.skerna.commons.logger


/**
 * @author Ronald Cárdenas
 **/
class ALogDelegateFactory:LogDelegateFactory{
    override fun createDelegate(name: String): LogDelegate {
        return ALogDelegte(name)
    }
}