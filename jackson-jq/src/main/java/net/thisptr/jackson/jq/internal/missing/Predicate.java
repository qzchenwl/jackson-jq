// Copyright (C) 2018 Meituan
// All rights reserved
package net.thisptr.jackson.jq.internal.missing;

/**
 * @author chenwenlong
 * @version 1.0
 * created 2018/7/18 下午3:00
 **/
public interface Predicate<T> {
    boolean test(T t);
}
