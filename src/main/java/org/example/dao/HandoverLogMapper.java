package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.HandoverLog;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Repository
public interface HandoverLogMapper extends BaseMapper<HandoverLog>{
}
