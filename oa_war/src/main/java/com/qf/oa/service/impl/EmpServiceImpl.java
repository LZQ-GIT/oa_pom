package com.qf.oa.service.impl;

import com.qf.oa.dao.EmpMapper;
import com.qf.oa.entity.DeptEmps;
import com.qf.oa.entity.Emp;
import com.qf.oa.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  11:10
 * @Version 1.0
 */
@Service
public class EmpServiceImpl implements IEmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Emp> getEmpPage() {
        return empMapper.getEmpList();
    }

    @Override
    public int saveOrUpdate(Emp emp) {
        if(emp.getId()==null){
           return empMapper.insert(emp);
        }else{
            return  empMapper.updateByPrimaryKey(emp);
        }
    }

    @Override
    public int deleteEmp(Integer id) {
        return empMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateRoles(Integer eid, Integer[] rids) {
        empMapper.deleteEmpandRoleInfo(eid);
        if(rids!=null){
            empMapper.insertEmpandRoleInfo(eid,rids);
        }
        return 1;
    }

    @Override
    public Emp doLogin(String email) {
        return empMapper.doLogin(email);
    }

    @Override
    public List<Emp> getEmpByKeyword(String keyword, Integer id) {
        return empMapper.getEmpByKeyword(keyword,id);
    }

    @Override
    public List<DeptEmps> getEmpNumByDept() {
        return empMapper.getEmpNumByDept();
    }

    @Override
    public List<DeptEmps> getEmpSexNum() {
        return empMapper.getEmpSexNum();
    }

    @Override
    public int batchInsert(List<Emp> list) {
        return empMapper.batchInsert(list);
    }
}
