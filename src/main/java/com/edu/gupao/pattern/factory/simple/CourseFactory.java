package com.edu.gupao.pattern.factory.simple;

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
public class CourseFactory {
    private static final String JAVA_COURSE_NAME = "Java";
    private static final String PYTHON_COURSE_NAME = "Python";

    /**
     * TODO verson01
     *
     * @param name
     * @return
     */
    public Course create_v1(String name) {
        Course course = null;
        if (JAVA_COURSE_NAME.equals(name)) {
            course = new JavaCourse();
        } else if (PYTHON_COURSE_NAME.equals(name)) {
            course = new PythonCourse();
        }
        return course;
    }


    /**
     * TODO version02
     * @param name
     * @return
     */
    public static Course create_v2(String name){
        Course course = null;
        try {
            if(!(null == name || StringUtils.isEmpty(name))){
                course = (Course)Class.forName(name).newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return course;
    }

    /**
     * TODO version03
     * @param clazz
     * @return
     */
    public static Course create_v3(Class<? extends Course> clazz){
        Course course = null;
        try {
            if(!(null == clazz)){
                course = (Course)clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return course;
    }
}
