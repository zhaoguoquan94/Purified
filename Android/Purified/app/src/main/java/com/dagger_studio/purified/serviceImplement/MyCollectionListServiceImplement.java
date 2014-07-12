package com.dagger_studio.purified.serviceImplement;

import com.dagger_studio.purified.service.MyCollectionListService;

import java.util.List;

/**
 * the Implementation from MyCollectionListService
 * Created by alex on 14-7-11.
 */
public class MyCollectionListServiceImplement implements MyCollectionListService {
    @Override
    public List<Object> getEverything() {
        return null;
    }

    @Override
    public List<Object> getAllTitle() {
        return null;
    }

    @Override
    public boolean deleteMyCollectionById(String id) {
        return false;
    }

    @Override
    public boolean markMyCollectionAsRead(String id) {
        return false;
    }

    @Override
    public boolean markMyCollectionAsUnread(String id) {
        return false;
    }
}
