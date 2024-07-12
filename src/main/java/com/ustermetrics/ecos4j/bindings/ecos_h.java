// Generated by jextract

package com.ustermetrics.ecos4j.bindings;

import org.scijava.nativelib.NativeLoader;

import java.io.IOException;
import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

public class ecos_h {

    ecos_h() {
        // Should not be called directly
    }

    static {
        try {
            NativeLoader.loadLibrary("ecos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final Arena LIBRARY_ARENA = Arena.ofAuto();
    static final boolean TRACE_DOWNCALLS = Boolean.getBoolean("jextract.trace.downcalls");

    static void traceDowncall(String name, Object... args) {
         String traceArgs = Arrays.stream(args)
                       .map(Object::toString)
                       .collect(Collectors.joining(", "));
         System.out.printf("%s(%s)\n", name, traceArgs);
    }

    static MemorySegment findOrThrow(String symbol) {
        return SYMBOL_LOOKUP.find(symbol)
            .orElseThrow(() -> new UnsatisfiedLinkError("unresolved symbol: " + symbol));
    }

    static MethodHandle upcallHandle(Class<?> fi, String name, FunctionDescriptor fdesc) {
        try {
            return MethodHandles.lookup().findVirtual(fi, name, fdesc.toMethodType());
        } catch (ReflectiveOperationException ex) {
            throw new AssertionError(ex);
        }
    }

    static MemoryLayout align(MemoryLayout layout, long align) {
        return switch (layout) {
            case PaddingLayout p -> p;
            case ValueLayout v -> v.withByteAlignment(align);
            case GroupLayout g -> {
                MemoryLayout[] alignedMembers = g.memberLayouts().stream()
                        .map(m -> align(m, align)).toArray(MemoryLayout[]::new);
                yield g instanceof StructLayout ?
                        MemoryLayout.structLayout(alignedMembers) : MemoryLayout.unionLayout(alignedMembers);
            }
            case SequenceLayout s -> MemoryLayout.sequenceLayout(s.elementCount(), align(s.elementLayout(), align));
        };
    }

    static final SymbolLookup SYMBOL_LOOKUP = SymbolLookup.loaderLookup()
            .or(Linker.nativeLinker().defaultLookup());

    public static final ValueLayout.OfBoolean C_BOOL = ValueLayout.JAVA_BOOLEAN;
    public static final ValueLayout.OfByte C_CHAR = ValueLayout.JAVA_BYTE;
    public static final ValueLayout.OfShort C_SHORT = ValueLayout.JAVA_SHORT;
    public static final ValueLayout.OfInt C_INT = ValueLayout.JAVA_INT;
    public static final ValueLayout.OfLong C_LONG_LONG = ValueLayout.JAVA_LONG;
    public static final ValueLayout.OfFloat C_FLOAT = ValueLayout.JAVA_FLOAT;
    public static final ValueLayout.OfDouble C_DOUBLE = ValueLayout.JAVA_DOUBLE;
    public static final AddressLayout C_POINTER = ValueLayout.ADDRESS
            .withTargetLayout(MemoryLayout.sequenceLayout(java.lang.Long.MAX_VALUE, JAVA_BYTE));

    private static class fflush {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            ecos_h.C_INT,
            ecos_h.C_POINTER
        );

        public static final MemorySegment ADDR = ecos_h.findOrThrow("fflush");

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * extern int fflush(FILE *__stream)
     * }
     */
    public static FunctionDescriptor fflush$descriptor() {
        return fflush.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * extern int fflush(FILE *__stream)
     * }
     */
    public static MethodHandle fflush$handle() {
        return fflush.HANDLE;
    }

    /**
     * Address for:
     * {@snippet lang=c :
     * extern int fflush(FILE *__stream)
     * }
     */
    public static MemorySegment fflush$address() {
        return fflush.ADDR;
    }

    /**
     * {@snippet lang=c :
     * extern int fflush(FILE *__stream)
     * }
     */
    public static int fflush(MemorySegment __stream) {
        var mh$ = fflush.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("fflush", __stream);
            }
            return (int)mh$.invokeExact(__stream);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class ECOS_setup {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            ecos_h.C_POINTER,
            ecos_h.C_LONG_LONG,
            ecos_h.C_LONG_LONG,
            ecos_h.C_LONG_LONG,
            ecos_h.C_LONG_LONG,
            ecos_h.C_LONG_LONG,
            ecos_h.C_POINTER,
            ecos_h.C_LONG_LONG,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER,
            ecos_h.C_POINTER
        );

        public static final MemorySegment ADDR = ecos_h.findOrThrow("ECOS_setup");

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * pwork *ECOS_setup(idxint n, idxint m, idxint p, idxint l, idxint ncones, idxint *q, idxint nex, pfloat *Gpr, idxint *Gjc, idxint *Gir, pfloat *Apr, idxint *Ajc, idxint *Air, pfloat *c, pfloat *h, pfloat *b)
     * }
     */
    public static FunctionDescriptor ECOS_setup$descriptor() {
        return ECOS_setup.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * pwork *ECOS_setup(idxint n, idxint m, idxint p, idxint l, idxint ncones, idxint *q, idxint nex, pfloat *Gpr, idxint *Gjc, idxint *Gir, pfloat *Apr, idxint *Ajc, idxint *Air, pfloat *c, pfloat *h, pfloat *b)
     * }
     */
    public static MethodHandle ECOS_setup$handle() {
        return ECOS_setup.HANDLE;
    }

    /**
     * Address for:
     * {@snippet lang=c :
     * pwork *ECOS_setup(idxint n, idxint m, idxint p, idxint l, idxint ncones, idxint *q, idxint nex, pfloat *Gpr, idxint *Gjc, idxint *Gir, pfloat *Apr, idxint *Ajc, idxint *Air, pfloat *c, pfloat *h, pfloat *b)
     * }
     */
    public static MemorySegment ECOS_setup$address() {
        return ECOS_setup.ADDR;
    }

    /**
     * {@snippet lang=c :
     * pwork *ECOS_setup(idxint n, idxint m, idxint p, idxint l, idxint ncones, idxint *q, idxint nex, pfloat *Gpr, idxint *Gjc, idxint *Gir, pfloat *Apr, idxint *Ajc, idxint *Air, pfloat *c, pfloat *h, pfloat *b)
     * }
     */
    public static MemorySegment ECOS_setup(long n, long m, long p, long l, long ncones, MemorySegment q, long nex, MemorySegment Gpr, MemorySegment Gjc, MemorySegment Gir, MemorySegment Apr, MemorySegment Ajc, MemorySegment Air, MemorySegment c, MemorySegment h, MemorySegment b) {
        var mh$ = ECOS_setup.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("ECOS_setup", n, m, p, l, ncones, q, nex, Gpr, Gjc, Gir, Apr, Ajc, Air, c, h, b);
            }
            return (MemorySegment)mh$.invokeExact(n, m, p, l, ncones, q, nex, Gpr, Gjc, Gir, Apr, Ajc, Air, c, h, b);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class ECOS_solve {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            ecos_h.C_LONG_LONG,
            ecos_h.C_POINTER
        );

        public static final MemorySegment ADDR = ecos_h.findOrThrow("ECOS_solve");

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * idxint ECOS_solve(pwork *w)
     * }
     */
    public static FunctionDescriptor ECOS_solve$descriptor() {
        return ECOS_solve.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * idxint ECOS_solve(pwork *w)
     * }
     */
    public static MethodHandle ECOS_solve$handle() {
        return ECOS_solve.HANDLE;
    }

