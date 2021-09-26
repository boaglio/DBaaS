package com.boaglio.dbaas.exception;

public class QueryException extends Exception {

    private static final long serialVersionUID = 4774480625796609536L;

    private String query;

    public QueryException(final String error, final String query) {
        super(error);
        this.query = query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
