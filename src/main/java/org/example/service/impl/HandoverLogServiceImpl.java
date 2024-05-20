package org.example.service.impl;

import org.example.dao.HandoverLogMapper;
import org.example.domain.HandoverLog;
import org.example.service.HandoverLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class HandoverLogServiceImpl extends ServiceImpl<HandoverLogMapper, HandoverLog> implements HandoverLogService {

}
