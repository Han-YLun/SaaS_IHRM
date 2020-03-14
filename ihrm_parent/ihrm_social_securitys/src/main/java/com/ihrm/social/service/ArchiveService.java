package com.ihrm.social.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.social.dao.ArchiveDao;
import com.ihrm.social.dao.ArchiveDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArchiveService {

	@Autowired
	private ArchiveDao archiveDao;

	@Autowired
	private ArchiveDetailDao archiveDetailDao;

	@Autowired
	private UserSocialService userSocialService;

	@Autowired
	private IdWorker idWorker;
}
