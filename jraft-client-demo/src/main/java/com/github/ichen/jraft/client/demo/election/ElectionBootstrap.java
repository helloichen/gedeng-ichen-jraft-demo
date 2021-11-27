/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ichen.jraft.client.demo.election;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jiachun.fjc
 */
@Component
@Slf4j
public class ElectionBootstrap {

    @Value("${jraft.dataPath}")
    private String dataPath;

    @Value("${jraft.groupId}")
    private String groupId;

    @Value("${jraft.serverId}")
    private String serverId;

    @Value("${jraft.initConf}")
    private String initConf;

    // Start elections by 3 instance. Note that if multiple instances are started on the same machine,
    // the first parameter `dataPath` should not be the same.
/*    public static void main(final String[] args) {
        if (args.length < 4) {
            System.out
                    .println("Useage : java com.github.ichen.jraft.client.demo.election.ElectionBootstrap {dataPath} {groupId} {serverId} {initConf}");
            System.out
                    .println("Example: java com.github.ichen.jraft.client.demo.election.ElectionBootstrap /tmp/server1 election_test 127.0.0.1:8081 127.0.0.1:8081,127.0.0.1:8082,127.0.0.1:8083");
            System.exit(1);
        }
        final String dataPath = args[0];
        final String groupId = args[1];
        final String serverIdStr = args[2];
        final String initialConfStr = args[3];

        final ElectionNodeOptions electionOpts = new ElectionNodeOptions();
        electionOpts.setDataPath(dataPath);
        electionOpts.setGroupId(groupId);
        electionOpts.setServerAddress(serverIdStr);
        electionOpts.setInitialServerAddressList(initialConfStr);

        final ElectionNode node = new ElectionNode();
        node.addLeaderStateListener(new LeaderStateListener() {

            @Override
            public void onLeaderStart(long leaderTerm) {
                PeerId serverId = node.getNode().getLeaderId();
                String ip = serverId.getIp();
                int port = serverId.getPort();
                System.out.println("[ElectionBootstrap] Leader's ip is: " + ip + ", port: " + port);
                System.out.println("[ElectionBootstrap] Leader start on term: " + leaderTerm);
            }

            @Override
            public void onLeaderStop(long leaderTerm) {
                System.out.println("[ElectionBootstrap] Leader stop on term: " + leaderTerm);
            }
        });
        node.init(electionOpts);
    }*/

    @PostConstruct
    public void init() throws Exception {
        log.info(dataPath);
        log.info(groupId);
        log.info(serverId);
        log.info(initConf);
        final ElectionNodeOptions electionOpts = new ElectionNodeOptions();
        electionOpts.setDataPath(dataPath);
        electionOpts.setGroupId(groupId);
        electionOpts.setServerAddress(serverId);
        electionOpts.setInitialServerAddressList(initConf);

        final ElectionNode node = ElectionNode.getElectionNodeInstance();
        node.addLeaderStateListener(new LeaderStateListener() {

            @Override
            public void onLeaderStart(long leaderTerm) {
                System.out.println("[ElectionBootstrap] Leader is: " + serverId);
                System.out.println("[ElectionBootstrap] Leader start on term: " + leaderTerm);
            }

            @Override
            public void onLeaderStop(long leaderTerm) {
                System.out.println("[ElectionBootstrap] Leader stop on term: " + leaderTerm);
            }
        });
        node.init(electionOpts);
    }
}
