// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct expcone {
 *     idxint colstart[3];
 *     pfloat v[6];
 *     pfloat g[3];
 * };
 * }
 */
public class expcone {

    public static MemoryLayout $LAYOUT() {
        return constants$8.const$0;
    }
    public static MemorySegment colstart$slice(MemorySegment seg) {
        return seg.asSlice(0, 24);
    }
    public static MemorySegment v$slice(MemorySegment seg) {
        return seg.asSlice(24, 48);
    }
    public static MemorySegment g$slice(MemorySegment seg) {
        return seg.asSlice(72, 24);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


