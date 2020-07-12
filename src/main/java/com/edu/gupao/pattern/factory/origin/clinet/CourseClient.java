package com.edu.gupao.pattern.factory.origin.clinet;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;
import com.edu.gupao.pattern.factory.origin.server.PythonCourse;

/**
 * CourseClient类
 *
 * @author wangjixue
 * @date 2020-06-26 17:34
 */
public class CourseClient {
    public static void main(String[] args) {
        Course course = new JavaCourse();
        course.record();

        course = new PythonCourse();
        course.record();
    }
}
