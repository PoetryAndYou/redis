package cn.ytmj.dao.impl;

import cn.ytmj.dao.ProvinceDao;
import cn.ytmj.domain.Province;
import cn.ytmj.util.JDBCUtils;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-24 23:43
 */
public class ProvinceDaoImpl implements ProvinceDao {
    //生命对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> finAll() {
        String sql = "select * from province";
        List<Province> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return query;
    }
}
