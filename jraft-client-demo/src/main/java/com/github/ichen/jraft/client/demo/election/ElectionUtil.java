package com.github.ichen.jraft.client.demo.election;

import com.alipay.remoting.util.StringUtils;
import com.alipay.sofa.jraft.Node;
import com.alipay.sofa.jraft.entity.PeerId;

/**
 * @author iChen
 * @since 2021-11-18
 */
public class ElectionUtil {
    /**
     * 当前节点是否是leader
     *
     * @return true or false
     */
    public static boolean isLeader() {
        return ElectionNode.getElectionNodeInstance().isLeader();
    }

    /**
     * 获取 leader ip
     *
     * @return 获取 leader ip, 当前无leader时返回空字符串
     */
    public static String getLeaderIp() {
        Node node = ElectionNode.getElectionNodeInstance().getNode();
        if (node == null) {
            return StringUtils.EMPTY;
        }
        PeerId leaderId = node.getLeaderId();
        if (leaderId == null) {
            return StringUtils.EMPTY;
        }
        return leaderId.getIp();
    }

    /**
     * 获取 leader 的端口
     *
     * @return leader 的端口, 无 leader时返回 -1
     */
    public static int getLeaderPort() {
        Node node = ElectionNode.getElectionNodeInstance().getNode();
        if (node == null) {
            return -1;
        }
        PeerId leaderId = node.getLeaderId();
        if (leaderId == null) {
            return -1;
        }
        return leaderId.getPort();
    }

    /**
     * @return 是否已启动
     */
    public static boolean isStarted() {
        return ElectionNode.getElectionNodeInstance().isStarted();
    }
}
