package com.qf.oa.service;

import com.qf.oa.entity.Dept;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/1  12:53
 * @Version 1.0
 */
public interface IDeptService {
    public List<Dept> getDeptPage();
    public int addDept(Dept dept);
    public Dept getDeptById(Integer id);
    public int updateDept(Dept dept);
    public int completeDeleteById(Integer id);
    public List<Dept> getDeptListForExcel();
    public int batchInsert(List<Dept> list);
}
