package org.pasut.prode.services.datastorage.parse;

import com.parse.ParseQuery;

import org.pasut.prode.services.datastorage.PersistenceCriteria;

/**
 * Created by marcelo on 02/05/14.
 */
public abstract class ParseAbstractCriteria<T> implements PersistenceCriteria {
    protected final ParseQuery query;
    protected final String attName;
    protected final T value;

    public ParseAbstractCriteria(ParseQuery query, String attributeName, T value) {
        this.query = query;
        this.attName = attributeName;
        this.value = value;
    }
}
