package com.cwb.service;

import com.cwb.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    /**
     * 存储标签
     * @param type
     * @return
     */
    Tag saveTag(Tag type);

    /**
     * 根据ID获取标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 根据标签名查询
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 分页查询标签
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 修改标签
     * @param id
     * @param type
     * @return
     */
    Tag updateTag(Long id, Tag type);

    /**
     * 根据id删除标签
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

}
