package com.example.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by suclogger on 16/5/23.
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {

    public static <T> T createCloneBean(Object obi, Class<T> destClaz) {
        try {
            T destObj = destClaz.newInstance();
            BeanUtils.copyProperties(obi, destObj);
            return destObj;
        } catch (Exception e) {
            throw new RuntimeException("Copy prop error.", e);
        }
    }

    public static <T> T createCloneBean(Object obi, Class<T> destClaz, String... ignoreProperties) {
        try {
            T destObj = destClaz.newInstance();
            BeanUtils.copyProperties(obi, destObj, ignoreProperties);
            return destObj;
        } catch (Exception e) {
            throw new RuntimeException("Copy prop error.", e);
        }
    }

    public static Object copyPorp2DO(Class<?> destClaz, Object obi) throws RuntimeException {
        try {
            Object destObj = destClaz.newInstance();
            BeanUtils.copyProperties(obi, destObj);
            return destObj;
        } catch (Exception e) {
            throw new RuntimeException("Copy prop error.", e);
        }
    }

    public static void copyProperties(Object originObj, Object destObj, String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(originObj, destObj, ignoreProperties);
        } catch (Exception e) {
            throw new RuntimeException("Copy prop error.", e);
        }
    }

    public static void copyPropertiesIgnoreNull(Object originObj, Object destObj) {
        try {
            BeanUtils.copyProperties(originObj, destObj, getNullPropertyNames(originObj));
        } catch (Exception e) {
            throw new RuntimeException("Copy prop error.", e);
        }
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
