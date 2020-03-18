package com.ihrm.salarys.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.salarys.SalaryArchive;
import com.ihrm.domain.salarys.SalaryArchiveDetail;
import com.ihrm.salarys.dao.ArchiveDao;
import com.ihrm.salarys.dao.ArchiveDetailDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 归档service
 */
@Service
public class ArchiveService {
    @Autowired
    private ArchiveDao archiveDao;
    @Autowired
    private ArchiveDetailDao archiveDetailDao;
    @Autowired
    private IdWorker idWorker;

}
