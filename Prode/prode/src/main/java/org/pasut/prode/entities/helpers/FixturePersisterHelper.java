package org.pasut.prode.entities.helpers;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import org.pasut.prode.entities.Fixture;

import java.util.Date;
import java.util.Map;

/**
 * Created by marcelo on 26/04/14.
 */
@Singleton
public class FixturePersisterHelper implements PersisterHelper<Fixture> {
    private final static String TABLE_NAME = "Fixture";
    public final static String _ID = "objectId";
    public final static String NAME = "name";
    public final static String DESCRIPTION = "description";
    public final static String CLOSE_DATE = "to";
    @Override
    public Map<String, Object> toMap(Fixture fixture) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(_ID, fixture.getId());
        map.put(NAME, fixture.getName());
        map.put(DESCRIPTION, fixture.getDescription());
        map.put(CLOSE_DATE, fixture.getCloseDate());
        return map;
    }

    @Override
    public Fixture fromMap(Map<String, Object> map) {
        return new Fixture(
                (String)map.get(_ID),
                (String)map.get(NAME),
                (String)map.get(DESCRIPTION),
                (Date)map.get(CLOSE_DATE));
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
