package com.edu.gupao.pattern.factory.method.server;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;
import com.edu.gupao.pattern.factory.origin.server.PythonCourse;

import org.apache.commons.lang3.StringUtils;

/**
 * CourseFactory类
 *
 * @author wangjixue
 * @date 2020-06-26 18:30
 */
public interface CourseFactory {
    /**
     * 创建课程
     * @return
     */
    Course create();
}
