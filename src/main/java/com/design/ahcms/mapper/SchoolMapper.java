package com.design.ahcms.mapper;

import com.design.ahcms.domain.School;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SchoolMapper {
    List<School> list();
}
