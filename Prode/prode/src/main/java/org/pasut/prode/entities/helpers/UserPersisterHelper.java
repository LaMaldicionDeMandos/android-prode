package org.pasut.prode.entities.helpers;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import org.pasut.prode.authentication.User;
import org.pasut.prode.authentication.UserType;

import java.util.Map;

/**
 * Created by marcelo on 12/04/14.
 */
@Singleton
public class UserPersisterHelper implements PersisterHelper<User> {
    private final static String TABLE_NAME = "user";
    private final static String _ID = "objectId";
    private final static String USER_ID = "userId";
    private final static String TYPE = "type";
    private final static String USER_NAME = "username";
    private final static String DEVICE = "device";
    private final static String NAME = "name";
    @Override
    public Map<String, Object> toMap(User user) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(_ID, user.getId());
        map.put(USER_ID, user.getUserId());
        map.put(TYPE, user.getType().toString());
        map.put(USER_NAME, user.getUsername());
        map.put(DEVICE, user.getDevice());
        map.put(NAME, user.getName());
        return map;
    }

    @Override
    public User fromMap(Map<String, Object> map) {
        User user = new User(
                map.get(_ID).toString(),
                map.get(USER_ID).toString(),
                map.get(USER_NAME).toString(),
                UserType.valueOf(map.get(TYPE).toString()),
                map.get(DEVICE).toString(),
                map.get(NAME).toString());
        return user;
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }
}
