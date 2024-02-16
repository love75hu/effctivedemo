package com.example.effctivedemo.example4;

import java.util.*;

/*
 *@title EnumAndAnnotation3_用EnumMap替代序数索引
 *@description
 *@author thj
 *@create 2024-02-15
 */
public class EnumAndAnnotation4_用EnumMap替代序数索引 {
    enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    final String name;
    final LifeCycle lifeCycle;

    public EnumAndAnnotation4_用EnumMap替代序数索引(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    void example(){
        Map<EnumAndAnnotation4_用EnumMap替代序数索引.LifeCycle, Set<EnumAndAnnotation4_用EnumMap替代序数索引>> plantsByLifeCycle =
                new EnumMap<>(EnumAndAnnotation4_用EnumMap替代序数索引.LifeCycle.class);
        for (EnumAndAnnotation4_用EnumMap替代序数索引.LifeCycle lc : EnumAndAnnotation4_用EnumMap替代序数索引.LifeCycle.values()){
            plantsByLifeCycle.put(lc, new HashSet<>());
        }
    }
}
