// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct settings {
 *     pfloat gamma;
 *     pfloat delta;
 *     pfloat eps;
 *     pfloat feastol;
 *     pfloat abstol;
 *     pfloat reltol;
 *     pfloat feastol_inacc;
 *     pfloat abstol_inacc;
 *     pfloat reltol_inacc;
 *     idxint nitref;
 *     idxint maxit;
 *     idxint verbose;
 *     idxint max_bk_iter;
 *     pfloat bk_scale;
 *     pfloat centrality;
 * };
 * }
 */
public class settings {

    public static MemoryLayout $LAYOUT() {
        return constants$23.const$2;
    }
    public static VarHandle gamma$VH() {
        return constants$23.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat gamma;
     * }
     */
    public static double gamma$get(MemorySegment seg) {
        return (double)constants$23.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat gamma;
     * }
     */
    public static void gamma$set(MemorySegment seg, double x) {
        constants$23.const$3.set(seg, x);
    }
    public static double gamma$get(MemorySegment seg, long index) {
        return (double)constants$23.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void gamma$set(MemorySegment seg, long index, double x) {
        constants$23.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle delta$VH() {
        return constants$23.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat delta;
     * }
     */
    public static double delta$get(MemorySegment seg) {
        return (double)constants$23.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat delta;
     * }
     */
    public static void delta$set(MemorySegment seg, double x) {
        constants$23.const$4.set(seg, x);
    }
    public static double delta$get(MemorySegment seg, long index) {
        return (double)constants$23.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void delta$set(MemorySegment seg, long index, double x) {
        constants$23.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle eps$VH() {
        return constants$23.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat eps;
     * }
     */
    public static double eps$get(MemorySegment seg) {
        return (double)constants$23.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat eps;
     * }
     */
    public static void eps$set(MemorySegment seg, double x) {
        constants$23.const$5.set(seg, x);
    }
    public static double eps$get(MemorySegment seg, long index) {
        return (double)constants$23.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void eps$set(MemorySegment seg, long index, double x) {
        constants$23.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle feastol$VH() {
        return constants$24.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat feastol;
     * }
     */
    public static double feastol$get(MemorySegment seg) {
        return (double)constants$24.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat feastol;
     * }
     */
    public static void feastol$set(MemorySegment seg, double x) {
        constants$24.const$0.set(seg, x);
    }
    public static double feastol$get(MemorySegment seg, long index) {
        return (double)constants$24.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void feastol$set(MemorySegment seg, long index, double x) {
        constants$24.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle abstol$VH() {
        return constants$24.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat abstol;
     * }
     */
    public static double abstol$get(MemorySegment seg) {
        return (double)constants$24.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat abstol;
     * }
     */
    public static void abstol$set(MemorySegment seg, double x) {
        constants$24.const$1.set(seg, x);
    }
    public static double abstol$get(MemorySegment seg, long index) {
        return (double)constants$24.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void abstol$set(MemorySegment seg, long index, double x) {
        constants$24.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle reltol$VH() {
        return constants$24.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat reltol;
     * }
     */
    public static double reltol$get(MemorySegment seg) {
        return (double)constants$24.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat reltol;
     * }
     */
    public static void reltol$set(MemorySegment seg, double x) {
        constants$24.const$2.set(seg, x);
    }
    public static double reltol$get(MemorySegment seg, long index) {
        return (double)constants$24.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void reltol$set(MemorySegment seg, long index, double x) {
        constants$24.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle feastol_inacc$VH() {
        return constants$24.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat feastol_inacc;
     * }
     */
    public static double feastol_inacc$get(MemorySegment seg) {
        return (double)constants$24.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat feastol_inacc;
     * }
     */
    public static void feastol_inacc$set(MemorySegment seg, double x) {
        constants$24.const$3.set(seg, x);
    }
    public static double feastol_inacc$get(MemorySegment seg, long index) {
        return (double)constants$24.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void feastol_inacc$set(MemorySegment seg, long index, double x) {
        constants$24.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle abstol_inacc$VH() {
        return constants$24.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat abstol_inacc;
     * }
     */
    public static double abstol_inacc$get(MemorySegment seg) {
        return (double)constants$24.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat abstol_inacc;
     * }
     */
    public static void abstol_inacc$set(MemorySegment seg, double x) {
        constants$24.const$4.set(seg, x);
    }
    public static double abstol_inacc$get(MemorySegment seg, long index) {
        return (double)constants$24.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void abstol_inacc$set(MemorySegment seg, long index, double x) {
        constants$24.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle reltol_inacc$VH() {
        return constants$24.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat reltol_inacc;
     * }
     */
    public static double reltol_inacc$get(MemorySegment seg) {
        return (double)constants$24.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat reltol_inacc;
     * }
     */
    public static void reltol_inacc$set(MemorySegment seg, double x) {
        constants$24.const$5.set(seg, x);
    }
    public static double reltol_inacc$get(MemorySegment seg, long index) {
        return (double)constants$24.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void reltol_inacc$set(MemorySegment seg, long index, double x) {
        constants$24.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle nitref$VH() {
        return constants$25.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint nitref;
     * }
     */
    public static long nitref$get(MemorySegment seg) {
        return (long)constants$25.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint nitref;
     * }
     */
    public static void nitref$set(MemorySegment seg, long x) {
        constants$25.const$0.set(seg, x);
    }
    public static long nitref$get(MemorySegment seg, long index) {
        return (long)constants$25.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void nitref$set(MemorySegment seg, long index, long x) {
        constants$25.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle maxit$VH() {
        return constants$25.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint maxit;
     * }
     */
    public static long maxit$get(MemorySegment seg) {
        return (long)constants$25.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint maxit;
     * }
     */
    public static void maxit$set(MemorySegment seg, long x) {
        constants$25.const$1.set(seg, x);
    }
    public static long maxit$get(MemorySegment seg, long index) {
        return (long)constants$25.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void maxit$set(MemorySegment seg, long index, long x) {
        constants$25.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle verbose$VH() {
        return constants$25.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint verbose;
     * }
     */
    public static long verbose$get(MemorySegment seg) {
        return (long)constants$25.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint verbose;
     * }
     */
    public static void verbose$set(MemorySegment seg, long x) {
        constants$25.const$2.set(seg, x);
    }
    public static long verbose$get(MemorySegment seg, long index) {
        return (long)constants$25.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void verbose$set(MemorySegment seg, long index, long x) {
        constants$25.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle max_bk_iter$VH() {
        return constants$25.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * idxint max_bk_iter;
     * }
     */
    public static long max_bk_iter$get(MemorySegment seg) {
        return (long)constants$25.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * idxint max_bk_iter;
     * }
     */
    public static void max_bk_iter$set(MemorySegment seg, long x) {
        constants$25.const$3.set(seg, x);
    }
    public static long max_bk_iter$get(MemorySegment seg, long index) {
        return (long)constants$25.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void max_bk_iter$set(MemorySegment seg, long index, long x) {
        constants$25.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle bk_scale$VH() {
        return constants$25.const$4;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat bk_scale;
     * }
     */
    public static double bk_scale$get(MemorySegment seg) {
        return (double)constants$25.const$4.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat bk_scale;
     * }
     */
    public static void bk_scale$set(MemorySegment seg, double x) {
        constants$25.const$4.set(seg, x);
    }
    public static double bk_scale$get(MemorySegment seg, long index) {
        return (double)constants$25.const$4.get(seg.asSlice(index*sizeof()));
    }
    public static void bk_scale$set(MemorySegment seg, long index, double x) {
        constants$25.const$4.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle centrality$VH() {
        return constants$25.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * pfloat centrality;
     * }
     */
    public static double centrality$get(MemorySegment seg) {
        return (double)constants$25.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * pfloat centrality;
     * }
     */
    public static void centrality$set(MemorySegment seg, double x) {
        constants$25.const$5.set(seg, x);
    }
    public static double centrality$get(MemorySegment seg, long index) {
        return (double)constants$25.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void centrality$set(MemorySegment seg, long index, double x) {
        constants$25.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


