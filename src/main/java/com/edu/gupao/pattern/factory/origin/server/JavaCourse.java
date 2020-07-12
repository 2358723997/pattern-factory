package com.edu.gupao.pattern.factory.origin.server;

import com.edu.gupao.pattern.factory.Course;

/**
 * JavaCourse类
 *
 * @author wangjixue
 * @date 2020-06-26 17:31
 */
public class JavaCourse implements Course {

    public void record() {
        System.err.println("Java录制课程。");
    }
}
