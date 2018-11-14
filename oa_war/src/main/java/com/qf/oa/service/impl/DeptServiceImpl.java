package com.qf.oa.service.impl;

import com.qf.oa.dao.DeptMapper;
import com.qf.oa.entity.Dept;
import com.qf.oa.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/1  12:55
 * @Version 1.0
 */
@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getDeptPage() {
        return deptMapper.getDeptList();
    }

    @Override
    public int addDept(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public int completeDeleteById(Integer id) {
        return deptMapper.completeDeleteById(id);
    }

    @Override
    public List<Dept> getDeptListForExcel() {
        return deptMapper.getDeptListForExcel();
    }

    @Override
    public int batchInsert(List<Dept> list) {
        return deptMapper.batchInsert(list);
    }
}
