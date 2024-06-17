package CSV.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Song {
    private String spotify_id;
    private String name;
    private String artists;
    private Double tempo;

    public Song(String spotify_id, String  name, String artists, Double tempo) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
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

    public Double getTempo() {
        return tempo;
    }

}
