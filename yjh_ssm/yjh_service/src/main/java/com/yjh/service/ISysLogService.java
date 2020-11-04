package com.yjh.service;

import com.yjh.pojo.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll(int page,int pageSize);
}
