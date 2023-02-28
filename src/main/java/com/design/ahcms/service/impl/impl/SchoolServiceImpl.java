package com.design.ahcms.service.impl.impl;

import com.design.ahcms.common.Result;
import com.design.ahcms.domain.School;
import com.design.ahcms.mapper.SchoolMapper;
import com.design.ahcms.service.impl.SchoolService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolMapper schoolMapper;
    @Override
    public List<School> list() {
        return schoolMapper.list();
    }
}
