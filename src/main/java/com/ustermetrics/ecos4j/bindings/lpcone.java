// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct lpcone {
 *     idxint p;
 *     pfloat* w;
 *     pfloat* v;
 *     idxint* kkt_idx;
 * };
 * }
 */
public class lpcone {

    public static MemoryLayout $LAYOUT() {
        return constants$9.const$4;
    }
    public static VarHandle p$VH() {
        return constants$9.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint p;
     * }
     */
    public static long p$get(MemorySegment seg) {
        return (long)constants$9.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint p;
     * }
     */
    public static void p$set(MemorySegment seg, long x) {
        constants$9.const$5.set(seg, x);
    }
    public static long p$get(MemorySegment seg, long index) {
        return (long)constants$9.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void p$set(MemorySegment seg, long index, long x) {
        constants$9.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle w$VH() {
        return constants$10.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat* w;
     * }
     */
    public static MemorySegment w$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$10.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat* w;
     * }
     */
    public static void w$set(MemorySegment seg, MemorySegment x) {
        constants$10.const$0.set(seg, x);
    }
    public static MemorySegment w$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$10.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void w$set(MemorySegment seg, long index, MemorySegment x) {
        constants$10.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle v$VH() {
        return constants$10.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat* v;
     * }
     */
    public static MemorySegment v$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$10.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat* v;
     * }
     */
    public static void v$set(MemorySegment seg, MemorySegment x) {
        constants$10.const$1.set(seg, x);
    }
    public static MemorySegment v$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$10.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void v$set(MemorySegment seg, long index, MemorySegment x) {
        constants$10.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle kkt_idx$VH() {
        return constants$10.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint* kkt_idx;
     * }
     */
    public static MemorySegment kkt_idx$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$10.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint* kkt_idx;
     * }
     */
    public static void kkt_idx$set(MemorySegment seg, MemorySegment x) {
        constants$10.const$2.set(seg, x);
    }
    public static MemorySegment kkt_idx$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$10.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void kkt_idx$set(MemorySegment seg, long index, MemorySegment x) {
        constants$10.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

