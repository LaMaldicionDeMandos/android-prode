package org.pasut.prode.services.implementation;

import android.util.Log;

import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseObject;

import org.pasut.prode.entities.helpers.PersisterHelper;
import org.pasut.prode.services.PersisterService;

import java.util.Map;

/**
 * Created by marcelo on 12/04/14.
 */
public class ParsePersisterService implements PersisterService {
    private final static String TAG = ParsePersisterService.class.getSimpleName();
    private final static String PARSE_ID = "objectId";
    @Override
    public <T> T put(T object, PersisterHelper<T> helper) {
        ParseObject parse = toParse(helper.tableName(), helper.toMap(object));
        try {
            parse.save();
            Log.d(TAG, "Parse ObjectId: " + parse.getObjectId());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return helper.fromMap(fromParse(parse));
    }

    private static ParseObject toParse(String tableName, Map<String, Object> map) {
        ParseObject parse = new ParseObject(tableName);
        for (String key : map.keySet()) {
            if (PARSE_ID.equals(key)) continue;
            parse.put(key, map.get(key));
        }
        return parse;
    }

    private static Map<String, Object> fromParse(ParseObject parse) {
        Map<String, Object> map = Maps.newHashMap();
        for (String key : parse.keySet()) {
            map.put(key, parse.get(key));
        }
        map.put(PARSE_ID, parse.getObjectId());
        return map;
    }
}
