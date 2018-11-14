package com.qf.oa.service.impl;

import com.qf.oa.dao.RoleMapper;
import com.qf.oa.entity.Role;
import com.qf.oa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  20:25
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolePage() {
        return roleMapper.getRoleList();
    }

    @Override
    public int savaOrUpdate(Role role) {
        if(role.getId()==null){
            return roleMapper.insert(role);
        }else{
            return roleMapper.updateByPrimaryKey(role);
        }
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList2();
    }

    @Override
    public List<Role> getRolesByEid(Integer eid) {
        return roleMapper.getRolesByEid(eid);
    }

    @Override
    @Transactional
    public int updateRescsAjax(Integer rid, Integer[] reids) {
        roleMapper.deleteRoleAndRescInfo(rid);
        if(reids!=null){
            roleMapper.insertRoleAndRescInfo(rid,reids);
        }
        return 1;
    }
}
