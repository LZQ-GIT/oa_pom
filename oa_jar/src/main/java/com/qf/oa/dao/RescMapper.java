package com.qf.oa.dao;

import com.qf.oa.entity.Resc;

import java.util.List;

public interface RescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    int insert(Resc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    int insertSelective(Resc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    Resc selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Resc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_resc
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Resc record);

    List<Resc> getRescList();

    List<Resc> getRescListByRid(Integer rid);
}