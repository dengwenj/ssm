package vip.dengwj.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import vip.dengwj.mapper.BrandMapper;
import vip.dengwj.pojo.Brand;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        System.out.println("brands ->ww " + brands);
        closeSqlSession(sqlSession);
    }

    @Test
    public void selectById() {
        SqlSession sqlSession = getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(1);
        System.out.println(brand);
    }

    @Test
    public void selectByCondition() {
        SqlSession sqlSession = getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 散装参数的方式
        //List<Brand> brands = brandMapper.selectByCondition(1, "小米", "小米");
        // 对象的方式
        //List<Brand> brands = brandMapper.selectByCondition(new Brand(1, "小米", "小米", 10, null, 1));

        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("companyName", "华为");
        map.put("brandName", "华为");
        // map集合参数的方式
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println("brands ->ww " + brands);
        closeSqlSession(sqlSession);
    }

    @Test
    public void selectByConditionSingle() {
        SqlSession sqlSession = getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = new Brand();
        //brand.setStatus(1);
        //brand.setBrandName("小米");
        brand.setCompanyName("小米");
        System.out.println("brand -> " + brand);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println("brands ->ww " + brands);
    }

    @Test
    public void add() {
        SqlSession sqlSession = getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = new Brand();
        brand.setBrandName("三星");
        brand.setCompanyName("三星有限公司");
        brand.setDescription("三星很好");
        brand.setOrdered(30);
        brand.setOrdered(1);
        brandMapper.add(brand);
        // 手动提交
        sqlSession.commit();
        closeSqlSession(sqlSession);
    }












    public static SqlSession getSqlSession() {
        String resource = "mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory.openSession();
    }

    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
