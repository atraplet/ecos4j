// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$15 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$15() {}
    static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_DOUBLE,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_DOUBLE,
        JAVA_DOUBLE,
        RuntimeHelper.POINTER,
        JAVA_DOUBLE
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "evalSymmetricBarrierValue",
        constants$15.const$0
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "scale",
        constants$14.const$0
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "scale2add",
        constants$14.const$0
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "unscale",
        constants$14.const$0
    );
    static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_DOUBLE,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
        "conicProduct",
        constants$15.const$5
    );
}


