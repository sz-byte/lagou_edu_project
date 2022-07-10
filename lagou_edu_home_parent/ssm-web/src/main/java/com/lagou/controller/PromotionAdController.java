package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*
    * 广告分页查询
    * */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> info = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", info);
        return responseResult;

    }

    /*
    * 图片上传
    * */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //3.获取元文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName= System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        String uploadPath = substring+"upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在，创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录："+filePath);
        }
        //图片进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }

    /*
    * 广告动态上下线
    * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(Integer id,Integer status){

        promotionAdService.updatePromotionAdStatus(id,status);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告动态上下线成功", null);
        return responseResult;
    }

    /*
    *新增或修改广告信息
    * */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if(promotionAd.getId() == null){
            //新增广告
            Date date = new Date();
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);
            promotionAdService.savePromotion(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else{
            promotionAd.setUpdateTime(new Date());
            promotionAdService.updatePromotion(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);
        }

    }

    /*
    * 根据id回显广告信息
    * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){

        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显广告成功", promotionAdById);
        return responseResult;
    }


}
