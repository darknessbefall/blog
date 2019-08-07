package com.cwb.web;

import com.cwb.pojo.Tag;
import com.cwb.service.BlogService;
import com.cwb.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String type(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,@PathVariable Long id,
                       Model model) {

        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1){
            id = tags.get(0).getId();
        }

        model.addAttribute("tags",tags );
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id );

        return "tags";
    }

}
