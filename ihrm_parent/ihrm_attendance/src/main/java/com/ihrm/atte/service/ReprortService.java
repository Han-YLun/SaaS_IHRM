package com.ihrm.atte.service;


import com.ihrm.atte.dao.ArchiveMonthlyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReprortService {


    @Autowired
    private ArchiveMonthlyDao archiveMonthlyDao;

}
