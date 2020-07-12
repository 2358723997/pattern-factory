package com.edu.gupao.pattern.factory.method.clinet;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.method.server.CourseFactory;
import com.edu.gupao.pattern.factory.method.server.impl.JavaCourseFactory;
import com.edu.gupao.pattern.factory.method.server.impl.PythonCourseFactory;

/**
 * CourseClient类
 *
 * @author wangjixue
 * @date 2020-06-26 17:34
 */
public class CourseClient {
    public static void main(String[] args) {
        CourseFactory factory = new JavaCourseFactory();
        Course course = factory.create();
        course.record();

        factory = new PythonCourseFactory();
        course = factory.create();
        course.record();
    }
}
