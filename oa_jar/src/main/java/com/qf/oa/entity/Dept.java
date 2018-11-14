package com.qf.oa.entity;

import java.io.Serializable;

public class Dept implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.pid
     *
     * @mbggenerated
     */
    private Integer pid;
    private String pname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.dname
     *
     * @mbggenerated
     */
    private String dname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.deptInfo
     *
     * @mbggenerated
     */
    private String deptinfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dept.createtime
     *
     * @mbggenerated
     */
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_dept
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.id
     *
     * @return the value of t_dept.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.id
     *
     * @param id the value for t_dept.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.pid
     *
     * @return the value of t_dept.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.pid
     *
     * @param pid the value for t_dept.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.dname
     *
     * @return the value of t_dept.dname
     *
     * @mbggenerated
     */
    public String getDname() {
        return dname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.dname
     *
     * @param dname the value for t_dept.dname
     *
     * @mbggenerated
     */
    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.deptInfo
     *
     * @return the value of t_dept.deptInfo
     *
     * @mbggenerated
     */
    public String getDeptinfo() {
        return deptinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.deptInfo
     *
     * @param deptinfo the value for t_dept.deptInfo
     *
     * @mbggenerated
     */
    public void setDeptinfo(String deptinfo) {
        this.deptinfo = deptinfo == null ? null : deptinfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dept.createtime
     *
     * @return the value of t_dept.createtime
     *
     * @mbggenerated
     */
    public String  getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dept.createtime
     *
     * @param createtime the value for t_dept.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(String  createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dept
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Dept other = (Dept) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getDname() == null ? other.getDname() == null : this.getDname().equals(other.getDname()))
            && (this.getDeptinfo() == null ? other.getDeptinfo() == null : this.getDeptinfo().equals(other.getDeptinfo()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dept
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getDname() == null) ? 0 : getDname().hashCode());
        result = prime * result + ((getDeptinfo() == null) ? 0 : getDeptinfo().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", pid=" + pid +
                ", pname='" + pname + '\'' +
                ", dname='" + dname + '\'' +
                ", deptinfo='" + deptinfo + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dept
     *
     * @mbggenerated
     */


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}