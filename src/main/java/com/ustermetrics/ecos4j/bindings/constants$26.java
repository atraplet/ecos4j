// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$26 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$26() {}
    static final StructLayout const$0 = MemoryLayout.structLayout(
        JAVA_DOUBLE.withName("pcost"),
        JAVA_DOUBLE.withName("dcost"),
        JAVA_DOUBLE.withName("pres"),
        JAVA_DOUBLE.withName("dres"),
        JAVA_DOUBLE.withName("pinf"),
        JAVA_DOUBLE.withName("dinf"),
        JAVA_DOUBLE.withName("pinfres"),
        JAVA_DOUBLE.withName("dinfres"),
        JAVA_DOUBLE.withName("gap"),
        JAVA_DOUBLE.withName("relgap"),
        JAVA_DOUBLE.withName("sigma"),
        JAVA_DOUBLE.withName("mu"),
        JAVA_DOUBLE.withName("step"),
        JAVA_DOUBLE.withName("step_aff"),
        JAVA_DOUBLE.withName("kapovert"),
        JAVA_LONG.withName("iter"),
        JAVA_LONG.withName("nitref1"),
        JAVA_LONG.withName("nitref2"),
        JAVA_LONG.withName("nitref3"),
        JAVA_DOUBLE.withName("tsetup"),
        JAVA_DOUBLE.withName("tsolve"),
        JAVA_LONG.withName("pob"),
        JAVA_LONG.withName("cb"),
        JAVA_LONG.withName("cob"),
        JAVA_LONG.withName("pb"),
        JAVA_LONG.withName("db"),
        JAVA_LONG.withName("affBack"),
        JAVA_LONG.withName("cmbBack"),
        JAVA_DOUBLE.withName("centrality")
    ).withName("stats");
    static final VarHandle const$1 = constants$26.const$0.varHandle(MemoryLayout.PathElement.groupElement("pcost"));
    static final VarHandle const$2 = constants$26.const$0.varHandle(MemoryLayout.PathElement.groupElement("dcost"));
    static final VarHandle const$3 = constants$26.const$0.varHandle(MemoryLayout.PathElement.groupElement("pres"));
    static final VarHandle const$4 = constants$26.const$0.varHandle(MemoryLayout.PathElement.groupElement("dres"));
    static final VarHandle const$5 = constants$26.const$0.varHandle(MemoryLayout.PathElement.groupElement("pinf"));
}

