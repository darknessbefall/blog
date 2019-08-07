package com.cwb.service.impl;

import com.cwb.NotFoundException;
import com.cwb.dao.TypeRepository;
import com.cwb.pojo.Type;
import com.cwb.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * 新增类型
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 查询类型
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);   //版本问题,1.5.6可以使用findOne来通过ID查询
    }

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    /**
     * 查询全部
     * @return
     */
    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    /**
     * 修改
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        //先根据id查询到对象
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t );      //将传过来的type复复制到查询出来的type上,然后更新
        return typeRepository.save(t);
    }

    /**
     * 根据主键id删除类型
     * @param id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        //先查后删
        //先根据id查询到对象
        Type type = typeRepository.getOne(id);
        if (type == null) {
            throw new NotFoundException("不存在该类型");
        }
        typeRepository.delete(type);
    }

    /**
     * 通过类型名字查询
     * @param name
     * @return
     */
    @Override
    public Type getTypeByName(String name) {
        Type byName = typeRepository.findByName(name);
        return byName;
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort );
        return typeRepository.findTop(pageable);
    }

}
