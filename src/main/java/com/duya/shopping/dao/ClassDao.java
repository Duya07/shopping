package com.duya.shopping.dao;

import com.duya.shopping.pojo.ClassEntity;
import com.duya.shopping.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassDao extends JpaRepository<ClassEntity, Integer>, JpaSpecificationExecutor<ClassEntity> {

}
