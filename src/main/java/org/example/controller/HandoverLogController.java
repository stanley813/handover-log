package org.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.example.domain.HandoverLog;
import org.example.service.impl.HandoverLogServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Stanley Zhou on 2019/7/9.
 */
@RestController
@RequestMapping("/handoverLog")
@Slf4j
public class HandoverLogController {

    @Autowired
    private HandoverLogServiceImpl handoverLogService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody HandoverLog handoverLog) {
        log.info("insert...param:{}", JSON.toJSONString(handoverLog));
        try {
            handoverLog.setLogTime(new Date());
            handoverLogService.save(handoverLog);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("insert error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/list")
    public ResponseEntity<JSONObject> list(@RequestBody HandoverLog handoverLog) {
        log.info("list...param:{}", JSON.toJSONString(handoverLog));
        try {
            int start = handoverLog.getStart();
            int length = handoverLog.getLength();
            start = start / length + 1;

            QueryWrapper<HandoverLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().orderByDesc(HandoverLog::getLogTime);
            if (StringUtils.isNotEmpty(handoverLog.getUserId()))
                queryWrapper.lambda().eq(HandoverLog::getUserId, handoverLog.getUserId());
            if (handoverLog.getRole() != null)
                queryWrapper.lambda().eq(HandoverLog::getRole, handoverLog.getRole());
            if (handoverLog.getStartTime() != null)
                queryWrapper.lambda().ge(HandoverLog::getLogTime, handoverLog.getStartTime());
            if (handoverLog.getEndTime() != null)
                queryWrapper.lambda().le(HandoverLog::getLogTime, handoverLog.getEndTime());
            if (handoverLog.getIsRemark() != null){
                if (handoverLog.getIsRemark() == 1){
                    queryWrapper.lambda().isNotNull(HandoverLog::getRemark);
                }else if(handoverLog.getIsRemark() == 0){
                    queryWrapper.lambda().isNull(HandoverLog::getRemark);
                }
            }

            Page<HandoverLog> page = new Page<>(start, length);

            IPage<HandoverLog> result = handoverLogService.page(page, queryWrapper);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("recordsFiltered", result.getTotal());
            jsonObject.put("recordsTotal", result.getTotal());
            jsonObject.put("data", result.getRecords());

            return ResponseEntity.status(HttpStatus.OK).body(jsonObject);
        } catch (Exception e) {
            log.error("list error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/remark")
    public ResponseEntity<Void> remark(@RequestBody HandoverLog handoverLog) {
        log.info("remark...param:{}", JSON.toJSONString(handoverLog));
        try {
            HandoverLog hl = new HandoverLog();
            BeanUtils.copyProperties(handoverLog, hl, "userId", "workStatus", "patrolRecord", "dangerValue",
                    "specialCase", "LogTime");
            handoverLogService.updateById(hl);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("remark error...", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
