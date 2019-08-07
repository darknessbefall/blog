package com.cwb.service;

import com.cwb.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /**
     * 新增类型
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 查询类型
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 查询所有分类到集合中
     * @return
     */
    List<Type> listType();

    /**
     * 修改
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 根据主键id删除类型
     * @param id
     */
    void deleteType(Long id);

    /**
     * 通过类型名称查询
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    List<Type> listTypeTop(Integer size);
}
