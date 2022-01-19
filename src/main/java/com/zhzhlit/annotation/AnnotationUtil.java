package com.zhzhlit.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnnotationUtil {

  /**
   * 获取枚举的所有已过期字段
   *
   * @param enumClass     枚举的类
   * @param deprecateFlag 是否过期表示 true获取所有过期的 false获取所有未过期的
   * @return
   */
  public static List<? extends Enum<?>> listDeprecated(Class<? extends Enum<?>> enumClass,
      boolean deprecateFlag) {

    //所有枚举常量值
    Enum<?>[] enumConstants = enumClass.getEnumConstants();

    //<名字，对应的枚举值>
    Map<String, ? extends Enum<?>> maps = Arrays.stream(enumConstants)
        .collect(Collectors.toMap(Enum::name, x -> x));

    //获取枚举的所有字段
    for (Field field : enumClass.getFields()) {
      boolean annotationPresent = field.isAnnotationPresent(Deprecated.class);

      if (
          (deprecateFlag && !annotationPresent)//true获取所有过期的 把不过期的删掉
              || (!deprecateFlag && annotationPresent)// false获取所有未过期的 把过期的删掉
      ) {
        maps.remove(field.getName());
      }
    }

    return new ArrayList<>(maps.values());
  }
}
