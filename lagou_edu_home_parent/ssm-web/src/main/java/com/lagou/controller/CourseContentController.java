package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        //调用service层
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);
        return responseResult;
    }

    /*
    * 回显章节对应的课程信息
    * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return responseResult;

    }

    /*
    * 新增章节信息
    * */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        //判断是否携带了章节id
        if (courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        }else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"更新章节成功",null);
        }

    }

    /*
    * 修改章节状态
    * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);
        //数据响应
        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态", map);
        return responseResult;
    }

    /*
    * 保存&修改课时
    * */
    @RequestMapping("/saveLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson){

        courseContentService.saveLesson(lesson);
        return new ResponseResult(true,200,"响应成功",null);

    }
}
