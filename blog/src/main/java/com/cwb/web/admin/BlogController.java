package com.cwb.web.admin;

import com.cwb.pojo.Blog;
import com.cwb.pojo.Type;
import com.cwb.pojo.User;
import com.cwb.service.BlogService;
import com.cwb.service.TagService;
import com.cwb.service.TypeService;
import com.cwb.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blog(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                   Pageable pageable, BlogQuery blog, Model model) {

        List<Type> types = typeService.listType();
        //获取所有类型的数据
        model.addAttribute("types", types);

        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                               Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    /**
     * 新增博客页面初始化
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input (Model model) {
        setTypeAndTag(model);

        model.addAttribute("blog",new Blog());

        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        //获取所有类型的数据
        model.addAttribute("types", typeService.listType());
        //获取所有标签的数据
        model.addAttribute("tags",  tagService.listTag());
    }

    /**
     * 编辑博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput (@PathVariable Long id , Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);

        return INPUT;
    }

    /**
     * 新增或修改操作
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        }else {
            b= blogService.updateBlog(blog.getId(),blog );
        }

        if (b == null) {
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }

        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;

    }

}
