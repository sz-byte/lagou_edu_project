package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
    * 根据课程ID查询对应的课程内容(章节+课时)
    * */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    /*
    * 回显章节对应的课程信息
    * */
    public Course findCourseByCourseId(int courseId);

    /*
     * 新增章节信息
     * */
    public void saveSection(CourseSection courseSection);

    /*
    * 更新章节信息
    * */
    public void updateSection(CourseSection courseSection);

    /*
    * 修改章节状态
    * */
    public void updateSectionStatus(int id,int status);

    /*
    * 保存课程信息
    * */
    public void saveLesson(CourseLesson courseLesson);

    /*
    * 修改课时信息
    * */
    public void updateLesson(CourseLesson courseLesson);

}
