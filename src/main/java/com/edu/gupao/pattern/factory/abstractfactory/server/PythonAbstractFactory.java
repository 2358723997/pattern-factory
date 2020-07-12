package com.edu.gupao.pattern.factory.abstractfactory.server;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.abstractfactory.domain.PythonNote;
import com.edu.gupao.pattern.factory.abstractfactory.domain.PythonVideo;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Note;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Video;
import com.edu.gupao.pattern.factory.origin.server.PythonCourse;

/**
 * PythonCourseFactory接口
 *
 * @author wangjixue
 * @date 2020-06-27 17:26
 */
public class PythonAbstractFactory implements AbstractFactory {

    /**
     * 开通Python课程
     *
     * @return
     */
    public Course createCourse() {
        return new PythonCourse();
    }

    /**
     * 录制Python视频
     *
     * @return
     */
    public Video createVideo() {
        return new PythonVideo();
    }

    /**
     * 编写Python笔记
     *
     * @return
     */
    public Note createNote() {
        return new PythonNote();
    }
}
