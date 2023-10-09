package com.kejin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kejin.entity.Dept;
import com.kejin.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 新增部门
     * @param dept
     * @return
     */
    Result addDept(Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    Result updateDept(Dept dept);

    /**
     * 删除部门
     * @param dept
     * @return
     */
    Result deleteDept(Dept dept);

    /**
     * 查询所有部门和其子部门
     * @return
     */
    Result getDeptList();

    /**
     * 根据上级部门id获取所有子部门id
     * @param deptId 上级部门id
     * @return
     */
    List<String> getChildDeptIds(Integer deptId);
}
