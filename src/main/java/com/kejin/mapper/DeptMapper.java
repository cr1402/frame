package com.kejin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kejin.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 删除用户部门关联表中的部门
     * @param deptId
     */
    void deleteDept(@Param("deptId") Integer deptId);

}
