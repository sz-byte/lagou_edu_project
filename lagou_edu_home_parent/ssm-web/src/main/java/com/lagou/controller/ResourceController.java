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

import java.util.Date;

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

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if(resource.getId() == null){
            //添加
            Date date = new Date();
            resource.setCreatedTime(date);
            resource.setUpdatedTime(date);
            resource.setCreatedBy("system");
            resource.setUpdatedBy("system");

            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"增加成功",null);
        }else {
            //修改
            resource.setUpdatedTime(new Date());
            resource.setUpdatedBy("system");

            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除成功",null);
    }
}
