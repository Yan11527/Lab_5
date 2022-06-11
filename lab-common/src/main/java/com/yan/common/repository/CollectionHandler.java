package com.yan.common.repository;

import com.yan.common.element.StudyGroup;
import com.yan.common.util.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * Collection handling and serving class
 */
public final class CollectionHandler {

    private Map<Integer, StudyGroup> groups;
    private final File file;
    private final Date initDate;
    private Integer idSeq = 1;

    public CollectionHandler(File file) throws FileNotFoundException {
        FileHandler.checkFile(file);
        this.file = file;
        initDate = new Date();
    }

    public void addAll(Map<Integer, StudyGroup> map) {
        groups = map;
        Integer maxId = 0;
        for (StudyGroup sg: groups.values()) {
            if (sg.getId() > maxId) {
                maxId = sg.getId();
            }
        }
        idSeq = maxId + 1;
    }

    public Integer getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(Integer idSeq) {
        this.idSeq = idSeq;
    }

    public Map<Integer, StudyGroup> getGroups() {
        return groups;
    }

    public int getSize() {
        return groups.size();
    }

    public Date getInitDate() {
        return initDate;
    }

    /**
     * Sorting map by studentsCount value in ascending order
     * @return naturally (by comparable) sorted TreeMap
     */
    public Map<Integer, StudyGroup> sortedByStudentsCount() {
        return new TreeMap<>(groups);
    }

    public void clear() {
        groups.clear();
    }

    public boolean isEmpty() {
        return groups.isEmpty();
    }

    public boolean containsKey(Integer key) {
        return groups.containsKey(key);
    }

    public void add(Integer key, StudyGroup value) {
        groups.put(key, value);
        idSeq++;
    }

    public StudyGroup removeByKey(Integer key) {
        return groups.remove(key);
    }

    public int removeLowerKey(Integer key) {
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> entry: groups.entrySet()) {
            if (entry.getKey() < key) {
                keys.add(entry.getKey());
            }
        }
        for (Integer i: keys) {
            removeByKey(i);
        }
        return keys.size();
    }

    public StudyGroup getByKey(Integer key) {
        return groups.get(key);
    }

    public Map<Integer, StudyGroup> filterContainsName(String sub) {
        Map<Integer, StudyGroup> filtered = new HashMap<>();
        for (Map.Entry<Integer, StudyGroup> entry: groups.entrySet()) {
            if (entry.getValue().getName().contains(sub)) {
                filtered.put(entry.getKey(), entry.getValue());
            }
        }
        return filtered;
    }

    public int removeByStudentsCount(Integer count) {
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> entry: groups.entrySet()) {
            if (entry.getValue().getStudentsCount().equals(count)) {
                keys.add(entry.getKey());
            }
        }
        for (Integer i: keys) {
            removeByKey(i);
        }
        return keys.size();
    }

    public boolean replaceIfLower(Integer key, StudyGroup newSG) {
        StudyGroup replaced = getByKey(key);
        if (replaced.compareTo(newSG) <= 0) {
            return false;
        }
        add(key, newSG);
        return true;
    }

    public void saveCollection() throws FileNotFoundException {
        FileHandler.writeFile(file, groups);
    }

}
