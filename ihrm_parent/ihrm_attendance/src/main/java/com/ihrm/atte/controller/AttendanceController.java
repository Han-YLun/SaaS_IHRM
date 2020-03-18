package com.ihrm.atte.controller;

import com.ihrm.atte.service.ArchiveService;
import com.ihrm.atte.service.AtteService;
import com.ihrm.atte.service.ExcelImportService;
import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.atte.entity.ArchiveMonthly;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.atte.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/03/16
 **/
@RestController
@RequestMapping("/attendances")
public class AttendanceController extends BaseController {

    @Autowired
    private ExcelImportService excelImportService;

    @Autowired
    private AtteService atteService;

    @Resource(name = "ihrm_attendance_archiveService")
    private ArchiveService archiveService;

    /**
     * 上传考勤数据
     */
    @RequestMapping(value = "/import" , method = RequestMethod.POST)
    public Result importExcel(@RequestParam(name ="file") MultipartFile file) throws Exception {
        excelImportService.importAttendanceExcel(file , companyId);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询考勤数据列表
     */
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public Result list(int page,int pagesize) throws ParseException {
        Map map = atteService.getAtteDate(companyId , page , pagesize);
        return new Result(ResultCode.SUCCESS , map);
    }

    /**
     * 编辑用户的考勤记录
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public Result editAtte(@RequestBody Attendance attendance){
        atteService.ehitAtte(attendance);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 获取月度报表归档数据
     */
    @RequestMapping(value = "/reports" , method = RequestMethod.GET)
    public Result reports(String atteDate){
        List<ArchiveMonthlyInfo> list = atteService.getReports(atteDate , companyId);
        return new Result(ResultCode.SUCCESS , list);
    }

    /**
     * 数据归档
     */
    @RequestMapping(value = "/archive/item" , method = RequestMethod.GET)
    public Result archive(String archiveDate) {
        archiveService.saveArchive(archiveDate , companyId);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 新建报表
     */
    @RequestMapping(value = "/newReports" , method = RequestMethod.GET)
    public Result newReports(String atteDate) {
        atteService.newReports(atteDate , companyId);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 归档历史列表
     */
    @RequestMapping(value = "/reports/year" , method = RequestMethod.GET)
    public Result findReportsYear(String year) {
        List<ArchiveMonthly> list = archiveService.findByYear(year , companyId);
        return new Result(ResultCode.SUCCESS , list);
    }

    /**
     * 查询归档详情
     */
    @RequestMapping(value = "/reports/{id}" , method = RequestMethod.POST)
    public Result findInfosById(@PathVariable String id) {
        List<ArchiveMonthlyInfo> list = archiveService.findMonthInfoByAmid(id);
        return new Result(ResultCode.SUCCESS , list);
    }

    /**
     * 根据用户id和月份查询已归档的考勤明细
     */
    @RequestMapping(value = "/archive/{userId}/{yearMonth}" , method = RequestMethod.GET)
    public Result historyData(@PathVariable String userId,@PathVariable String yearMonth) {
        ArchiveMonthlyInfo archiveMonthlyInfo = archiveService.findUserArchiveDetail(userId , yearMonth);
        return new Result(ResultCode.SUCCESS , archiveMonthlyInfo);
    }
    
}
