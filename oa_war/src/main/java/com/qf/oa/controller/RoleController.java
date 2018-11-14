package com.qf.oa.controller;

import com.qf.oa.entity.Role;
import com.qf.oa.service.IRoleService;
import com.qf.ssm.interceptor.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  20:27
 * @Version 1.0
 */
@Controller
@RequestMapping("roleController")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("getRolePage")
    public String getRolePage(Page page, Model model){
        page.setPageSize(4);
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("roleList",roleList);
        model.addAttribute("indexs",page.getIndexs());
        return "roleList";
    }
    @RequestMapping("getRoles")
    @ResponseBody
    public List<Role> getRoles(){
        List<Role> roles = roleService.getRoleList();
        return roles;
    }
    @RequestMapping("getRolesByEid")
    @ResponseBody
    public List<Role> getRolesByEid(Integer eid){
        List<Role> roles = roleService.getRolesByEid(eid);
        return roles;
    }

    @RequestMapping("saveorupdate")
    public String saveOrUpdate(Role role){
        roleService.savaOrUpdate(role);
        return "redirect:/roleController/getRolePage";
    }

    @RequestMapping("deleteRole/{id}")
    public void deleteRole(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        try {
            roleService.deleteRole(id);
            response.getWriter().write("<script>window.location='/roleController/getRolePage';</script>");
        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('该角色还有权限或其他员工具有该角色，请将权限以及具有该角色的员工的角色清空，再进行删除');window.location='/roleController/getRolePage';</script>");
        }
    }

    @RequestMapping("updateRescs")
    @ResponseBody
    public Integer updateRescs(Integer rid,Integer[] reids){
        roleService.updateRescsAjax(rid,reids);
        return 1;
    }
}
