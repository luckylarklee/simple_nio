package com.lucky.yogurt;

/**
 *  数据包解码器
 * @author Administrator
 *
 */
public interface PacketDecoder {

	/**
     * 将数据包解码为对象. 数据包的内容是只读的, 
     * 如果方法返回null，它的位置将不会改变.
     * 
     * @param session
     *            session
     * @param packet
     *            packet
     * @return decoded object
     * @throws Exception
     *             any exception
     */
    Object decode(Session session, Packet packet) throws Exception;

}
