package com.lucky.yogurt.util;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Collection;

import com.lucky.yogurt.Buffer;

public final class Charset {

	/**
     * System charset.
     */
    public static final Charset SYSTEM = new Charset(System
            .getProperty("file.encoding"));

    /**
     * UTF-8 charset.
     */
    public static final Charset UTF8 = new Charset("UTF-8");

    // Charset is thread-safe
    private final java.nio.charset.Charset charset;

    // CharsetEncoder and CharsetDecoder are not thread-safe
    private final ThreadLocal<CharsetEncoder> encoderCache = new ThreadLocal<CharsetEncoder>();
    private final ThreadLocal<CharsetDecoder> decoderCache = new ThreadLocal<CharsetDecoder>();

    private static Object getReference(ThreadLocal threadLocal) {
        SoftReference reference = (SoftReference) threadLocal.get();
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    private static void setReference(ThreadLocal threadLocal, Object object) {
        threadLocal.set(new SoftReference(object));
    }

    /**
     * Construct charset with charset name.
     * 
     * @param charsetName
     *            charset name
     */
    public Charset(String charsetName) {
        charset = java.nio.charset.Charset.forName(charsetName);
    }

    /**
     * Get charset name.
     * 
     * @return charset name
     */
    public String getCharsetName() {
        return charset.name();
    }

    private CharsetEncoder getEncoder() {
        CharsetEncoder encoder = (CharsetEncoder) getReference(encoderCache);
        if (encoder == null) {
            encoder = charset.newEncoder().onMalformedInput(
                    CodingErrorAction.REPLACE).onUnmappableCharacter(
                    CodingErrorAction.REPLACE);
            setReference(encoderCache, encoder);
        }
        return encoder;
    }

    private CharsetDecoder getDecoder() {
        CharsetDecoder decoder = (CharsetDecoder) getReference(decoderCache);
        if (decoder == null) {
            decoder = charset.newDecoder().onMalformedInput(
                    CodingErrorAction.REPLACE).onUnmappableCharacter(
                    CodingErrorAction.REPLACE);
            setReference(decoderCache, decoder);
        }
        return decoder;
    }

    /**
     * Encode char sequence to byte array.
     * 
     * @param sequence
     *            char sequence
     * @return the encoded byte array
     */
    public byte[] encodeToArray(CharSequence sequence) {
        ByteBuffer buffer = encode(sequence);
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    /**
     * Encode char sequence to byte buffer.
     * 
     * @param sequence
     *            the char sequence
     * @return the encoded byte buffer
     */
    public ByteBuffer encode(CharSequence sequence) {
        CharBuffer buffer = CharBuffer.wrap(sequence);
        CharsetEncoder encoder = getEncoder();

        int n = 0;
        if (buffer.remaining() > 0) {
            n = (int) (buffer.remaining() * encoder.averageBytesPerChar());
            if (n == 0) {
                n = (int) (buffer.remaining() * encoder.maxBytesPerChar());
            }
        }
        ByteBuffer result = ByteBuffer.allocate(n); // judge result length
        if (n == 0) {
            return result;
        }

        encoder.reset();
        while (true) {
            CoderResult cr = buffer.hasRemaining() ? encoder.encode(buffer,
                    result, true) : encoder.flush(result);
            if (cr.isUnderflow()) {
                break;
            } else if (cr.isOverflow()) {
                n *= 2;
                result.flip();
                result = ByteBuffer.allocate(n).put(result);
                continue;
            }
        }
        result.flip();
        return result;
    }

    /**
     * Encode char sequence to byte buffer array. Every encoded byte buffer's
     * capacity will equals or less than bufferCapcity, and won't contain parts
     * of a char.
     * 
     * @param sequence
     *            the char sequence
     * @param bufferCapcity
     *            the encoded byte buffer's max capacity
     * @return the encoded byte buffer array
     * @throws IllegalArgumentException
     */
    public ByteBuffer[] encode(CharSequence sequence, int bufferCapcity) {
        CharBuffer buffer = CharBuffer.wrap(sequence);
        CharsetEncoder encoder = getEncoder();
        encoder.reset();

        Collection<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        while (true) {
            ByteBuffer out = ByteBuffer.allocate(bufferCapcity);
            CoderResult cr = encoder.encode(buffer, out, true);
            if (cr.isUnderflow()) {
                encoder.flush(out);
                out.flip();
                buffers.add(out);
                break;
            }
            if (cr.isOverflow()) {
                if (out.position() == 0) {
                    // can't encode this char, bufferCapcity too small
                    throw new IllegalArgumentException(
                            "buffer capacity too small");
                }
                out.flip();
                buffers.add(out);
                continue;
            }
        }
        return (ByteBuffer[]) buffers.toArray(new ByteBuffer[0]);
    }

    /**
     * Decode byte array to string.
     * 
     * @param b
     *            byte array
     * @return the decoded string
     */
    public String decode(byte[] b) {
        return decode(ByteBuffer.wrap(b));
    }

    /**
     * Decode buffer to string.
     * 
     * @param buffer
     *            the buffer
     * @return the decoded string
     */
    public String decode(Buffer buffer) {
        return decode(buffer.asByteBuffer());
    }

    /**
     * Decode byte buffer to string.
     * 
     * @param buffer
     *            the byte buffer
     * @return the decoded string
     */
    public String decode(ByteBuffer buffer) {
        CharsetDecoder decoder = getDecoder();

        int n = 0;
        if (buffer.remaining() > 0) {
            n = (int) (buffer.remaining() * decoder.averageCharsPerByte());
            if (n == 0) {
                n = (int) (buffer.remaining() * decoder.maxCharsPerByte());
            }
        }
        if (n == 0) {
            return "";
        }
        CharBuffer result = CharBuffer.allocate(n);

        decoder.reset();
        while (true) {
            CoderResult cr = buffer.hasRemaining() ? decoder.decode(buffer,
                    result, true) : decoder.flush(result);
            if (cr.isUnderflow()) {
                break;
            } else if (cr.isOverflow()) {
                n *= 2;
                result.flip();
                result = CharBuffer.allocate(n).put(result);
                continue;
            }
        }
        result.flip();
        return result.toString();
    }

    /**
     * charset cache.
     */
    private static ThreadLocal charsetCache = new ThreadLocal();

    private static Charset getCharset(String charsetName) {
        Charset charset = (Charset) getReference(charsetCache);
        if (charset == null
                || !charset.getCharsetName().equalsIgnoreCase(charsetName)) {
            charset = new Charset(charsetName);
            setReference(charsetCache, charset);
        }
        return charset;
    }

    /**
     * Encode char sequence to byte buffer.
     * 
     * @param charsetName
     *            charset name
     * @param sequence
     *            the char sequence
     * @return the encoded byte buffer
     */
    public static ByteBuffer encode(String charsetName, CharSequence sequence) {
        return getCharset(charsetName).encode(sequence);
    }

    /**
     * Decode byte buffer to string.
     * 
     * @param charsetName
     *            charset name
     * @param buffer
     *            the byte buffer
     * @return the decoded string
     */
    public static String decode(String charsetName, ByteBuffer buffer) {
        return getCharset(charsetName).decode(buffer);
    }
}
