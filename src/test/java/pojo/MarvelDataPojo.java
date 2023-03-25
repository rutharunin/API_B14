package pojo;

import java.util.List;

public class MarvelDataPojo {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<MarvelResultsPojo> results;


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MarvelResultsPojo> getResults() {
        return results;
    }

    public void setResults(List<MarvelResultsPojo> results) {
        this.results = results;
    }
}
