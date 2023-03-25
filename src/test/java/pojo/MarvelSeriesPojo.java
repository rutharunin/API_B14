package pojo;

import java.util.List;

public class MarvelSeriesPojo {
    private int available;
    private String collectionURI;
    private List<MarvelItemsPojo>items;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<MarvelItemsPojo> getItems() {
        return items;
    }

    public void setItems(List<MarvelItemsPojo> items) {
        this.items = items;
    }
}
