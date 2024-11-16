package vip.dengwj.mapper;

import vip.dengwj.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    /**
     * 获取所有
     */
    List<Brand> selectAll();

    /**
     * 查询详情
     */
    Brand selectById(Integer id);
}
