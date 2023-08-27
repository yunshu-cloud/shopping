package com.yunshucloud.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshucloud.shopping_common.pojo.Role;
import com.yunshucloud.shopping_common.result.BaseResult;
import com.yunshucloud.shopping_common.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台角色
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @DubboReference
    private RoleService roleService;


    /**
     * 新增角色
     *
     * @param role 角色对象
     * @return 执行结果
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Role role) {
        roleService.add(role);
        return BaseResult.ok();
    }


    /**
     * 修改角色
     *
     * @param role 角色对象
     * @return 执行结果
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Role role) {
        roleService.update(role);
        return BaseResult.ok();
    }


    /**
     * 删除角色
     *
     * @param rid 角色id
     * @return 执行结果
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long rid) {
        roleService.delete(rid);
        return BaseResult.ok();
    }


    /**
     * 根据id查询角色
     *
     * @param rid
     * @return 查询到的角色
     */
    @GetMapping("/findById")
    public BaseResult<Role> findById(Long rid) {
        Role role = roleService.findById(rid);
        return BaseResult.ok(role);
    }


    /**
     * 分页查询角色
     *
     * @param page 页码
     * @param size 每页条数
     * @return 查询结果
     */
    @GetMapping("/search")
    public BaseResult<Page<Role>> search(int page, int size) {
        Page<Role> page1 = roleService.search(page, size);
        return BaseResult.ok(page1);
    }


    /**
     * 查询所有角色
     * @return 查询结果
     */
    @GetMapping("/findAll")
    public BaseResult<List<Role>> findAll() {
        List<Role> all = roleService.findAll();
        return BaseResult.ok(all);
    }




    /**
     * 修改角色的权限
     *
     * @param rid  角色id
     * @param pids 权限id
     * @return 执行结果
     */
    @PutMapping("/updatePermissionToRole")
    public BaseResult updatePermissionToRole(Long rid, Long[] pids) {
        roleService.addPermissionToRole(rid,pids);
        return BaseResult.ok();
    }
}

