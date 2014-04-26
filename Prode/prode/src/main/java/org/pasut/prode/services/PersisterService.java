package org.pasut.prode.services;

import org.pasut.prode.entities.helpers.PersisterHelper;

/**
 * Created by marcelo on 12/04/14.
 */
public interface PersisterService {
    <T> T put(T object, PersisterHelper<T> helper);
}
