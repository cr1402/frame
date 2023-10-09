package com.kejin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kejin.entity.Submenu;
import com.kejin.mapper.SubmenuMapper;
import com.kejin.service.ISubmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 子菜单表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2023-06-08
 */
@Service
public class SubmenuServiceImpl extends ServiceImpl<SubmenuMapper, Submenu> implements ISubmenuService {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private SubmenuMapper submenuMapper;

}
