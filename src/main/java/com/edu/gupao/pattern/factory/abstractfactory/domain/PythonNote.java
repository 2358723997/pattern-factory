package com.edu.gupao.pattern.factory.abstractfactory.domain;

/**
 * PythonNote类
 *
 * @author wangjixue
 * @date 2020-06-27 17:21
 */
public class PythonNote implements Note{

    public void edit() {
        System.out.println("编写Python 笔记");
    }
}
