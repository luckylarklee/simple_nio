package com.lucky.yogurt;

/**
 * <code> Session </ code>会话处理器
 * @author Administrator
 *
 */
public interface SessionHandler {
	/**
     * Session 会话开始
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionStarted(Session session) throws Exception;

    /**
     * Session 会话关闭
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionClosed(Session session) throws Exception;

    /**
     * Session 会话超时 ，但是没有关闭
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionTimeout(Session session) throws Exception;

    /**
     * Session 接收到一个被 <code>PacketDecoder</code>解码的Object.
     * 
     * @param session
     *            session
     * @param obj
     *            object
     * @throws Exception
     *             any exception
     */
    void objectReceived(Session session, Object obj) throws Exception;

    /**
     * Session 发送一个Object
     * 
     * @param session
     *            session
     * @param obj
     *            object
     * @throws Exception
     *             any exception
     */
    void objectSent(Session session, Object obj) throws Exception;

    /**
     * Session 会话捕获的异常.
     * 
     * @param session
     *            session
     * @param cause
     *            exception
     */
    void exceptionCaught(Session session, Throwable cause);
}
