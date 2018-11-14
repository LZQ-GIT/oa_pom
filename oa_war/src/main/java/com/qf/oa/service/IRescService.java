package com.qf.oa.service;

import com.qf.oa.entity.Resc;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/3  9:30
 * @Version 1.0
 */
public interface IRescService {
    public List<Resc> getRescPage();
    public List<Resc> getRescPageByRid(Integer rid);
    public int saveorupdate(Resc resc);

}
