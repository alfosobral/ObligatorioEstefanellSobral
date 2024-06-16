package CSV;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Song {
    private String spotify_id;
    private String name;
    private String artists;
    private int daily_rank;
    private Double tempo;

    public Song(String spotify_id, String  name, String artists, int daily_rank, Double tempo) {
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

    public int getDaily_rank() {
        return daily_rank;
    }

    public Double getTempo() {
        return tempo;
    }

}
