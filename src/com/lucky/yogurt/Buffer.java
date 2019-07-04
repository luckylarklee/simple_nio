package com.lucky.yogurt;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.lucky.yogurt.util.Charset;

/**
 * 替代 <code>java.nio.ByteBuffer</code>的Buffer.
 * <p>
 * 优点:
 * <ul>
 * <li>可缓存</li>
 * <li>增加一些实用方法, such as indexOf/putString/getString/getUnsignedXXX</li>
 * <li>可以添加自定义实现</li>
 * </ul>
 * <p>
 * 多个并发线程使用Buffer是不安全的。
 * 如果一个Buffer要由多个线程使用，
 * 则应通过适当的同步来控制对Buffer的访问。
 * 
 * @author Administrator
 */
public interface Buffer {
	/**
	 * 将此Buffer视图创建为 ByteBuffer. 
	 * 返回的ByteBuffer 的position/limit/capacity 等于当前Buffer 的 position/limit/capacity.
	 * 返回的 <code>ByteBuffer</code> 可以共享<code>Buffer</code>的内容, 具体取决于实现.
	 * 
	 * @return the byte buffer
	 */
	ByteBuffer asByteBuffer();

	/**
	 * 返回当前buffer 是否为永久的. 永久buffer无法释放.
	 * 
	 * @return is permanent
	 */
	boolean isPermanent();

	/**
	 * 设置当前buffer是永久的.永久buffer无法释放.
	 * 
	 * @param b
	 *            permanent
	 */
	void setPermanent(boolean b);

	/**
	 * 释放此buffer（非永久性）的内容.
	 */
	void release();

	/**
	 * 当前buffer的内容是否已经释放.
	 * 
	 * @return released
	 */
	boolean isReleased();

	/**
	 * 将一个字节序列写入通道.
	 * 
	 * @param channel
	 *            channel
	 * @return 写入的字节数，可能为0
	 * @throws IOException
	 *             any io exception
	 */
	int write(WritableByteChannel channel) throws IOException;

	/**
	 * 从通道读取一系列字节.
	 * 
	 * @param channel
	 *            channel
	 * @return 读取的字节数，可能为0，如果通道已到达流末尾，则为-1
	 * @throws IOException
	 *             any io exception
	 */
	int read(ReadableByteChannel channel) throws IOException;

	/**
	 * 这种方法是一种简写: position(position() + size).
	 * 
	 * @param size
	 *            skip size
	 * @return the byte buffer
	 */
	Buffer skip(int size);

	/**
	 * 将当前缓冲区转储到字符串， 帮助调试.
	 * 
	 * @return string
	 */
	String dump();

	int capacity();

	int position();

	Buffer position(int position);

	int limit();

	Buffer limit(int limit);

	Buffer mark();

	Buffer reset();

	Buffer clear();

	Buffer flip();

	Buffer rewind();

	int remaining();

	boolean hasRemaining();

	boolean isReadonly();

	boolean isBigEndian();

	Buffer setBigEndian(boolean b);

	boolean isDirect();

	Buffer compact();

	Buffer slice();

	Buffer duplicate();

	Buffer asReadOnlyBuffer();

	byte get();

	byte get(int index);

	Buffer get(byte[] dst);

	Buffer get(int index, byte[] dst);

	Buffer get(byte[] dst, int offset, int length);

	Buffer get(int index, byte[] dst, int offset, int length);

	Buffer get(ByteBuffer dst);

	Buffer get(ByteBuffer dst, int length);

	Buffer get(int index, ByteBuffer dst);

	Buffer get(int index, ByteBuffer dst, int length);

	Buffer get(Buffer dst);

	Buffer get(Buffer dst, int length);

	Buffer get(int index, Buffer dst);

	Buffer get(int index, Buffer dst, int length);

	Buffer put(byte b);

	Buffer put(int index, byte b);

	Buffer put(ByteBuffer src);

	Buffer put(ByteBuffer src, int length);

	Buffer put(int index, ByteBuffer src);

	Buffer put(int index, ByteBuffer src, int length);

	Buffer put(Buffer src);

	Buffer put(Buffer src, int length);

	Buffer put(int index, Buffer src);

	Buffer put(int index, Buffer src, int length);

	Buffer put(byte[] src);

	Buffer put(byte[] src, int offset, int length);

	Buffer put(int index, byte[] src);

	Buffer put(int index, byte[] src, int offset, int length);

	char getChar();

	Buffer putChar(char c);

	char getChar(int index);

	Buffer putChar(int index, char c);

	short getShort();

	Buffer putShort(short s);

	short getShort(int index);

	Buffer putShort(int index, short s);

	int getInt();

	Buffer putInt(int i);

	int getInt(int index);

	Buffer putInt(int index, int i);

	long getLong();

	Buffer putLong(long l);

	long getLong(int index);

	Buffer putLong(int index, long l);

	float getFloat();

	Buffer putFloat(float f);

	float getFloat(int index);

	Buffer putFloat(int index, float f);

	double getDouble();

	Buffer putDouble(double d);

	double getDouble(int index);

	Buffer putDouble(int index, double d);

	short getUnsignedByte();

	short getUnsignedByte(int index);

	Buffer putUnsignedByte(short s);

	Buffer putUnsignedByte(int index, short s);

	int getUnsignedShort();

	int getUnsignedShort(int index);

	Buffer putUnsignedShort(int i);

	Buffer putUnsignedShort(int index, int i);

	long getUnsignedInt();

	long getUnsignedInt(int index);

	Buffer putUnsignedInt(long l);

	Buffer putUnsignedInt(int index, long l);

	String getString(Charset charset, int bufferLen);

	Buffer putString(String s, Charset charset);

	String getString(int index, Charset charset, int bufferLen);

	Buffer putString(int index, String s, Charset charset);

	int indexOf(byte[] b);
}
