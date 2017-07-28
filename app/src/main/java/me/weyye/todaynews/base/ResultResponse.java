package me.weyye.todaynews.base;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ResultResponse<T> {

    public String kind;
    public String genre;
    public String last_updated;
    public T collection;

    public ResultResponse(String kind, String genre, String last_updated, T collection) {
        this.kind = kind;
        this.genre = genre;
        this.last_updated = last_updated;
        this.collection = collection;
    }
}
