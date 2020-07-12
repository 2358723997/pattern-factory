package com.edu.gupao.pattern.factory.abstractfactory.server;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.abstractfactory.domain.JavaNote;
import com.edu.gupao.pattern.factory.abstractfactory.domain.JavaVideo;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Note;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Video;
import com.edu.gupao.pattern.factory.origin.server.JavaCourse;

/**
 * JavaCourseFactory接口
 *
 * @author wangjixue
 * @date 2020-06-27 17:26
 */
public class JavaAbstractFactory implements AbstractFactory {

    /**
     * 开通Java课程
     *
     * @return
     */
    public Course createCourse() {
        return new JavaCourse();
    }

    /**
     * 录制Java视频
     *
     * @return
     */
    public Video createVideo() {
        return new JavaVideo();
    }

    /**
     * 编写Java笔记
     *
     * @return
     */
    public Note createNote() {
        return new JavaNote();
    }
}
