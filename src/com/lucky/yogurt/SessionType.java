package com.lucky.yogurt;

/**
 * Session 类型.
 * @author Administrator
 *
 */
public final class SessionType {

	/**
     * TCP
     */
    public static final SessionType TCP = new SessionType("TCP");

    /**
     * UDP
     */
    public static final SessionType UDP = new SessionType("UDP");

    /**
     * Pipe
     */
    public static final SessionType PIPE = new SessionType("Pipe");

    /**
     * File
     */
    public static final SessionType FILE = new SessionType("File");

    /**
     * Unknown
     */
    public static final SessionType UNKNOWN = new SessionType("Unknown");

    private final String type;

    private SessionType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
