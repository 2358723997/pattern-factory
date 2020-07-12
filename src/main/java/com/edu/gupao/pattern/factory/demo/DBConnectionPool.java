package com.edu.gupao.pattern.factory.demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DBConnectionPool类
 *
 * @author wangjixue
 * @date 2020-07-12 18:34
 */
public final class DBConnectionPool extends Pool {
    private ReentrantLock lock = new ReentrantLock(true);

    private Queue<Connection> freeConnectionQueue = new ConcurrentLinkedQueue<Connection>();
    /**
     * 空闲连接数
     */
    private static int num = 0;
    /**
     * 正在使用的连接数
     */
    private static int checkedOut;
    /**
     * 当前可用的连接数
     */
    private int numActive = 0;
    /**
     * 连接底座
     */
    private String url;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 连接池实例变量
     */
    private static DBConnectionPool pool = null;

    private DBConnectionPool(){
        try {
            init();
            for (int i = 0; i < normalConnect; i++) {
                Connection connection = newConnection();
                if(connection != null){
                    freeConnectionQueue.add(connection);
                    num++;
                }
            }
        } catch (Exception e) {

        }
    }

    private void init() throws IOException {
        InputStream is = DBConnectionPool.class.getResourceAsStream(POOL_CONFIG);
        Properties properties = new Properties();
        properties.load(is);
        this.userName = properties.getProperty("userName");
        this.password = properties.getProperty("passWord");
        this.driverName = properties.getProperty("driverName");
        this.url = properties.getProperty("url");
        this.driverName = properties.getProperty("driverName");
        this.maxConnect = Integer.parseInt(properties.getProperty("maxConnect")); 
        this.normalConnect = Integer.parseInt(properties.getProperty("normalConnect"));
    }

    /**
     * 获取数据库连接池实例(懒汉式单例)
     * 添加类锁保证线程安全
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static synchronized Pool getInstance(){
        if (instance == null) {
            instance = new DBConnectionPool();

        }
        return instance;
    }


    public void createPool() {
        pool = new DBConnectionPool();
    }

    public Connection getConnection() {
        Connection connection = null;
        if (freeConnectionQueue.size() > 0) {
            num--;
            connection = freeConnectionQueue.peek();
            try {
                if (connection.isClosed()) {
                    //无效连接，再次获取
                    connection = getConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (maxConnect == 0 || checkedOut < maxConnect) {
            // 没有空闲连接且当前连接小于最大允许 值,最大值为 0 则不限制
            connection = newConnection();

        }
        if (connection != null) {
            checkedOut++;
        }
        numActive++;

        return connection;

    }

    private Connection newConnection() {
        Connection connection = null;
        try {
            if (userName == null) {
                connection = DriverManager.getConnection(url);
            } else {
                connection = DriverManager.getConnection(url, userName, password);
            }
        } catch (Exception e) {
            System.out.println("无法创建这个 URL 的连接" + url);
            return null;
        }
        return connection;
    }

    public Connection getConnection(long expire) {
        long startTime = new Date().getTime();
        Connection connection;
        while ((connection = getConnection()) == null) {
            try {
                wait(expire);
            } catch (InterruptedException e) {

            }
            if (new Date().getTime() - startTime > expire) {
                return null;
            }
        }
        return connection;
    }

    public void freeConnection(Connection connection) {
        lock.lock();
        try {
            freeConnectionQueue.add(connection);
            num++;
            checkedOut--;
            numActive--;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }

    public int getActiveNum() {
        return numActive;
    }

    @Override
    protected void release() {
        try {
            for (Connection connection : freeConnectionQueue) {
                try {
                    connection.isClosed();
                } catch (SQLException e) {
                    System.out.println("无法关闭连接池中的连接");
                }
            }
            freeConnectionQueue.removeAll(freeConnectionQueue);
            numActive = 0;
        } finally {
            super.release();
        }
    }
}
