package cn.ytmj.service.impl;

import cn.ytmj.dao.ProvinceDao;
import cn.ytmj.dao.impl.ProvinceDaoImpl;
import cn.ytmj.domain.Province;
import cn.ytmj.jedis.util.JedisUtils;
import cn.ytmj.service.ProvinceSevince;
import cn.ytmj.util.JDBCUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-24 23:44
 */
public class ProvinceServinceImpl implements ProvinceSevince {
    ProvinceDao provinceDao = new ProvinceDaoImpl();

    @Override
    public List<Province> finAll() {
        return provinceDao.finAll();
    }

    /**
     * 使用redies缓存
     * @return
     */
    @Override
    public String finAllJson() {
        //从redies中查询数据
        //获取连接
        Jedis jedis = JedisUtils.getJedis();
        String province_json = jedis.get("province");
        //判断province_json是否为null
        if(province_json==null||province_json.length()==0){
            //从数据库中查询
            System.out.println("redis没数据");
            List<Province> provinces = provinceDao.finAll();
            System.out.println(provinces);
            //将list数列化为json
            ObjectMapper om = new ObjectMapper();
            try {
                province_json = om.writeValueAsString(provinces);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json数据存入redis
            jedis.set("province",province_json);
//            System.out.println(province_json);
            jedis.close();
        }else{
            System.out.println("有数据");
        }
        return province_json;
    }
}
