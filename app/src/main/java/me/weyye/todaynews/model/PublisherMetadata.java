package me.weyye.todaynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
* Created by zhuowei on 2017/7/28.
*/
class PublisherMetadata {

   @SerializedName("id")
   @Expose
   private Integer id;
   @SerializedName("urn")
   @Expose
   private String urn;
   @SerializedName("artist")
   @Expose
   private String artist;

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public String getUrn() {
       return urn;
   }

   public void setUrn(String urn) {
       this.urn = urn;
   }

   public String getArtist() {
       return artist;
   }

   public void setArtist(String artist) {
       this.artist = artist;
   }

}
