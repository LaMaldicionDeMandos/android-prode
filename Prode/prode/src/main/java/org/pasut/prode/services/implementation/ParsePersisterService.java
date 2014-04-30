package org.pasut.prode.services.implementation;

import android.util.Log;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.pasut.prode.entities.helpers.PersisterHelper;
import org.pasut.prode.services.PersisterService;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

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

    @Override
    public <T> void findAll(final PersisterHelper<T> helper, final FindCallback<List<T>> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(helper.tableName());
        query.findInBackground(new com.parse.FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e != null) throw new RuntimeException(e);
                if (callback != null) callback.onFound(parseResult(parseObjects, helper));
            }
        });
    }

    @Override
    public <T> List<T> findAll(final PersisterHelper<T> helper) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(helper.tableName());
        try {
            List<ParseObject> parseList = query.find();
            return parseResult(parseList, helper);
        }catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> parseResult(List<ParseObject> parseList, final PersisterHelper<T> helper) {
        List<T> list = Lists.transform(parseList, new Function<ParseObject, T>() {
            @Nullable
            @Override
            public T apply(@Nullable ParseObject input) {
                return helper.fromMap(fromParse(input));
            }
        });
        return list;
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
