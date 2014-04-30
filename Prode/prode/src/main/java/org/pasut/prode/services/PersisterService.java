package org.pasut.prode.services;

import org.pasut.prode.entities.helpers.PersisterHelper;

import java.util.List;

/**
 * Created by marcelo on 12/04/14.
 */
public interface PersisterService {
    <T> T put(T object, PersisterHelper<T> helper);

    <T> List<T> findAll(PersisterHelper<T> helper);

    <T> void findAll(PersisterHelper<T> helper, FindCallback<List<T>> callback);

    public interface FindCallback<T> {
        void onFound(T object);
    }
}
