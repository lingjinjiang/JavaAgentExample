package org.ling.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.apache.jute.BinaryInputArchive;
import org.apache.zookeeper.proto.RequestHeader;
import org.apache.zookeeper.server.ByteBufferInputStream;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerCnxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ZkPacketInterceptor {
  private static final Logger LOG = LoggerFactory.getLogger(ZkPacketInterceptor.class);

  private static final Map<Integer, String> packetMap = new HashMap<>();

  static {
    packetMap.put(0, "notification");
    packetMap.put(1, "create");
    packetMap.put(2, "delete");
    packetMap.put(3, "exists");
    packetMap.put(4, "getData");
    packetMap.put(5, "setData");
    packetMap.put(6, "getACL");
    packetMap.put(7, "setACL");
    packetMap.put(8, "getChildren");
    packetMap.put(9, "sync");
    packetMap.put(11, "ping");
    packetMap.put(12, "getChildren2");
    packetMap.put(13, "check");
    packetMap.put(14, "multi");
    packetMap.put(15, "create2");
    packetMap.put(16, "reconfig");
    packetMap.put(17, "checkWatches");
    packetMap.put(18, "removeWatches");
    packetMap.put(19, "createContainer");
    packetMap.put(20, "deleteContainer");
    packetMap.put(21, "createTTL");
    packetMap.put(22, "multiRead");
    packetMap.put(100, "auth");
    packetMap.put(101, "setWatches");
    packetMap.put(102, "sasl");
    packetMap.put(103, "getEphemerals");
    packetMap.put(104, "getAllChildrenNumber");
    packetMap.put(105, "setWatches2");
    packetMap.put(106, "addWatch");
    packetMap.put(107, "whoAmI");
    packetMap.put(-10, "createSession");
    packetMap.put(-11, "closeSession");
    packetMap.put(-1, "error");
  }

  @RuntimeType
  public static Object intercept(@Origin Method method,
                                 @SuperCall Callable<?> callable,
                                 @AllArguments Object args[]) throws Exception {
    if ("submitRequest".equals(method.getName()) && args != null && args.length == 1) {
      Request req = (Request)args[0];
      String type = packetMap.get(req.type);
      if (type == null || type.length() == 0) {
        type = "unknown";
      }
      LOG.info("========= Process packet: session id {}, address {}, packet type {}({})",
              req.getConnection().getSessionId(), req.getConnection().getRemoteSocketAddress().toString(), type, req.type);
    }
    return callable.call();
  }


}
