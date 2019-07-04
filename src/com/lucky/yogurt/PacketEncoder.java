package com.lucky.yogurt;

/**
 *  数据包编码器
 * @author Administrator
 *
 */
public interface PacketEncoder {

	/**
     * 将对象编码为数据包.
     * 
     * @param session
     *            session
     * @param obj
     *            object
     * @return encoded packet
     * @throws Exception
     *             any exception
     */
    Packet encode(Session session, Object obj) throws Exception;
}
