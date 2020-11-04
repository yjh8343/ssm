package com.yjh.service.impl;

import com.github.pagehelper.PageHelper;
import com.yjh.dao.SysLogDao;
import com.yjh.pojo.SysLog;
import com.yjh.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll();
    }
}
