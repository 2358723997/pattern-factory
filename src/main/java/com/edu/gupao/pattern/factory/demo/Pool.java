package com.edu.gupao.pattern.factory.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Pool类
 *
 * @author wangjixue
 * @date 2020-06-27 17:44
 */
public abstract class Pool {
    public static String POOL_CONFIG = "connection-inf.properties";
    /**
     * 定义唯一实例
     */
    public static Pool instance = null;
    /**
     * 驱动名称
     */
    protected String driverName = null;
    /**
     * 驱动
     */
    protected Driver driver = null;
    /**
     * 最大连接数
     */
    protected int maxConnect = 100;
    /**
     * 活跃连接数
     */
    protected int normalConnect = 10;

    /**
     * 私有构造方法
     *
     */
    protected Pool() {
        try {
            init();
            loadDrivers(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化所有从配置文件中读取成员变量
     *
     * @throws IOException
     */
    private void init() throws IOException {
        InputStream in = Pool.class.getResourceAsStream(POOL_CONFIG);
        Properties properties = new Properties();
        properties.load(in);
        this.driverName = properties.getProperty("driverName");
        this.maxConnect = Integer.parseInt(properties.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(properties.getProperty("normalConnect"));

    }

    /**
     * 装载或注册所有的JDBC驱动程序
     *
     * @param driverName
     */
    private void loadDrivers(String driverName) {
        String driverClassName = driverName;
        try {
            driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
            System.out.println("Success to register JDBC Driver: " + driverClassName);
        } catch (Exception e) {
            System.out.println("Failed to register JDBC Driver:" + driverClassName + "reason:" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 创建线程池
     */
    public abstract void createPool();

    /**
     * 获取数据库连接池实例(懒汉式单例)
     * 添加类锁保证线程安全
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static synchronized Pool getInstance() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (instance == null) {
            instance.init();
            instance = (Pool) Class.forName("org.e_book.sqlhelp.Pool").newInstance();

        }
        return instance;
    }

    /**
     * 获取一个可有连接，如果没有且连接小于最大连接时，创建一个
     *
     * @return
     */
    public abstract Connection getConnection();
    /**
     * 获取一个可有连接，如果没有且连接小于最大连接时，创建一个，注意有效期为毫秒
     *
     * @return
     */
    public abstract Connection getConnection(long expire);
    /**
     * 将连接对象返回给连接池
     *
     */
    public abstract void freeConnection(Connection connection);

    /**
     * 返回当前空闲连接数
     *
     * @return
     */
    public abstract int getNum();

    /**
     * 返回当前工作连接数
     * @return
     */
    public abstract int getActiveNum();

    /**
     * 关闭所有连接，撤销驱动注册
     */
    protected void release() {
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("Deregister driver is success.");
        } catch (Exception e) {
            System.out.println("Deregister driver is failed,reason:" + e.getMessage());
        }
    }

}
