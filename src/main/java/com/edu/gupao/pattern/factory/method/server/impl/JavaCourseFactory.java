package com.edu.gupao.pattern.factory.method.server.impl;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.method.server.CourseFactory;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;

/**
 * JavaCourseFactoryImpl类
 *
 * @author wangjixue
 * @date 2020-06-27 16:38
 */
public class JavaCourseFactory implements CourseFactory {

    public Course create() {
        return new JavaCourse();
    }
}
