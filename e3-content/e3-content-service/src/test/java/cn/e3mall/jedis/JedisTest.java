package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class JedisTest {
	/**
	 * 单机版
	 * <p>Title: testJedis</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testJedis() {
		Jedis jedis = new Jedis("192.168.25.128",6379);
		jedis.set("test", "my first jedis test");
		String string =jedis.get("test");
		System.out.println(string);
	}
	
	/**
	 * 集群版
	 * <p>Title: testJedisCluster</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testJedisCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.128", 7001));
		nodes.add(new HostAndPort("192.168.25.128", 7002));
		nodes.add(new HostAndPort("192.168.25.128", 7003));
		nodes.add(new HostAndPort("192.168.25.128", 7004));
		nodes.add(new HostAndPort("192.168.25.128", 7005));
		nodes.add(new HostAndPort("192.168.25.128", 7006));
		JedisCluster jedisCluster =new JedisCluster(nodes);
		jedisCluster.set("test", "123");
		String string =jedisCluster.get("test");
		System.out.println(string);
		jedisCluster.close();
	}
}
