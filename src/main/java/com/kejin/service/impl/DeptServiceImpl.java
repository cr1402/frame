package com.kejin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kejin.entity.Dept;
import com.kejin.entity.vo.DeptVo;
import com.kejin.mapper.DeptMapper;
import com.kejin.service.IDeptService;
import com.kejin.utils.CommonUtils;
import com.kejin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen
 * @since 2023-05-04
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result addDept(Dept dept){
        Result result =new Result();

        long count = count(Wrappers.lambdaQuery(Dept.class).
                select(Dept::getDeptId).
                eq(Dept::getDeptCode, dept.getDeptCode()));

        if (count>0){
            result.setCode(-1).setMessage("该部门编号已存在，请修改");
        }else {
            save(dept);
            result.setCode(200).setMessage("新增部门："+dept.getDeptName()+"成功");

//            setFatherDept(0, dept.getFatherDeptId());
        }

        return result;
    }


    @Override
    public Result updateDept(Dept dept){
        Result result =new Result();

//        Integer fatherDept = getFatherDept(dept.getDeptId());

        long count = count(Wrappers.lambdaQuery(Dept.class).
                select(Dept::getDeptId).
                eq(Dept::getDeptCode, dept.getDeptCode()));

        if (count>0){
            result.setCode(-1).setMessage("该部门编号已存在，请修改");
        }else {
            updateById(dept);
//            setFatherDept(fatherDept, dept.getFatherDeptId());

            result.setCode(200).setMessage("修改部门："+dept.getDeptName()+"成功");
        }

        return result;
    }


    @Override
    public Result deleteDept(Dept dept){
        Result result =new Result();

        long count = count(Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptId, dept.getDeptId()));

        if (count>=1){
            result.setCode(-1).setMessage("该部门存在下级部门，无法删除");
            return result;
        }

//        Integer fatherDept = getFatherDept(dept.getDeptId());

        removeById(dept);

//        setFatherDept(fatherDept,dept.getFatherDeptId());

        deptMapper.deleteDept(dept.getDeptId());

        result.setCode(200).setMessage("删除成功");

        return result;
    }

    /**
     * 返回修改前的上级部门
     * @param deptId
     * @return
     */
   /* private Integer getFatherDept(Integer deptId){
        if (CommonUtils.numIsNull(deptId)){
            return 0;
        }

        Dept dept = getOne(Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptId, deptId));

        return dept.getFatherDeptId();
    }*/

    /**
     * 判断哪些部门需要更新下级部门的状态
     * @param oldFatherDept 修改前的部门
     * @param newFatherDept 修改后的部门
     */
    /*private void setFatherDept(Integer oldFatherDept, Integer newFatherDept){
        Integer fatherDept =newFatherDept;

        if (oldFatherDept.equals(newFatherDept)){
            return;
        }

        if(CommonUtils.numIsNull(oldFatherDept) && CommonUtils.numIsNull(newFatherDept)){
            return;
        }

        if (!CommonUtils.numIsNull(oldFatherDept) && !CommonUtils.numIsNull(newFatherDept)){
            setFatherDept(oldFatherDept);
            setFatherDept(newFatherDept);
            return;
        }

        if (CommonUtils.numIsNull(newFatherDept)){
            fatherDept =oldFatherDept;
        }

        setFatherDept(fatherDept);


    }
*/
    /**
     * 重新更新上级部门的下级部门信息
     * @param
     */
/*
    private void setFatherDept(Integer deptId){
        long count = count(Wrappers.lambdaQuery(Dept.class)
                .select(Dept::getDeptId)
                .eq(Dept::getFatherDeptId, deptId));

        Dept dept =new Dept();
        dept.setDeptId(deptId);
        if (count<=0){
            dept.setChildDept(0);
        }else {
            dept.setChildDept(1);
        }
        updateById(dept);
    }
*/


    @Override
    public Result getDeptList(){
        Result result =new Result();

        List<Dept> list = list();
        List<Dept> depts = list.stream().filter(dept -> CommonUtils.numIsNull(dept.getFatherDeptId())).collect(Collectors.toList());

        List<DeptVo> deptVos = depts.stream().map(dept ->new DeptVo(dept.getDeptId(), dept.getDeptName(), dept.getDeptId().toString(), null)).collect(Collectors.toList());

        deptVos.stream().forEach(dept -> {
            List<DeptVo> arr =new ArrayList();
            for (Dept d : list) {
                if(dept.getDeptId().equals(d.getFatherDeptId())){
                    DeptVo deptVo = new DeptVo(d.getDeptId(), d.getDeptName(), dept.getDeptId()+"-"+d.getDeptId(), null);
//                    d.setDeptId(dept.getDeptId());
                    arr.add(deptVo);
                }
            }
            dept.setChildren(arr);
        });

        result.setCode(200).setMessage("查询成功").setResult(deptVos);

        return result;
    }

    @Override
    public List<String> getChildDeptIds(Integer deptId) {
        LambdaQueryWrapper<Dept> eq = Wrappers.lambdaQuery(Dept.class)
                .select(Dept::getDeptId)
                .eq(Dept::getFatherDeptId, deptId);
        List<String> list = (List<String>)(List) listObjs(eq);

        return list;
    }
}
