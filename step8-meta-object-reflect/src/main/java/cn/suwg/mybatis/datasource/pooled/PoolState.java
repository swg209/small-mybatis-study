package cn.suwg.mybatis.datasource.pooled;


import java.util.ArrayList;
import java.util.List;

/**
 * 链接池状态.
 *
 * @Author: suwg
 * @Date: 2024/12/16
 */
public class PoolState {

    //资源
    protected PooledDataSource dataSource;

    //空闲链接
    protected final List<PooledConnection> idleConnections = new ArrayList<>();

    //活跃链接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();

    //请求次数
    protected long requestCount = 0;

    //总请求时间
    protected long accumulatedRequestTime = 0;

    //总检出时间
    protected long accumulatedCheckoutTime = 0;

    //请求超时次数
    protected long claimedOverdueConnectionCount = 0;

    //请求超时时间
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;

    //总等待时间
    protected long accumulatedWaitTime = 0;

    //要等待的次数
    protected long hadToWaitCount = 0;

    //失败连接次数
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }


    // 获取指标get方法.

    public synchronized long getRequestCount() {
        return requestCount;
    }

    public synchronized long getAverageRequestTime() {
        return requestCount == 0 ? 0 : accumulatedRequestTime / requestCount;
    }

    public synchronized long getAverageWaitTime() {
        return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;
    }

    public synchronized long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public synchronized long getBadConnectionCount() {
        return badConnectionCount;
    }

    public synchronized long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public synchronized long getAverageOverdueCheckoutTime() {
        return claimedOverdueConnectionCount == 0 ? 0 : accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }

    public synchronized long getAverageCheckoutTime() {
        return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
    }

    public synchronized int getIdleConnectionCount() {
        return idleConnections.size();
    }

    public synchronized int getActiveConnectionCount() {
        return activeConnections.size();
    }


}
