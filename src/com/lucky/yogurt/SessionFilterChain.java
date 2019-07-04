package com.lucky.yogurt;

/**
 * Session会话过滤器链，管理会话过滤器
 * @author Administrator
 *
 */
public interface SessionFilterChain {

	/**
     * Get session associated with the session filter chain.
     * 
     * @return session
     */
    Session getSession();

    /**
     * Session have established.
     */
    void sessionStarted();

    /**
     * Session have closed or refused.
     */
    void sessionClosed();

    /**
     * Session timeout, but not closed.
     */
    void sessionTimeout();

    /**
     * Session received a packet.
     * 
     * @param packet
     *            the received packet
     */
    void packetReceived(Packet packet);

    /**
     * Session received a object which is decoded by <code>PacketDecoder</code>.
     * 
     * @param obj
     *            object
     */
    void objectReceived(Object obj);

    /**
     * Filter before send packet.
     * 
     * @param packet
     *            send packet
     */
    void packetSend(Packet packet);

    /**
     * Session sent a packet.
     * 
     * @param packet
     *            the sent packet
     */
    void packetSent(Packet packet);

    /**
     * Session sent a object.
     * 
     * @param obj
     *            the sent object
     */
    void objectSent(Object obj);

    /**
     * Session caught a exception.
     * 
     * @param cause
     *            exception
     */
    void exceptionCaught(Throwable cause);
}
