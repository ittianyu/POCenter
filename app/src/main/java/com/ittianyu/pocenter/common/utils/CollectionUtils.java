package com.ittianyu.pocenter.common.utils;

import java.util.Collection;

/**
 * Created by yu on 2017/1/17.
 */
public class CollectionUtils {
    /**
     * check the collection is empty
     * @param collection
     * @return true if collection == null or size() == 0
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.isEmpty())
            return true;
        return false;
    }
}
