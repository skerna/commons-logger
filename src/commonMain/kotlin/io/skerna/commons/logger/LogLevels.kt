package io.skerna.commons.logger

interface LogLevels {

    interface Flow {
        fun record(message: Any)

        fun record(message: Any, vararg params: Any)

        fun record(message: Any, t: Throwable)

        fun record(message: Any, t: Throwable, vararg params: Any)
    }

    interface Info {

        fun info(message: Any)

        fun info(message: Any, vararg params: Any)

        fun info(message: Any, t: Throwable)

        fun info(message: Any, t: Throwable, vararg params: Any)
    }

    interface Debug {
        fun debug(message: Any)

        fun debug(message: Any, vararg params: Any)

        fun debug(message: Any, t: Throwable)

        fun debug(message: Any, t: Throwable, vararg params: Any)

    }

    interface Warning {
        fun warn(message: Any)

        fun warn(message: Any, vararg params: Any)

        fun warn(message: Any, t: Throwable)

        fun warn(message: Any, t: Throwable, vararg params: Any)

    }

    interface Error {
        fun error(message: Any)

        fun error(message: Any, vararg params: Any)

        fun error(message: Any, t: Throwable)

        fun error(message: Any, t: Throwable, vararg params: Any)
    }

    interface Trace {
        fun trace(message: Any)

        fun trace(message: Any, vararg params: Any)

        fun trace(message: Any, t: Throwable)

        fun trace(message: Any, t: Throwable, vararg params: Any)
    }


    interface Fatal {

        fun fatal(message: Any)

        fun fatal(message: Any, t: Throwable)
    }

    interface ALL:Info,Warning,Debug,Trace

}