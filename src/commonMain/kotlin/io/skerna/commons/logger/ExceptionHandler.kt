package io.skerna.commons.logger

/**
 * ExceptionHandler, this method allow handle exception
 */
interface ExceptionHandler {
    /**
     * onException, this method is called when a exceptions is fired
     * @param exception
     */
    fun onException(exception:Exception)
}

/**
 * ExceptionHandler,
 */
fun ExceptionHandler.create(action:(exception: Exception)->Unit):ExceptionHandler{
    return object:ExceptionHandler{
        override fun onException(exception: Exception) {
            action(exception)
        }
    }
}



