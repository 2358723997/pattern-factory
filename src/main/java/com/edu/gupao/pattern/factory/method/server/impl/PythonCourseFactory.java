package com.edu.gupao.pattern.factory.method.server.impl;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.method.server.CourseFactory;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;
import com.edu.gupao.pattern.factory.origin.server.PythonCourse;

/**
 * JavaCourseFactoryImpl类
 *
 * @author wangjixue
 * @date 2020-06-27 16:38
 */
public class PythonCourseFactory implements CourseFactory {

    public Course create() {
        return new PythonCourse();
    }
}
