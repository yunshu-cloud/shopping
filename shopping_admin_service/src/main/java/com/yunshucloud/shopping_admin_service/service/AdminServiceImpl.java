package com.yunshucloud.shopping_admin_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshucloud.shopping_admin_service.mapper.AdminMapper;
import com.yunshucloud.shopping_common.pojo.Admin;
import com.yunshucloud.shopping_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }


    @Override
    public void delete(Long id) {
        // 删除用户的所有角色
        adminMapper.deleteAdminAllRole(id);
        // 删除用户
        adminMapper.deleteById(id);
    }

    @Override
    public Admin findById(Long id) {
        return adminMapper.findById(id);
    }

    @Override
    public Page<Admin> search(int page, int size) {
        return adminMapper.selectPage(new Page(page,size),null);
    }
    @Override
    public void updateRoleToAdmin(Long aid, Long[] rids) {
        // 删除用户的所有角色
        adminMapper.deleteAdminAllRole(aid);
        // 重新添加管理员角色
        for (Long rid : rids) {
            adminMapper.addRoleToAdmin(aid, rid);
        }
    }
}
