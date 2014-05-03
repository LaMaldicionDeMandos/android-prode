package org.pasut.prode.services.datastorage.parse;

import com.google.common.collect.Lists;
import com.parse.ParseQuery;

import org.pasut.prode.services.datastorage.PersistenceAndCriteria;
import org.pasut.prode.services.datastorage.PersistenceCriteria;
import org.pasut.prode.services.datastorage.PersisterService;

import java.util.List;

/**
 * Created by marcelo on 03/05/14.
 */
public class ParseCriteriaBuilder implements PersisterService.CriteriaBuilder {
    private final ParseQuery query;
    private final List<PersistenceCriteria> criterias = Lists.newArrayList();

    public ParseCriteriaBuilder(ParseQuery query) {
        this.query = query;
    }
    @Override
    public <T> PersisterService.CriteriaBuilder withEqual(String attributeName, T value) {
        ParseEqualsCriteria<T> criteria = new ParseEqualsCriteria<T>(query, attributeName, value);
        criterias.add(criteria);
        return this;
    }

    @Override
    public <T> PersisterService.CriteriaBuilder withGt(String attributeName, T value) {
        ParseGtCriteria<T> criteria = new ParseGtCriteria<T>(query, attributeName, value);
        criterias.add(criteria);
        return this;
    }

    @Override
    public PersistenceCriteria build() {
        PersistenceAndCriteria mainCriteria = new PersistenceAndCriteria();
        mainCriteria.add(criterias);
        return mainCriteria;
    }

    public ParseQuery getQuery() {
        return query;
    }
}
