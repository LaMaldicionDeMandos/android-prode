package org.pasut.prode.entities.helpers;

import java.util.Map;

/**
 * Created by marcelo on 12/04/14.
 */
public interface PersisterHelper<T> {
    Map<String, Object> toMap(T object);
    T fromMap(Map<String, Object> map);
    String tableName();
}
