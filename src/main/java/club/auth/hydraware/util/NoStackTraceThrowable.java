package club.auth.hydraware.util;

import club.auth.hydraware.HydraWare;

public class NoStackTraceThrowable extends RuntimeException {

    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "" + HydraWare.VERSION;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
