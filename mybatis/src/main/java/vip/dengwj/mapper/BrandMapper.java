package vip.dengwj.mapper;

import org.apache.ibatis.annotations.Param;
import vip.dengwj.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /**
     * 获取所有
     */
    List<Brand> selectAll();

    /**
     * 查询详情
     */
    Brand selectById(Integer id);

    /**
     * 条件查询
     */
    //List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);
    //List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map<String, Object> map);

    /**
     * 动态单条件查询
     */
    List<Brand> selectByConditionSingle(Brand brand);

    /**
     * 添加
     */
    void add(Brand brand);

    /**
     * 更新
     */
    int updateById(Brand brand);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 删除多个
     */
    int deleteByIds(@Param("ids") Integer[] ids);
}