    /**
     * Address for:
     * {@snippet lang=c :
     * idxint ECOS_solve(pwork *w)
     * }
     */
    public static MemorySegment ECOS_solve$address() {
        return ECOS_solve.ADDR;
    }

    /**
     * {@snippet lang=c :
     * idxint ECOS_solve(pwork *w)
     * }
     */
    public static long ECOS_solve(MemorySegment w) {
        var mh$ = ECOS_solve.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("ECOS_solve", w);
            }
            return (long)mh$.invokeExact(w);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class ECOS_cleanup {
        public static final FunctionDescriptor DESC = FunctionDescriptor.ofVoid(
            ecos_h.C_POINTER,
            ecos_h.C_LONG_LONG
        );

        public static final MemorySegment ADDR = ecos_h.findOrThrow("ECOS_cleanup");

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * void ECOS_cleanup(pwork *w, idxint keepvars)
     * }
     */
    public static FunctionDescriptor ECOS_cleanup$descriptor() {
        return ECOS_cleanup.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * void ECOS_cleanup(pwork *w, idxint keepvars)
     * }
     */
    public static MethodHandle ECOS_cleanup$handle() {
        return ECOS_cleanup.HANDLE;
    }

    /**
     * Address for:
     * {@snippet lang=c :
     * void ECOS_cleanup(pwork *w, idxint keepvars)
     * }
     */
    public static MemorySegment ECOS_cleanup$address() {
        return ECOS_cleanup.ADDR;
    }

