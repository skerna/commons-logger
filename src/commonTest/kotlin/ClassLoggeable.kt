/*
 * Copyright (c)  2019  SKERNA
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
 */

package io.skerna.slog

class ClassLoggeable(val logger: Logger) {


    fun executeTestFunction() {
        logger.info("Some log")
        logger.debug("Some log")
        logger.trace("Some log")
        logger.warn("Some log")
        logger.error("Some log")
        logger.info("Some log")
    }

    /**
     * @throws java.lang.RuntimeException
     */
    fun executeTestFunctionWithExceptions() {
        logger.info("Some log", createExption())
        logger.debug("Some log", createExption())
        logger.trace("Some log", createExption())
        logger.warn("Some log", createExption())
        logger.error("Some log", createExption())
        logger.info("Some log", createExption())
    }

    fun createExption(): RuntimeException {
        return RuntimeException("Este es un test de logger con expcetions")
    }
}
