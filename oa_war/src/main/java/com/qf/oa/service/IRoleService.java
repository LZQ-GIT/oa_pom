package com.qf.oa.service;

import com.qf.oa.entity.Role;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  20:24
 * @Version 1.0
 */
public interface IRoleService {
    public List<Role> getRolePage();
    public int savaOrUpdate(Role role);
    public int deleteRole(Integer id);

    public List<Role> getRoleList();
    public List<Role> getRolesByEid(Integer eid);

    public int updateRescsAjax(Integer rid,Integer[] reids);
}
