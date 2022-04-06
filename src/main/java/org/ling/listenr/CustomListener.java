package org.ling.listenr;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

public class CustomListener implements AgentBuilder.Listener {
  @Override
  public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

  }

  @Override
  public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

  }

  @Override
  public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

  }

  @Override
  public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

  }

  @Override
  public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

  }
}
