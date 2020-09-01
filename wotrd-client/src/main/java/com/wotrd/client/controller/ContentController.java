package com.wotrd.client.controller;


import com.wotrd.client.model.entity.ContentDO;
import com.wotrd.client.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: ContentController
 * @Description: 内容控制类
 * @Author: wotrd
 * @Date: 2020/09/01 23:20
 */
@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("list")
    public List<ContentDO> queryAllContent(@RequestParam("categoryId") Long categoryId) {
        return contentService.queryAllContent(categoryId);

    }


}
