package com.edu.gupao.pattern.factory.abstractfactory.server;

import com.edu.gupao.pattern.factory.Course;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Note;
import com.edu.gupao.pattern.factory.abstractfactory.domain.Video;

/**
 * CourseFactory接口
 *
 * @author wangjixue
 * @date 2020-06-27 17:26
 */
public interface AbstractFactory {

    /**
     * 开通课程
     *
     * @return
     */
    public Course createCourse();

    /**
     * 录制视频
     *
     * @return
     */
    public Video createVideo();

    /**
     * 编写笔记
     *
     * @return
     */
    public Note createNote();
}
