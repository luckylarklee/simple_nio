package com.lucky.yogurt;

/**
 * Session会话过滤器，过滤Session会话事件
 *
 */
public interface SessionFilter {

	/**
     * Session 会话已经开始.
     * 
     * @param filterChain
     *            session filter chain
     * @throws Exception
     *             any exception
     */
    void sessionStarted(SessionFilterChain filterChain) throws Exception;

    /**
     * Session 会话已经关闭.
     * 
     * @param filterChain
     *            session filter chain
     * @throws Exception
     *             any exception
     */
    void sessionClosed(SessionFilterChain filterChain) throws Exception;

    /**
     * Session 会话超时，但未关闭.
     * 
     * @param filterChain
     *            session filter chain
     * @throws Exception
     *             any exception
     */
    void sessionTimeout(SessionFilterChain filterChain) throws Exception;

    /**
     * Session 收到了一个数据包.
     * 
     * @param filterChain
     *            session filter chain
     * @param packet
     *            the received packet
     * @throws Exception
     *             any exception
     */
    void packetReceived(SessionFilterChain filterChain, Packet packet)
            throws Exception;

    /**
     * Session 收到了一个被 <code>PacketDecoder</code>解码的对象.
     * 
     * @param filterChain
     *            session filter chain
     * @param obj
     *            object
     * @throws Exception
     *             any exception
     */
    void objectReceived(SessionFilterChain filterChain, Object obj)
            throws Exception;

    /**
     * 发送数据包前过滤, 事件派发将按相反顺序发送.
     * 
     * @param filterChain
     *            session filter chain
     * @param packet
     *            send packet
     * @throws Exception
     *             any exception
     */
    void packetSend(SessionFilterChain filterChain, Packet packet)
            throws Exception;

    /**
     * Session 发送一个数据包. 事件派发将按相反顺序发送. 
     * 数据包的位置不会更新.
     * 
     * @param filterChain
     *            session filter chain
     * @param packet
     *            the sent packet
     * @throws Exception
     *             any exception
     */
    void packetSent(SessionFilterChain filterChain, Packet packet)
            throws Exception;

    /**
     * Session 发送一个对象. 事件派发将按相反顺序发送.
     * 
     * @param filterChain
     *            session filter chain
     * @param obj
     *            the sent object
     * @throws Exception
     *             any exception
     */
    void objectSent(SessionFilterChain filterChain, Object obj)
            throws Exception;

    /**
     * Session 捕获异常.
     * 
     * @param filterChain
     *            session filter chain
     * @param cause
     *            exception
     */
    void exceptionCaught(SessionFilterChain filterChain, Throwable cause);
}
