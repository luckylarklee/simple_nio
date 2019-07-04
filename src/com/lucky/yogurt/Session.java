package com.lucky.yogurt;

import java.net.SocketAddress;
import java.util.Map;

/**
 * Session 表示网络连接会话
 * @author Administrator
 *
 */
public interface Session {

	/**
     * 获取 session 会话类型
     * 
     * @return session type
     */
    SessionType getSessionType();

    /**
     * 设置会话将连接到的远程地址.
     * 
     * @param address
     *            remote address
     * @throws IllegalStateException
     */
    void setRemoteAddress(SocketAddress address);

    /**
     * 获取会话连接的远程地址.
     * 
     * @return the remote address
     */
    SocketAddress getRemoteAddress();

    /**
     * 设置会话绑定的本地地址.
     * 
     * @param address
     *            the local address
     * @throws IllegalStateException
     */
    void setLocalAddress(SocketAddress address);

    /**
     * 获取会话绑定的本地地址.
     * 
     * @return the local address
     */
    SocketAddress getLocalAddress();

    /**
     * 设置数据包解码器.
     * 
     * @param decoder
     *            packet decoder
     */
    void setPacketDecoder(PacketDecoder decoder);

    /**
     * 获取数据包解码器.
     * 
     * @return packet decoder
     */
    PacketDecoder getPacketDecoder();

    /**
     * 设置数据包编码器.
     * 
     * @param encoder
     *            packet encoder
     */
    void setPacketEncoder(PacketEncoder encoder);

    /**
     * 获取数据包编码器.
     * 
     * @return packet encoder
     */
    PacketEncoder getPacketEncoder();

    /**
     * Get all session attributs. The returned map is unmodifiable.
     * 
     * @return all attributes
     */
    Map getAttributes();

    /**
     * Returns <tt>true</tt> if the session contains an attribute for the
     * specified key.
     * 
     * @param key
     *            key
     * @return <tt>true</tt> if the session contains an attribute for the
     *         specified key
     */
    boolean containsAttribute(Object key);

    /**
     * Get the session attribute mapped with the specified key.
     * 
     * @param key
     *            key
     * @return attribute
     */
    Object getAttribute(Object key);

    /**
     * Set session attribute.
     * 
     * @param key
     *            key
     * @param attribute
     *            attribute
     */
    void setAttribute(Object key, Object attribute);

    /**
     * Removes the attribute for this key if it is present.
     * 
     * @param key
     *            key
     */
    void removeAttribute(Object key);

    /**
     * 设置Session会话超时，以毫秒为单位.
     * 
     * @param timeout
     *            session timeout
     */
    void setSessionTimeout(int timeout);

    /**
     * 获取Session会话超时，以毫秒为单位.
     * 
     * @return session timeout
     */
    int getSessionTimeout();

    /**
     * 获取Session会话过滤器链.
     * 
     * @param reversed
     *            some events like xxxSend/xxxSent need to be dispatched in
     *            reversed order
     * @return session filter chain
     */
    SessionFilterChain getSessionFilterChain(boolean reversed);

    /**
     * 添加Session会话过滤器.
     * 
     * @param filter
     *            session filter
     */
    void addSessionFilter(SessionFilter filter);

    /**
     * 添加Session会话过滤器.
     * 
     * @param index
     *            index
     * @param filter
     *            session filter
     */
    void addSessionFilter(int index, SessionFilter filter);

    /**
     * 删除Session会话过滤器.
     * 
     * @param filter
     *            session filter
     */
    void removeSessionFilter(SessionFilter filter);

    /**
     * 获取Session会话过滤器.
     * 
     * @param index
     *            index
     * @return session filter
     */
    SessionFilter getSessionFilter(int index);

    /**
     * 获取所有的Session会话过滤器.
     * 
     * @return session filters
     */
    SessionFilter[] getSessionFilters();

    /**
     * 设置Sesison会话处理器.
     * 
     * @param handler
     *            session handler
     */
    void setSessionHandler(SessionHandler handler);

    /**
     * 获取Session会话处理器.
     * 
     * @return session handler
     */
    SessionHandler getSessionHandler();

    /**
     * Session 会话已经开始.
     * 
     * @return session have started
     */
    boolean isStarted();

    /**
     * 开始 session.
     * 
     * @return start future
     */
    Future start();

    /**
     * 关闭 session.
     * 
     * @return close future
     */
    Future close();

    /**
     * Send packet with normal priority. This method is a shorthand for
     * flush(packet, 0).
     * 
     * @param packet
     *            send packet
     * @return send future
     */
    Future flush(Packet packet);

    /**
     * Send packet with specified priority. Integer.MAX_VALUE indicate the max
     * priority, Integer.MIN_VALUE indicate the min priority and 0 indicate the
     * normal priority.
     * 
     * @param packet
     *            send packet
     * @param priority
     *            send priority
     * @return send future
     */
    Future flush(Packet packet, int priority);

    /**
     * Send object with normal priority. The object will be encoded to
     * <code>Packet</code> use associated <code>PacketEncoder</code>. This
     * method is a shorthand for send(obj, 0).
     * 
     * @param obj
     *            send object
     * @return send future
     */
    Future send(Object obj);

    /**
     * Send object with specified priority. Integer.MAX_VALUE indicate the max
     * priority, Integer.MIN_VALUE indicate the min priority and 0 indicate the
     * normal priority. The object will be encoded to <code>Packet</code> use
     * associated <code>PacketEncoder</code>.
     * 
     * @param obj
     *            send object
     * @param priority
     *            send priority
     * @return send future
     */
    Future send(Object obj, int priority);
}