    /**
     * {@snippet lang=c :
     * void ECOS_cleanup(pwork *w, idxint keepvars)
     * }
     */
    public static void ECOS_cleanup(MemorySegment w, long keepvars) {
        var mh$ = ECOS_cleanup.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("ECOS_cleanup", w, keepvars);
            }
            mh$.invokeExact(w, keepvars);
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }

    private static class ECOS_ver {
        public static final FunctionDescriptor DESC = FunctionDescriptor.of(
            ecos_h.C_POINTER    );

        public static final MemorySegment ADDR = ecos_h.findOrThrow("ECOS_ver");

        public static final MethodHandle HANDLE = Linker.nativeLinker().downcallHandle(ADDR, DESC);
    }

    /**
     * Function descriptor for:
     * {@snippet lang=c :
     * const char *ECOS_ver()
     * }
     */
    public static FunctionDescriptor ECOS_ver$descriptor() {
        return ECOS_ver.DESC;
    }

    /**
     * Downcall method handle for:
     * {@snippet lang=c :
     * const char *ECOS_ver()
     * }
     */
    public static MethodHandle ECOS_ver$handle() {
        return ECOS_ver.HANDLE;
    }

    /**
     * Address for:
     * {@snippet lang=c :
     * const char *ECOS_ver()
     * }
     */
    public static MemorySegment ECOS_ver$address() {
        return ECOS_ver.ADDR;
    }

    /**
     * {@snippet lang=c :
     * const char *ECOS_ver()
     * }
     */
    public static MemorySegment ECOS_ver() {
        var mh$ = ECOS_ver.HANDLE;
        try {
            if (TRACE_DOWNCALLS) {
                traceDowncall("ECOS_ver");
            }
            return (MemorySegment)mh$.invokeExact();
        } catch (Throwable ex$) {
           throw new AssertionError("should not reach here", ex$);
        }
    }
    private static final int MAXIT = (int)100L;
    /**
     * {@snippet lang=c :
     * #define MAXIT 100
     * }
     */
    public static int MAXIT() {
        return MAXIT;
    }
    private static final double FEASTOL = 1.0E-8d;
    /**
     * {@snippet lang=c :
     * #define FEASTOL 1.0E-8
     * }
     */
    public static double FEASTOL() {
        return FEASTOL;
    }
    private static final double ABSTOL = 1.0E-8d;
    /**
     * {@snippet lang=c :
     * #define ABSTOL 1.0E-8
     * }
     */
    public static double ABSTOL() {
        return ABSTOL;
    }
    private static final double RELTOL = 1.0E-8d;
    /**
     * {@snippet lang=c :
     * #define RELTOL 1.0E-8
     * }
     */
    public static double RELTOL() {
        return RELTOL;
    }
    private static final double FTOL_INACC = 1.0E-4d;
    /**
     * {@snippet lang=c :
     * #define FTOL_INACC 1.0E-4
     * }
     */
    public static double FTOL_INACC() {
        return FTOL_INACC;
    }
    private static final double ATOL_INACC = 5.0E-5d;
    /**
     * {@snippet lang=c :
     * #define ATOL_INACC 5.0E-5
     * }
     */
    public static double ATOL_INACC() {
        return ATOL_INACC;
    }
    private static final double RTOL_INACC = 5.0E-5d;
    /**
     * {@snippet lang=c :
     * #define RTOL_INACC 5.0E-5
     * }
     */
    public static double RTOL_INACC() {
        return RTOL_INACC;
    }
    private static final int VERBOSE = (int)1L;
    /**
     * {@snippet lang=c :
     * #define VERBOSE 1
     * }
     */
    public static int VERBOSE() {
        return VERBOSE;
    }
    private static final int NITREF = (int)9L;
    /**
     * {@snippet lang=c :
     * #define NITREF 9
     * }
     */
    public static int NITREF() {
        return NITREF;
    }
    private static final int ECOS_OPTIMAL = (int)0L;
    /**
     * {@snippet lang=c :
     * #define ECOS_OPTIMAL 0
     * }
     */
    public static int ECOS_OPTIMAL() {
        return ECOS_OPTIMAL;
    }
    private static final int ECOS_PINF = (int)1L;
    /**
     * {@snippet lang=c :
     * #define ECOS_PINF 1
     * }
     */
    public static int ECOS_PINF() {
        return ECOS_PINF;
    }
    private static final int ECOS_DINF = (int)2L;
    /**
     * {@snippet lang=c :
     * #define ECOS_DINF 2
     * }
     */
    public static int ECOS_DINF() {
        return ECOS_DINF;
    }
    private static final int ECOS_INACC_OFFSET = (int)10L;
    /**
     * {@snippet lang=c :
     * #define ECOS_INACC_OFFSET 10
     * }
     */
    public static int ECOS_INACC_OFFSET() {
        return ECOS_INACC_OFFSET;
    }
    private static final int ECOS_MAXIT = (int)-1L;
    /**
     * {@snippet lang=c :
     * #define ECOS_MAXIT -1
     * }
     */
    public static int ECOS_MAXIT() {
        return ECOS_MAXIT;
    }
    private static final int ECOS_NUMERICS = (int)-2L;
    /**
     * {@snippet lang=c :
     * #define ECOS_NUMERICS -2
     * }
     */
    public static int ECOS_NUMERICS() {
        return ECOS_NUMERICS;
    }
    private static final int ECOS_OUTCONE = (int)-3L;
    /**
     * {@snippet lang=c :
     * #define ECOS_OUTCONE -3
     * }
     */
    public static int ECOS_OUTCONE() {
        return ECOS_OUTCONE;
    }
    private static final int ECOS_SIGINT = (int)-4L;
    /**
     * {@snippet lang=c :
     * #define ECOS_SIGINT -4
     * }
     */
    public static int ECOS_SIGINT() {
        return ECOS_SIGINT;
    }
    private static final int ECOS_FATAL = (int)-7L;
    /**
     * {@snippet lang=c :
     * #define ECOS_FATAL -7
     * }
     */
    public static int ECOS_FATAL() {
        return ECOS_FATAL;
    }
}

