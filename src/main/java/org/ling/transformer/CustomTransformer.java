package org.ling.transformer;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.ling.interceptor.ZkPacketInterceptor;

public class CustomTransformer implements AgentBuilder.Transformer {
  @Override
  public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
    return builder.method(ElementMatchers.any()).intercept(MethodDelegation.to(ZkPacketInterceptor.class));
  }
}
