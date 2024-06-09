// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct lpcone {
 *     idxint p;
 *     pfloat *w;
 *     pfloat *v;
 *     idxint *kkt_idx;
 * }
 * }
 */
public class lpcone {

    lpcone() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        ecos_h.C_LONG_LONG.withName("p"),
        ecos_h.C_POINTER.withName("w"),
        ecos_h.C_POINTER.withName("v"),
        ecos_h.C_POINTER.withName("kkt_idx")
    ).withName("lpcone");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfLong p$LAYOUT = (OfLong)$LAYOUT.select(groupElement("p"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * idxint p
     * }
     */
    public static final OfLong p$layout() {
        return p$LAYOUT;
    }

    private static final long p$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * idxint p
     * }
     */
    public static final long p$offset() {
        return p$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * idxint p
     * }
     */
    public static long p(MemorySegment struct) {
        return struct.get(p$LAYOUT, p$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * idxint p
     * }
     */
    public static void p(MemorySegment struct, long fieldValue) {
        struct.set(p$LAYOUT, p$OFFSET, fieldValue);
    }

    private static final AddressLayout w$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("w"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * pfloat *w
     * }
     */
    public static final AddressLayout w$layout() {
        return w$LAYOUT;
    }

    private static final long w$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * pfloat *w
     * }
     */
    public static final long w$offset() {
        return w$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * pfloat *w
     * }
     */
    public static MemorySegment w(MemorySegment struct) {
        return struct.get(w$LAYOUT, w$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * pfloat *w
     * }
     */
    public static void w(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(w$LAYOUT, w$OFFSET, fieldValue);
    }

    private static final AddressLayout v$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("v"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * pfloat *v
     * }
     */
    public static final AddressLayout v$layout() {
        return v$LAYOUT;
    }

    private static final long v$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * pfloat *v
     * }
     */
    public static final long v$offset() {
        return v$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * pfloat *v
     * }
     */
    public static MemorySegment v(MemorySegment struct) {
        return struct.get(v$LAYOUT, v$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * pfloat *v
     * }
     */
    public static void v(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(v$LAYOUT, v$OFFSET, fieldValue);
    }

    private static final AddressLayout kkt_idx$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("kkt_idx"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * idxint *kkt_idx
     * }
     */
    public static final AddressLayout kkt_idx$layout() {
        return kkt_idx$LAYOUT;
    }

    private static final long kkt_idx$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * idxint *kkt_idx
     * }
     */
    public static final long kkt_idx$offset() {
        return kkt_idx$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * idxint *kkt_idx
     * }
     */
    public static MemorySegment kkt_idx(MemorySegment struct) {
        return struct.get(kkt_idx$LAYOUT, kkt_idx$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * idxint *kkt_idx
     * }
     */
    public static void kkt_idx(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(kkt_idx$LAYOUT, kkt_idx$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

