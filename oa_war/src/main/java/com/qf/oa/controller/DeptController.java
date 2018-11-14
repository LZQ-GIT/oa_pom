package com.qf.oa.controller;

import com.qf.oa.entity.Dept;
import com.qf.oa.service.IDeptService;
import com.qf.ssm.interceptor.Page;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/1  13:00
 * @Version 1.0
 */
@Controller
@RequestMapping("deptController")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    @RequestMapping("getDeptPage")
    public String getDeptPage(Page page, Model model){
        page.setPageSize(5);
        List<Dept> deptList = deptService.getDeptPage();
        model.addAttribute("deptList",deptList);
        model.addAttribute("indexs",page.getIndexs());
        return "deptList";
    }
    @RequestMapping("ajaxDeptList")
    @ResponseBody
    public List<Dept> ajaxDeptList(){
        List<Dept> deptList = deptService.getDeptPage();
        return deptList;
    }
    @RequiresPermissions("deptController/saveorupdate")
    @RequestMapping("addDept")
    public String addDept(Dept dept){
        deptService.addDept(dept);
        return "redirect:/deptController/getDeptPage";
    }
    @RequestMapping("getDeptById")
    @ResponseBody
    public Dept getDeptById(Integer id){
        Dept dept = deptService.getDeptById(id);
        return dept;
    }
    @RequiresPermissions("deptController/saveorupdate")
    @RequestMapping("updateDept")
    public String updateDept(Dept dept){
        deptService.updateDept(dept);
        return "redirect:/deptController/getDeptPage";
    }
    @RequiresPermissions("deptController/deleteDept")
    @RequestMapping("deleteDept/{id}")
    public String deleteDept(@PathVariable  Integer id){
        deptService.completeDeleteById(id);
        return "redirect:/deptController/getDeptPage";
    }
    @RequestMapping("exportDept")
    public void exportDept(HttpServletResponse response) throws IOException, WriteException {
        List<Dept> deptList = deptService.getDeptListForExcel();
        //1.通过Workbook创建一个excel表格文件
        WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\Administrator\\Desktop\\Dept.xls"));
         //2.创建一个表格，并设置表格的sheet名称
        WritableSheet deptSheet = workbook.createSheet("部门信息表", 0);
        //3.设置表格的表头，也就是部门表的字段,并设置表头颜色
        WritableCellFormat cellFormat = new WritableCellFormat();
        WritableCellFormat cellFormat2 = new WritableCellFormat();
        WritableCellFormat cellFormat3 = new WritableCellFormat();
        WritableCellFormat cellFormat4 = new WritableCellFormat();
        cellFormat.setBackground(Colour.RED);
        cellFormat2.setBackground(Colour.GRAY_25);
        cellFormat3.setBackground(Colour.SKY_BLUE);
        cellFormat4.setBackground(Colour.LIGHT_GREEN);
        deptSheet.addCell(new Label(2,0,"部门信息表",cellFormat));
        deptSheet.addCell(new Label(0,1,"部门编号",cellFormat2));
        deptSheet.addCell(new Label(1,1,"父部门编号",cellFormat2));
        deptSheet.addCell(new Label(2,1,"部门名称",cellFormat2));
        deptSheet.addCell(new Label(3,1,"部门描述",cellFormat2));
        deptSheet.addCell(new Label(4,1,"部门创建时间",cellFormat2));
        //4.设置表格的数据
        int row = 2;//行数，为迭代递增
        for (Dept dept : deptList) {
            if(row%2!=0){
                deptSheet.addCell(new Label(0,row,String.valueOf(dept.getId()),cellFormat3));
                deptSheet.addCell(new Label(1,row,String.valueOf(dept.getPid()),cellFormat3));
                deptSheet.addCell(new Label(2,row,dept.getDname(),cellFormat3));
                deptSheet.addCell(new Label(3,row,dept.getDeptinfo(),cellFormat3));
                deptSheet.addCell(new Label(4,row,dept.getCreatetime(),cellFormat3));
            }else{
                deptSheet.addCell(new Label(0,row,String.valueOf(dept.getId()),cellFormat4));
                deptSheet.addCell(new Label(1,row,String.valueOf(dept.getPid()),cellFormat4));
                deptSheet.addCell(new Label(2,row,dept.getDname(),cellFormat4));
                deptSheet.addCell(new Label(3,row,dept.getDeptinfo(),cellFormat4));
                deptSheet.addCell(new Label(4,row,dept.getCreatetime(),cellFormat4));
            }
            row++;
        }
        //将文件写出到指定的桌面上
        workbook.write();
        if(workbook!=null){
            workbook.close();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('导出部门表至桌面[Dept.xls]中');window.location='/deptController/getDeptPage';</script>");
    }
    @RequestMapping("importExcel")
    public void importExcel(MultipartFile file,HttpServletResponse response) throws IOException {
        List<Dept> deptList = new ArrayList<>();
        //1.获得表格文件
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        //2.获得文件内的表格
        HSSFSheet sheet = workbook.getSheetAt(0);
        //3.获得表格里面的所有行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 2; i < rows; i++) {
            //获得行
            HSSFRow row = sheet.getRow(i);
            //准备部门对象，一行相当于一个部门对象
            Dept dept = new Dept();
            //获得行里面的所有单元格数
            int cells = row.getPhysicalNumberOfCells();
            for (int j = 0; j < cells; j++) {
                //获得单元格内容
                HSSFCell cell = row.getCell(j);
                if(j==0){
                    dept.setId(Integer.parseInt(cell.toString()));
                }else if(j==1){
                    dept.setPid(Integer.parseInt(cell.toString()));
                }else if(j==2){
                    dept.setDname(cell.toString());
                }else if(j==3){
                    dept.setDeptinfo(cell.toString());
                }else if(j==4){
                    dept.setCreatetime(cell.toString());
                }
            }
            //将部门对象装入集合
            deptList.add(dept);
        }
        deptService.batchInsert(deptList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('导入部门表至数据库1806_oa--[t_dept_copy]中');window.location='/deptController/getDeptPage';</script>");
    }
}
