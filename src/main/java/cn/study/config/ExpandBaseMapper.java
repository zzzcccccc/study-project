package cn.study.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * 真正实现批量插入的拓展接口
 * @param <T>
 */
public interface ExpandBaseMapper<T> extends BaseMapper<T> {


    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}