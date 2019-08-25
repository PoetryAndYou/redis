package cn.ytmj.jedis;

import cn.ytmj.jedis.util.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * @author rui
 * @create 2019-08-24 17:34
 */
public class JedisTest {
    /**
     * 快速入门
     */
    @Test
    public void test1() {
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        jedis.set("username", "张三");

        //关闭连接
        jedis.close();
    }

    /*
    String
     */
    @Test
    public void test2() {
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);//如果使用空参构造，默认localhost和6379
        //操作
        //存储
        jedis.set("username", "张三");
        String username = jedis.get("username");
        System.out.println(username);
        //可以使用setex()方法指定过期时间的key value自动删除该键值对
        //关闭连接
        jedis.setex("code", 20, "hehe");//将code hehe键值对存入redies，并且20秒后自动删除
        jedis.close();
    }

    /**
     * hash
     */
    @Test
    public void test3() {
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);//如果使用空参构造，默认localhost和6379
        //操作
        //存储
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "male");
        String hget = jedis.hget("user", "name");
        Map<String, String> user = jedis.hgetAll("user");

        System.out.println(hget);
        jedis.close();
    }

    /**
     * jedis连接池
     */
    @Test
    public void test7() {
        //创建一个配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        //创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        //获取连接
        Jedis resource = jedisPool.getResource();

        resource.set("hehe", "haha");

        //关闭，归还至连接池
        resource.close();
    }

    /**
     * 测试连接池
     */
    @Test
    public void test8() {
        Jedis resource = JedisUtils.getJedis();

        resource.set("as", "haha");

        //关闭，归还至连接池
        resource.close();
    }

}
