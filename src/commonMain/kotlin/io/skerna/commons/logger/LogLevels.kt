/*
 * Copyright (c)  2020  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.skerna.commons.logger

interface LogLevels {

    interface Flow {
        fun record(message: Any)

        fun record(message: Any, vararg params: Any)

        fun record(message: Any, t: Throwable)

        fun record(message: Any, t: Throwable, vararg params: Any)
    }

    interface Info {

        fun     info(message: Any)

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