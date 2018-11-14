package com.qf.oa.service;

import com.qf.oa.entity.DeptEmps;
import com.qf.oa.entity.Emp;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  10:04
 * @Version 1.0
 */
public interface IEmpService {
    public List<Emp> getEmpPage();
    public int saveOrUpdate(Emp emp);
    public int deleteEmp(Integer id);
    public int updateRoles(Integer eid,Integer[] rids);
    public Emp doLogin(String email);
    public List<Emp> getEmpByKeyword(String keyword,Integer id);
    public  List<DeptEmps> getEmpNumByDept();
    public List<DeptEmps> getEmpSexNum();
    public int batchInsert(List<Emp> list);
}
