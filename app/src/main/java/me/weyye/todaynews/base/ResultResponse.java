package me.weyye.todaynews.base;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ResultResponse<T> {

    public String return_count;
    public String has_more;
    public String page_id;
    public Object _ck;
    public String html;
    public T data;

    public ResultResponse(String return_count, String more, String page_id, Object ck, String html, T result) {
        this.return_count = return_count;
        has_more = more;
        this.page_id = page_id;
        this._ck = ck;
        this.html = html;
        data = result;
    }
}
