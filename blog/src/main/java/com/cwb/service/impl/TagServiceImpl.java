package com.cwb.service.impl;

import com.cwb.NotFoundException;
import com.cwb.dao.TagRepository;
import com.cwb.pojo.Tag;
import com.cwb.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 存储标签
     * @param tag
     * @return
     */
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 根据ID获取标签
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    /**
     * 根据标签名查询
     * @param name
     * @return
     */
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * 分页查询标签
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {

        return tagRepository.findAll(pageable);
    }

    /**
     * 修改标签
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag type) {
        //先查后改
        Tag t = tagRepository.getOne(id);
        if(t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t );
        return tagRepository.save(t);
    }

    /**
     * 根据id删除标签
     * @param id
     */
    @Transactional
    @Override
    public void deleteTag(Long id) {
        //先查后删
        //先根据id查询到对象
        Tag tag = tagRepository.getOne(id);
        if (tag == null) {
            throw new NotFoundException("不存在该类型");
        }
        tagRepository.delete(tag);
    }

    /**
     * 查询所有
     * @return
     */
    @Transactional
    @Override
    public List<Tag> listTag() {

        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {

        return  tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort );
        List<Tag> top = tagRepository.findTop(pageable);
        return top;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0;i<idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


}
