package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    public ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);

        return new ResponseResult(true,200,"资源分页信息多条件查询完毕",pageInfo);
    }
}
