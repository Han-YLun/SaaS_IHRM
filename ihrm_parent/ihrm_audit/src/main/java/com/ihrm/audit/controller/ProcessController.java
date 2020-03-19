package com.ihrm.audit.controller;

import com.ihrm.audit.service.ProcessService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 流程控制的controller
 * @author: hyl
 * @date: 2020/03/19
 **/
@CrossOrigin
@RestController
@RequestMapping("/user/process")
public class ProcessController extends BaseController {

    @Autowired
    private ProcessService processService;

    /**
     * 部署流程
     */
    @RequestMapping(value = "/deplog" , method = RequestMethod.POST)
    public Result deploy(@RequestParam("file")MultipartFile file) throws IOException {
        processService.deployProcess(file , companyId);
        return new Result(ResultCode.SUCCESS);
    }



}
