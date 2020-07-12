package com.edu.gupao.pattern.factory.abstractfactory.client;

import com.edu.gupao.pattern.factory.abstractfactory.server.AbstractFactory;
import com.edu.gupao.pattern.factory.abstractfactory.server.JavaAbstractFactory;
import com.edu.gupao.pattern.factory.abstractfactory.server.PythonAbstractFactory;

/**
 * AbstractFactroyClient类
 *
 * @author wangjixue
 * @date 2020-06-27 17:35
 */
public class AbstractFactroyClient {
    public static void main(String[] args) {
        AbstractFactory factory = new JavaAbstractFactory();
        factory.createVideo().record();
        factory.createNote().edit();
        factory.createCourse().record();

        factory = new PythonAbstractFactory();
        factory.createVideo().record();
        factory.createNote().edit();
        factory.createCourse().record();
    }
}
