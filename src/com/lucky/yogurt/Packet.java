package com.lucky.yogurt;

import java.net.SocketAddress;

/**
 * 数据包（包含内容和 地址）
 * @author Administrator
 *
 */
public interface Packet {

	/**
     * 获取数据包的socket地址
     * 
     * @return the socket address
     */
    SocketAddress getAddress();

    /**
     * 获取数据包的内容
     * 
     * @return content
     */
    Buffer getContent();
}
