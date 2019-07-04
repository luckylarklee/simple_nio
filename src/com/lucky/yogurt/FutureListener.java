package com.lucky.yogurt;

/**
 * <code>Future</code> 任务监听器.
 * @author Administrator
 *
 */
public interface FutureListener {

	/**
     * Future 任务完成.
     * 
     * @param future
     *            完成的 future
     * @throws Exception
     *             any exception
     */
    void futureCompleted(Future future) throws Exception;
}
