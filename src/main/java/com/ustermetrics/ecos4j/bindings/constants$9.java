// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$9 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$9() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "scaleToAddExpcone",
        constants$6.const$0
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(JAVA_LONG,
        RuntimeHelper.POINTER,
        JAVA_LONG
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "evalExpPrimalFeas",
        constants$9.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "evalExpDualFeas",
        constants$9.const$1
    );
    static final StructLayout const$4 = MemoryLayout.structLayout(
        JAVA_LONG.withName("p"),
        RuntimeHelper.POINTER.withName("w"),
        RuntimeHelper.POINTER.withName("v"),
        RuntimeHelper.POINTER.withName("kkt_idx")
    ).withName("lpcone");
    static final VarHandle const$5 = constants$9.const$4.varHandle(MemoryLayout.PathElement.groupElement("p"));
}


