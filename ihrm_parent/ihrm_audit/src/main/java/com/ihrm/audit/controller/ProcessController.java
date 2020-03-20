package com.ihrm.audit.controller;

import com.ihrm.audit.service.ProcessService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    @RequestMapping(value = "/deploy" , method = RequestMethod.POST)
    public Result deploy(@RequestParam("file")MultipartFile file) throws IOException {
        processService.deployProcess(file , companyId);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询所有的流程信息
     */
    @RequestMapping(value = "/definition" , method = RequestMethod.GET)
    public Result definitionList() {
        List list = processService.getProcessDefinitionList(companyId);
        return new Result(ResultCode.SUCCESS , list);
    }

    // 挂起和恢复流程
    @RequestMapping(value = "/suspend/{processKey}", method = RequestMethod.GET)
    public Result setProcessStatu(@PathVariable String processKey) {
        processService.suspendProcess(processKey);
        return new Result(ResultCode.SUCCESS);
    }



}
