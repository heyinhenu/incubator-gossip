/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.gossip.manager.handlers;

import org.apache.gossip.manager.GossipCore;
import org.apache.gossip.manager.GossipManager;
import org.apache.gossip.model.Base;
import org.apache.gossip.model.PerNodeDataMessage;
import org.apache.gossip.model.ShutdownMessage;

public class ShutdownMessageHandler implements MessageHandler {

    /**
     * @param gossipCore    context.
     * @param gossipManager context.
     * @param base          message reference.
     * @return boolean indicating success.
     */
    @Override
    public boolean invoke(GossipCore gossipCore, GossipManager gossipManager, Base base) {
        ShutdownMessage s = (ShutdownMessage) base;
        PerNodeDataMessage m = new PerNodeDataMessage();
        m.setKey(ShutdownMessage.PER_NODE_KEY);
        m.setNodeId(s.getNodeId());
        m.setPayload(base);
        m.setTimestamp(System.currentTimeMillis());
        m.setExpireAt(System.currentTimeMillis() + 30L * 1000L);
        gossipCore.addPerNodeData(m);
        return true;
    }
}
