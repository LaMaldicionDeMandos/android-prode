package org.pasut.prode.entities.helpers;

import com.google.inject.Singleton;

import org.pasut.prode.entities.Fixture;

import java.util.Map;

/**
 * Created by marcelo on 26/04/14.
 */
@Singleton
public class FixturePersisterHelper implements PersisterHelper<Fixture> {
    @Override
    public Map<String, Object> toMap(Fixture object) {
        return null;
    }

    @Override
    public Fixture fromMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String tableName() {
        return null;
    }
}
