package com.lucky.yogurt;

public interface SessionHandler {
	/**
     * Session have started.
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionStarted(Session session) throws Exception;

    /**
     * Session have closed.
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionClosed(Session session) throws Exception;

    /**
     * Session timeout, but not closed.
     * 
     * @param session
     *            session
     * @throws Exception
     *             any exception
     */
    void sessionTimeout(Session session) throws Exception;

    /**
     * Session received a object which is decoded by <code>PacketDecoder</code>.
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
     * Session sent a object.
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
     * Session caught a exception.
     * 
     * @param session
     *            session
     * @param cause
     *            exception
     */
    void exceptionCaught(Session session, Throwable cause);
}
