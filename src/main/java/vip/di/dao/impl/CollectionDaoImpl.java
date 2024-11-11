package vip.di.dao.impl;

import vip.di.dao.CollectionDao;

import java.util.*;

public class CollectionDaoImpl implements CollectionDao {
    private int[] arr;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties properties;

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void save() {
        System.out.println(Arrays.toString(arr));
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
        System.out.println(properties);
    }
}
