// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct spmat {
 *     idxint* jc;
 *     idxint* ir;
 *     pfloat* pr;
 *     idxint n;
 *     idxint m;
 *     idxint nnz;
 * };
 * }
 */
public class spmat {

    public static MemoryLayout $LAYOUT() {
        return constants$4.const$5;
    }
    public static VarHandle jc$VH() {
        return constants$5.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint* jc;
     * }
     */
    public static MemorySegment jc$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$5.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint* jc;
     * }
     */
    public static void jc$set(MemorySegment seg, MemorySegment x) {
        constants$5.const$0.set(seg, x);
    }
    public static MemorySegment jc$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$5.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void jc$set(MemorySegment seg, long index, MemorySegment x) {
        constants$5.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle ir$VH() {
        return constants$5.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint* ir;
     * }
     */
    public static MemorySegment ir$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$5.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint* ir;
     * }
     */
    public static void ir$set(MemorySegment seg, MemorySegment x) {
        constants$5.const$1.set(seg, x);
    }
    public static MemorySegment ir$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$5.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void ir$set(MemorySegment seg, long index, MemorySegment x) {
        constants$5.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle pr$VH() {
        return constants$5.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat* pr;
     * }
     */
    public static MemorySegment pr$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$5.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat* pr;
     * }
     */
    public static void pr$set(MemorySegment seg, MemorySegment x) {
        constants$5.const$2.set(seg, x);
    }
    public static MemorySegment pr$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$5.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void pr$set(MemorySegment seg, long index, MemorySegment x) {
        constants$5.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle n$VH() {
        return constants$5.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint n;
     * }
     */
    public static long n$get(MemorySegment seg) {
        return (long)constants$5.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint n;
     * }
     */
    public static void n$set(MemorySegment seg, long x) {
        constants$5.const$3.set(seg, x);
    }
    public static long n$get(MemorySegment seg, long index) {
        return (long)constants$5.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void n$set(MemorySegment seg, long index, long x) {
        constants$5.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle m$VH() {
        return constants$5.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint m;
     * }
     */
    public static long m$get(MemorySegment seg) {
        return (long)constants$5.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint m;
     * }
     */
    public static void m$set(MemorySegment seg, long x) {
        constants$5.const$4.set(seg, x);
    }
    public static long m$get(MemorySegment seg, long index) {
        return (long)constants$5.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void m$set(MemorySegment seg, long index, long x) {
        constants$5.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle nnz$VH() {
        return constants$5.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint nnz;
     * }
     */
    public static long nnz$get(MemorySegment seg) {
        return (long)constants$5.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint nnz;
     * }
     */
    public static void nnz$set(MemorySegment seg, long x) {
        constants$5.const$5.set(seg, x);
    }
    public static long nnz$get(MemorySegment seg, long index) {
        return (long)constants$5.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void nnz$set(MemorySegment seg, long index, long x) {
        constants$5.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


