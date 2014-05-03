package org.pasut.prode.services.datastorage.parse;

import com.parse.ParseQuery;

/**
 * Created by marcelo on 02/05/14.
 */
public class ParseEqualsCriteria<T> extends ParseAbstractCriteria<T> {
    public ParseEqualsCriteria(ParseQuery query, String attributeName, T value) {
        super(query, attributeName, value);
    }

    @Override
    public void apply() {
        query.whereEqualTo(attName, value);
    }
}
