package com.md.core.leafid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.md.core.leafid.id.IDGen;
import com.md.core.leafid.snowflake.SnowflakeIDGenImpl;

/**
 * 分布式id生成器
 * 
 * @author Minbo
 *
 */
public class MdIdsGen {

	protected static Logger logger = LoggerFactory.getLogger(MdIdsGen.class);

	private static Result result = null;
	private static IDGen idGen = null;
	private static String sIp;
	private static String sPort;

	// 美团开源的分布式id生成器
	// https://github.com/hemin1003/Leaf
	/**
	 * 与注册中心建立连接， 初始化id值
	 * 
	 * @param zkAddress
	 * @param ip
	 * @param port
	 */
	public static void init(String zkAddress, String ip, String port) {
		try {
			sIp = ip;
			sPort = port;

			logger.info("----------------");
			logger.info("初始化：分布式id生成器");
			logger.info("参数值：zkAddress=" + zkAddress + "，sIp=" + sIp + "，sPort=" + sPort);

			idGen = new SnowflakeIDGenImpl(zkAddress, Integer.valueOf(port));
			Long idLong = getId();
			if (idLong == null || idLong < 0) {
				logger.error("分布式id生成器初始化【异常】：id值为空或小于0，【id=" + idLong + "】", new RuntimeException("valueError"));
				// 初始失败，直接停止应用
				System.exit(0);
			}
			logger.info("分布式id生成器，初始化【成功】。测试id值=" + idLong);
			logger.info("----------------done");

		} catch (Exception e) {
			logger.error("分布式id生成器，初始化【异常】：" + e.getMessage(), new RuntimeException("initError"));
			// 初始失败，直接停止应用
			System.exit(0);
		}
	}

	public static Long getId() {
		result = idGen.get(sIp + ":" + sPort);
		return result.getId();
	}
}
