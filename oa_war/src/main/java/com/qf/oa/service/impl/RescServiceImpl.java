package com.qf.oa.service.impl;

import com.qf.oa.dao.RescMapper;
import com.qf.oa.entity.Resc;
import com.qf.oa.service.IRescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/3  9:31
 * @Version 1.0
 */
@Service
public class RescServiceImpl implements IRescService {
    @Autowired
    private RescMapper rescMapper;
    @Override
    public List<Resc> getRescPage() {
        return rescMapper.getRescList();
    }

    @Override
    public List<Resc> getRescPageByRid(Integer rid) {
        return rescMapper.getRescListByRid(rid);
    }

    @Override
    public int saveorupdate(Resc resc) {
        if(resc.getId()!=null){
            return rescMapper.updateByPrimaryKey(resc);
        }else{
            return rescMapper.insert(resc);
        }

    }
}
