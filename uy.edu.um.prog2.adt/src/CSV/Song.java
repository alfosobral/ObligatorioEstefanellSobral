package CSV;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Song {
    private String spotify_id;
    private String name;
    private String artists;
    private String daily_rank;
    private String tempo;

    public Song(String spotify_id, String  name, String artists, String daily_rank,String tempo) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
        this.daily_rank = daily_rank;
        this.tempo = tempo;
    }

    public String getSpotify_id() {
        return spotify_id;
    }

    public String getName() {
        return name;
    }

    public String getArtists() {
        return artists;
    }

    public String getDaily_rank() {
        return daily_rank;
    }


    public String getTempo() {
        return tempo;
    }

}
