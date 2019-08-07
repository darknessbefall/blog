package com.cwb.service;

import com.cwb.pojo.Blog;
import com.cwb.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 根据查询条件进行分页查询
     * @param pageable
     * @param blog  查询条件
     * @return
     */
    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    /**
     * 新增博客
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 通过id查询,在通过BeanUtils进行转移,最后存储
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 根据id删除博客
     * @param id
     */
    void deleteBlog(Long id);

    /**
     * 全局搜索
     * @param query
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * 分页查找
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 已经发布的博客
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 将MarkDown格式的博客转换成HTML
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

    /**
     * 根据tagId进行博客的查询
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * 博客归档
     * @return
     */
    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
