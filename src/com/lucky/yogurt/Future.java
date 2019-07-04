package com.lucky.yogurt;

/**
 * Future表示异步任务的结果
 * @author Administrator
 *
 */
public interface Future {

	/**
     * 获取与future相关的session
     * 
     * @return session
     */
	Session getSession();
	
	/**
     * 必要时等待任务完成.
     * 警告：阻塞操作可能导致吞吐量降低.
     * 
     * @return is succeeded
     */
	boolean complete();
	
	/**
	 * 如有必要，请等待任务完成或超时期限已过期
	 * 警告：阻塞操作可能导致吞吐量降低.
	 * @param timeout 
	 * 				超时期限
	 * @return is completed
	 */
	boolean complete(int timeout);
	
	/**
     * 如果任务完成，则返回true.
     * 
     * @return is completed
     */
	boolean isCompleted();
	
	/**
     * 如果任务成功完成，则返回true.
     * 
     * @return is succeeded
     */
	boolean isSucceeded();
	
	/**
     * 添加任务监听器.
     * 
     * @param listener
     *            任务监听器
     */
	void addListener(FutureListener listener);
	
	/**
     * 删除任务监听器.
     * 
     * @param listener
     *            任务监听器
     */
	void removeListener(FutureListener listener);
	
	
}
