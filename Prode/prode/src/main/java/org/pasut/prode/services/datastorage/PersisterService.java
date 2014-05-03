package org.pasut.prode.services.datastorage;

import org.pasut.prode.entities.helpers.PersisterHelper;

import java.util.List;

/**
 * Created by marcelo on 12/04/14.
 */
public interface PersisterService {
    <T> T put(T object, PersisterHelper<T> helper);

    <T> List<T> findAll(PersisterHelper<T> helper);

    <T> void findAll(PersisterHelper<T> helper, FindCallback<List<T>> callback);

    <T> List<T> find(PersisterHelper<T> helper, CriteriaBuilder builder);

    <T> void find(PersisterHelper<T> helper, CriteriaBuilder builder, FindCallback<List<T>> callback);

    <T> CriteriaBuilder getCriteriaBuilder(PersisterHelper<T> helper);

    public interface CriteriaBuilder {
        <T> CriteriaBuilder withEqual(String attributeName, T value);
        <T> CriteriaBuilder withGt(String attributeName, T value);
        PersistenceCriteria build();
    }

    public interface FindCallback<T> {
        void onFound(T object);
    }
}
