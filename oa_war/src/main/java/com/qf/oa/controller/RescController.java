package com.qf.oa.controller;

import com.qf.oa.entity.Resc;
import com.qf.oa.service.IRescService;
import com.qf.ssm.interceptor.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/11/3  9:32
 * @Version 1.0
 */
@Controller
@RequestMapping("rescController")
public class RescController {
    @Autowired
    private IRescService rescService;
    @RequestMapping("getRescPage")
    public String getRescPage(Page page, Model model){
        page.setPageSize(5);
        List<Resc> rescList = rescService.getRescPage();
        model.addAttribute("rescList",rescList);
        model.addAttribute("indexs",page.getIndexs());
        return "rescList";
    }
    @RequestMapping("ajaxRescList")
    @ResponseBody
    public List<Resc> getRescListAjax(){
        List<Resc> rescs = rescService.getRescPage();
        return rescs;
    }
    @RequestMapping("ajaxRescList2")
    @ResponseBody
    public List<Resc> getRescListAjax2(Integer rid){
        List<Resc> rescs = rescService.getRescPageByRid(rid);
        return rescs;
    }
    @RequestMapping("saveorupdate")
    public String saveorupdate(Resc resc){
        rescService.saveorupdate(resc);
        return "redirect:/rescController/getRescPage";
    }
}
