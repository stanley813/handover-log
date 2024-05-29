package org.example.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "T_HANDOVER_LOG")
@KeySequence(value = "SEQ_HANDOVER_LOG")
public class HandoverLog {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String userId;

    // 交班记录的录入人角色
    private Integer role;
    private String workStatus;

    // 状态：0x01:水电,0x02:门窗,0x04:设备,0x01|0x02|0x04:全部勾选
    private Integer patrolRecord;
    private String dangerValue;
    private String specialCase;
    private Integer shift;
    private String remarkUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date LogTime;

    // 批注?
    private String remark;

    @TableField(exist = false)
    private Integer start;

    @TableField(exist = false)
    private Integer length;

    @TableField(exist = false)
    private Integer isRemark;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date startTime;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date endTime;

}
