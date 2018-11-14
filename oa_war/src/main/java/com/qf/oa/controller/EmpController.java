package com.qf.oa.controller;

import com.qf.oa.entity.DeptEmps;
import com.qf.oa.entity.Emp;
import com.qf.oa.service.IEmpService;
import com.qf.ssm.interceptor.Page;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
 * @DateTime 2018/11/2  11:12
 * @Version 1.0
 */
@Controller
@RequestMapping("empController")
public class EmpController {
    @Autowired
    private IEmpService empService;

    @RequestMapping("getEmpPage")
    public String getEmpPage(Model model, Page page) {
        page.setPageSize(5);
        List<Emp> empList = empService.getEmpPage();
        model.addAttribute("empList", empList);
        model.addAttribute("indexs", page.getIndexs());
        return "empList";
    }

    @RequestMapping("saveorupdate")
    public String saveOrUpdate(Emp emp) {
        empService.saveOrUpdate(emp);
        return "redirect:/empController/getEmpPage";
    }

    @RequestMapping("deleteEmp/{id}")
    public void deleteEmp(@PathVariable Integer id,HttpServletResponse response) throws IOException {
        try {
            empService.deleteEmp(id);
            response.getWriter().write("<script>window.location='/empController/getEmpPage';</script>");

        } catch (Exception e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('该员工还有角色，请将角色清空，再进行删除');window.location='/empController/getEmpPage';</script>");
        }
    }

    @RequestMapping("updateRoles")
    public String updateRoles(Integer eid,Integer[] rids){
        empService.updateRoles(eid,rids);
        return "redirect:/empController/getEmpPage";
    }
    @RequestMapping("getEmpNumByDept")
    @ResponseBody
    public List<DeptEmps> getEmpNumByDept(){
        List<DeptEmps> list = empService.getEmpNumByDept();
        return list;
    }
    @RequestMapping("getEmpSexNum")
    @ResponseBody
    public List<DeptEmps> getEmpSexNum(){
        List<DeptEmps> list = empService.getEmpSexNum();
        return list;
    }
    @RequestMapping("exportEmp")
    public void exportEmp(HttpServletResponse response) throws IOException, WriteException {
        List<Emp> empList = empService.getEmpPage();
        //1.创建一个表格文件
        WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\Administrator\\Desktop\\Emp.xls"));
        //2.在文件里面创建一个表格
        WritableSheet empSheet = workbook.createSheet("员工信息表",0);
        //3.设置表格内容：标题，表头,以及通过cellFormat来设置样式
        WritableCellFormat cellFormat = new WritableCellFormat();
        WritableCellFormat cellFormat2 = new WritableCellFormat();
        WritableCellFormat cellFormat3 = new WritableCellFormat();
        WritableCellFormat cellFormat4 = new WritableCellFormat();
        cellFormat.setBackground(jxl.format.Colour.RED);
        cellFormat2.setBackground(jxl.format.Colour.GRAY_25);
        cellFormat3.setBackground(jxl.format.Colour.SKY_BLUE);
        cellFormat4.setBackground(Colour.LIGHT_GREEN);
        empSheet.addCell(new Label(3,0,"员工信息表",cellFormat));
        empSheet.addCell(new Label(0,1,"员工编号",cellFormat2));
        empSheet.addCell(new Label(1,1,"员工姓名",cellFormat2));
        empSheet.addCell(new Label(2,1,"员工邮箱",cellFormat2));
        empSheet.addCell(new Label(3,1,"员工密码",cellFormat2));
        empSheet.addCell(new Label(4,1,"员工性别",cellFormat2));
        empSheet.addCell(new Label(5,1,"员工生日",cellFormat2));
        empSheet.addCell(new Label(6,1,"员工头像",cellFormat2));
        empSheet.addCell(new Label(7,1,"员工部门编号",cellFormat2));
        int row = 2;//内容从第三行开始，也就是r=2;
        for (Emp emp : empList) {
            if(row%2==0){
                empSheet.addCell(new Label(0,row,String.valueOf(emp.getId()),cellFormat3));
                empSheet.addCell(new Label(1,row,emp.getName(),cellFormat3));
                empSheet.addCell(new Label(2,row,emp.getEmail(),cellFormat3));
                empSheet.addCell(new Label(3,row,emp.getPassword(),cellFormat3));
                empSheet.addCell(new Label(4,row,String.valueOf(emp.getSex()),cellFormat3));
                empSheet.addCell(new Label(5,row,emp.getBirthday(),cellFormat3));
                empSheet.addCell(new Label(6,row,emp.getImg(),cellFormat3));
                empSheet.addCell(new Label(7,row,String.valueOf(emp.getDid()),cellFormat3));
            }else{
                empSheet.addCell(new Label(0,row,String.valueOf(emp.getId()),cellFormat4));
                empSheet.addCell(new Label(1,row,emp.getName(),cellFormat4));
                empSheet.addCell(new Label(2,row,emp.getEmail(),cellFormat4));
                empSheet.addCell(new Label(3,row,emp.getPassword(),cellFormat4));
                empSheet.addCell(new Label(4,row,String.valueOf(emp.getSex()),cellFormat4));
                empSheet.addCell(new Label(5,row,emp.getBirthday(),cellFormat4));
                empSheet.addCell(new Label(6,row,emp.getImg(),cellFormat4));
                empSheet.addCell(new Label(7,row,String.valueOf(emp.getDid()),cellFormat4));
            }
            row++;
        }
        //4.内容设置完毕，就将文件写出到指定目录
        workbook.write();
        if (workbook != null) {
            workbook.close();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('导出员工表至桌面[Emp.xls]中');window.location='/deptController/getDeptPage';</script>");

    }
    @RequestMapping("importEmp")
    public void importEmp(MultipartFile file,HttpServletResponse response) throws IOException {
        List<Emp> empList = new ArrayList<>();
        //1.得到表格文件
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        //2.获得文件里面的第一页表格
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获得第三行，数据在第三行里面
        for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
            //获得当前指定行
            HSSFRow row = sheet.getRow(i);
            //一行代表一个对象，创建emp对象
            Emp emp = new Emp();
            for(int j=0;j<row.getPhysicalNumberOfCells();j++){
                //获得当前指定单元格
                HSSFCell cell = row.getCell(j);
                if(j==0){
                    emp.setId(Integer.parseInt(cell.toString()));
                }else if(j==1){
                    emp.setName(cell.toString());
                }else if(j==2){
                    emp.setEmail(cell.toString());
                }else if(j==3){
                    emp.setPassword(cell.toString());
                }else if(j==4){
                    emp.setSex(Integer.parseInt(cell.toString()));
                }else if(j==5){
                    emp.setBirthday(cell.toString());
                }else if(j==6){
                    emp.setImg(cell.toString());
                }else if(j==7){
                    emp.setDid(Integer.parseInt(cell.toString()));
                }

            }
            empList.add(emp);
        }
        empService.batchInsert(empList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('导入员工表至数据库1806_oa--[t_emp_copy]中');window.location='/empController/getEmpPage';</script>");
    }
}
