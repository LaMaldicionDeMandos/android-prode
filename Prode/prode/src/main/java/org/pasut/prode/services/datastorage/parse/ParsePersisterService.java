package org.pasut.prode.services.datastorage.parse;

import android.util.Log;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.pasut.prode.entities.helpers.PersisterHelper;
import org.pasut.prode.services.datastorage.PersistenceCriteria;
import org.pasut.prode.services.datastorage.PersisterService;

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
        findInBackground(helper, callback, query);
    }

    private <T> void findInBackground(final PersisterHelper<T> helper, final FindCallback<List<T>> callback, ParseQuery<ParseObject> query) {
        query.findInBackground(new com.parse.FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e != null) throw new RuntimeException(e);
                if (callback != null) callback.onFound(parseResult(parseObjects, helper));
            }
        });
    }

    @Override
    public <T> CriteriaBuilder getCriteriaBuilder(PersisterHelper<T> helper) {
        return new ParseCriteriaBuilder(ParseQuery.getQuery(helper.tableName()));
    }

    @Override
    public <T> List<T> find(PersisterHelper<T> helper, CriteriaBuilder criteriaBuilder) {
        validateParseCriteriaBuilder(criteriaBuilder);
        ParseQuery<ParseObject> query = buildParseQuery((ParseCriteriaBuilder) criteriaBuilder);
        return parse(helper, query);
    }

    private ParseQuery<ParseObject> buildParseQuery(ParseCriteriaBuilder builder) {
        PersistenceCriteria criteria = builder.build();
        criteria.apply();
        return builder.getQuery();
    }

    @Override
    public <T> void find(final PersisterHelper<T> helper, CriteriaBuilder criteriaBuilder, final FindCallback<List<T>> callback) {
        validateParseCriteriaBuilder(criteriaBuilder);
        ParseQuery<ParseObject> query = buildParseQuery((ParseCriteriaBuilder) criteriaBuilder);
        findInBackground(helper, callback, query);
    }

    private void validateParseCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        if (!(criteriaBuilder instanceof ParseCriteriaBuilder)) {
            throw new IllegalArgumentException("The CriteriaBuilder object must be a ParseCriteriaBuilder");
        }
    }

    @Override
    public <T> List<T> findAll(final PersisterHelper<T> helper) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(helper.tableName());
        return parse(helper, query);
    }

    private <T> List<T> parse(PersisterHelper<T> helper, ParseQuery<ParseObject> query) {
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
