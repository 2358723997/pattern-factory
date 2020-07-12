package com.edu.gupao.pattern.factory.abstractfactory.domain;

/**
 * PythonVideo类
 *
 * @author wangjixue
 * @date 2020-06-27 17:21
 */
public class PythonVideo implements Video {

    public void record() {
        System.out.println("录制 Python 视频");
    }
}
