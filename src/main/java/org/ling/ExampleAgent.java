package org.ling;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;
import org.ling.interceptor.ZkPacketInterceptor;
import org.ling.listenr.CustomListener;
import org.ling.transformer.CustomTransformer;

import java.lang.instrument.Instrumentation;

public class ExampleAgent {
  public static void premain(String agentArgs, Instrumentation inst) {
    ExampleAgent agent = new ExampleAgent();
    agent.run(agentArgs, inst);
  }

  private void run(String agentArgs, Instrumentation inst) {
    CustomListener listener = new CustomListener();
    CustomTransformer transformer = new CustomTransformer();

    new AgentBuilder.Default().type(ElementMatchers.<TypeDescription>named("org.apache.zookeeper.server.ZooKeeperServer"))
            .transform(transformer).with(listener).installOn(inst);
  }
}
