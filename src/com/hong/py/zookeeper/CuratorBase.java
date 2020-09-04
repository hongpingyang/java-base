package com.hong.py.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.zookeeper
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/11/25 13:45
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/11/25 13:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CuratorBase {

    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    //ZooKeeper服务地址
    private static final String CONNECT_ADDR = "192.168.1.215:2181";

    //创建连接实体
    private  CuratorFramework client=null;

    public static void main(String[] args) {

        CuratorBase base=new CuratorBase();

        /*CuratorFrameworkFactory.Builder builder=CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(new RetryNTimes(1,1000))
                .namespace("hongpy");*/

        base.client = base.curatorClient();//builder.build();

        //3 开启连接
        base.client.start();

        System.out.println(base.client.getState());
        System.out.println(base.isConnected());

        //base.createPersistent("/curator1");
        //base.createPersistent("/curator1/1");
        //base.createPersistent("/curator1/2");
        //base.createPersistent("/curator1/3");

        base.createEphemeral("/curatorEphemeral");

        /*List<String> children = base.getChildren("/");
        for (String path : children) {
            System.out.println(path);
        }*/

        //base.setData("/curator1", "this is a test");

        base.setData("/curatorEphemeral", "this is a testcuratorEphemeral");

        //base.setData("/curator1/1", "this is a test1");

        //base.setData("/curator1/2", "this is a test2");
        //base.setData("/curator1/3", "this is a test3");


        byte[] data = base.getData("/curatorEphemeral");
        System.out.println(new String(data));

        //base.setData("/curator1/3", "this is a testfor 2");

        //base.delete("/curator1/2");

    }

    //创建连接实体
    public CuratorFramework curatorClient() {

        CuratorFrameworkFactory.Builder builder=CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(new RetryNTimes(1,1000))
                .namespace("hongpy");

        return builder.build();
    }

    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }

    public void doClose() {
        client.close();
    }

    //创建永久节点
    public void createPersistent(String path) {
        try {
            client.create().forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //创建永久有序节点
    public void createPersistentSequential(String path) {
        try {
            client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //创建临时节点
    public void createEphemeral(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //创建临时有序节点
    public void createEphemeralSequential(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //删除节点
    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //guaranteed()方法：如果服务端可能删除成功，
    // 但是client没有接收到删除成功的提示，
    // Curator将会在后台持续尝试删除该节点
    //deletingChildrenIfNeeded()方法：如果待删除节点存在子节点，
    // 则Curator将会级联删除该节点的子节点
    public void deleteGuaranteed(String path) {
        try {
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
        } catch (KeeperException.NoNodeException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //检测某个节点是否存在
    public boolean checkExists(String path) {
        try {
            if (client.checkExists().forPath(path) != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    //获取某个节点的所有子节点
    public List<String> getChildren(String path) {
        try {
            return client.getChildren().forPath(path);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //获取某个节点数据
    public byte[] getData(String path) {
        try {
            byte[] bytes = client.getData().forPath(path);
            return bytes;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    //设置某个节点的数据
    public void setData(String path, String data) {
        try {
            client.setData().forPath(path,data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
