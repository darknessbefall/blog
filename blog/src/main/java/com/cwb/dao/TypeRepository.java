package com.cwb.dao;

import com.cwb.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long>,JpaSpecificationExecutor<Type> {

    public Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
