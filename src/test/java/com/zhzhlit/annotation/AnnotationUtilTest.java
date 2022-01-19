package com.zhzhlit.annotation;

import cn.hutool.core.lang.Assert;
import java.util.List;
import junit.framework.TestCase;

public class AnnotationUtilTest extends TestCase {

  public void testListDeprecated() {

    List<? extends Enum<?>> objects = AnnotationUtil.listDeprecated(EnumAnnotation.class, true);
    Assert.isTrue(objects.contains(EnumAnnotation.DEPRECATED));
  }
}