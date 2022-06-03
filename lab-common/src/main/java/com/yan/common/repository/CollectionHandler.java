package com.yan.common.repository;

import com.yan.common.element.StudyGroup;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Collection handling and serving class
 */
public final class CollectionHandler {

    private static Map<Integer, StudyGroup> groups;
    private static Date initDate;

    private CollectionHandler() {
    }

    public static Map<Integer, StudyGroup> getGroups() {
        return groups;
    }

    public static void setGroups(Map<Integer, StudyGroup> groups) {
        CollectionHandler.groups = groups;
    }

    public static void setInitDate(Date initDate) {
        CollectionHandler.initDate = initDate;
    }

    public static int getSize() {
        return groups.size();
    }

    public static Date getInitDate() {
        return initDate;
    }

    /**
     * Sorting map by studentsCount value in ascending order
     * @return naturally (by comparable) sorted TreeMap
     */
    public static Map<Integer, StudyGroup> sortedByStudentsCount() {
        return new TreeMap<>(groups);
    }

    public static void clear() {
        groups.clear();
    }

    public static boolean isEmpty() {
        return groups.isEmpty();
    }

    public static boolean containsKey(Integer key) {
        return groups.containsKey(key);
    }

    public static void add(Integer key, StudyGroup value) {
        groups.put(key, value);
    }

    public static StudyGroup removeByKey(Integer key) {
        return groups.remove(key);
    }

    public static StudyGroup getByKey(Integer key) {
        return groups.get(key);
    }

}
