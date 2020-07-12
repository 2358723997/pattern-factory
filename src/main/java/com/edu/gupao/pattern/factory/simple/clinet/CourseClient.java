package com.edu.gupao.pattern.factory.simple.clinet;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;
import com.edu.gupao.pattern.factory.origin.server.PythonCourse;
import com.edu.gupao.pattern.factory.simple.CourseFactory;

/**
 * CourseClient类
 *
 * @author wangjixue
 * @date 2020-06-26 17:34
 */
public class CourseClient {
    public static void main(String[] args) {
        //TODO version01
        CourseFactory courseFactory = new CourseFactory();

        Course course = courseFactory.create_v1("Java");
        course.record();

        course = courseFactory.create_v1("Python");
        course.record();
        //TODO version02
        course = CourseFactory.create_v2("com.edu.gupao.pattern.factory.origin.server.JavaCourse");
        course.record();
        course = CourseFactory.create_v2("com.edu.gupao.pattern.factory.origin.server.PythonCourse");
        course.record();

        //TODO version03
        course = CourseFactory.create_v3(JavaCourse.class);
        course.record();
        course = CourseFactory.create_v3(PythonCourse.class);
        course.record();
    }
}
