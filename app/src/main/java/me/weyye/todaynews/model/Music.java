package me.weyye.todaynews.model;

/**
 * Created by zhuowei on 2017/7/28.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music {

    @SerializedName("track")
    @Expose
    private Track track;
    @SerializedName("score")
    @Expose
    private Double score;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}

