package com.hong.py.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.zookeeper
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/11/25 14:38
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/11/25 14:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 *
 * ZooKeeper原生支持通过注册Watcher来进行事件监听，但是其使用并不是特别方便，
 * 需要开发人员自己反复注册Watcher，比较繁琐。
 * Curator引入了Cache来实现对ZooKeeper服务端事件的监听。
 * Cache是Curator中对事件监听的包装，
 * 其对事件的监听其实可以近似看作是一个本地缓存视图和远程ZooKeeper视图的对比过程。
 * 同时Curator能够自动为开发人员处理反复注册监听，
 * 从而大大简化了原生API开发的繁琐过程。Cache分为两类监听类型：节点监听和子节点监听。
 *
 **/
public class CuratorWatcherDemo {

    //创建连接实体
    private CuratorFramework client=null;

    //监听数据节点的变化情况
    public static void main(String[] args) throws Exception {

        CuratorWatcherDemo watcher=new CuratorWatcherDemo();
        CuratorBase base=new CuratorBase();
        watcher.client=base.curatorClient();
        watcher.client.start();

        //NodeCacheTest(watcher);
        //PatchChildCacheTest(watcher);
        watcher.UsingWatcherTest(base);

        System.in.read();
    }

    //监听数据节点的变化情况
    public static void NodeCacheTest(CuratorWatcherDemo watcher) throws Exception {
        NodeCache nodeCache = new NodeCache(watcher.client,"/curator1");

        //该方法有个boolean类型的参数，默认是false，如果设置为true，
        // 那么NodeCache在第一次启动的时候就会立刻从ZooKeeper上读取对应节点的数据内容，
        // 并保存在Cache中
        nodeCache.start(true);
        if(nodeCache.getCurrentData()!=null){
            System.out.println("节点初始化数据为："+new String(nodeCache.getCurrentData().getData()));
        }else {
            System.out.println("节点数据为空！");
        }

        nodeCache.getListenable().addListener(() -> {
            String data = new String(nodeCache.getCurrentData().getData());
            System.out.println("节点路径："+nodeCache.getCurrentData().getPath()+"，节点数据为："+data);
        });
    }

    //监听子节点的变化情况
    public static void PatchChildCacheTest(CuratorWatcherDemo watcher) throws Exception {

        final PathChildrenCache childrenCache = new PathChildrenCache(watcher.client, "/curator1", true);
        /**
         * StartMode：初始化方式
         *  NORMAL：普通异步初始化
         BUILD_INITIAL_CACHE:同步初始化
         POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件
         */
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        List<ChildData> list = childrenCache.getCurrentData();
        System.out.println("获取子节点列表：");
        //如果是BUILD_INITIAL_CACHE可以获取这个数据，如果不是就不行
        list.forEach(childData -> {
            System.out.println(new String(childData.getData()));
        });

        //AtomicInteger atomicInteger = new AtomicInteger(0);
        childrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED: " + pathChildrenCacheEvent.getData().getPath());
                    System.out.println("CHILD_ADDED数据: " + new String(pathChildrenCacheEvent.getData().getData()));
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED: " + pathChildrenCacheEvent.getData().getPath());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED: " + pathChildrenCacheEvent.getData().getPath());
                    System.out.println("CHILD_UPDATED数据: " + new String(pathChildrenCacheEvent.getData().getData()));
                    break;
                default:
                    break;
            }
        });

        }

    //CuratorWatcher
    public  void UsingWatcherTest(CuratorBase base) throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/curatorEphemeral");
        client.getChildren().usingWatcher(new CuratorWatcherImpl()).forPath("/curatorEphemeral");
    }

    //创建CuratorWatcher
    private class CuratorWatcherImpl implements CuratorWatcher {
        @Override
        public void process(WatchedEvent event) throws Exception {
                String path = event.getPath() == null ? "" : event.getPath();
                System.out.println("触发了Watcher：事件路径"+event.getPath() +",事件类型"+event.getType());
                if(StringUtils.isNotEmpty(path))
                    client.getChildren().usingWatcher(this).forPath(path);
        }
    }
}
