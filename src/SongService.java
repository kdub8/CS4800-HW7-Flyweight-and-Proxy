import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete class representing a Song object with title, artist, album, and
 * duration.
 */
class Song {
    private String title;
    private String artist;
    private String album;
    private int duration;

    /**
     * Constructs a Song object with the specified title, artist, album, and
     * duration.
     *
     * @param title    The title of the song.
     * @param artist   The artist of the song.
     * @param album    The album of the song.
     * @param duration The duration of the song in seconds.
     */
    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // Getters and setters for song metadata
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }
}

/**
 * SongService interface defining methods for searching songs by ID, title, and
 * album.
 */
public interface SongService {
    /**
     * Searches for a song by its ID.
     *
     * @param songID The ID of the song to search for.
     * @return The song with the specified ID, or null if not found.
     */
    Song searchById(Integer songID);

    /**
     * Searches for songs by their title.
     *
     * @param title The title of the songs to search for.
     * @return A list of songs with the specified title, or an empty list if none
     *         found.
     */
    List<Song> searchByTitle(String title);

    /**
     * Searches for songs by their album.
     *
     * @param album The album of the songs to search for.
     * @return A list of songs in the specified album, or an empty list if none
     *         found.
     */
    List<Song> searchByAlbum(String album);
}

/**
 * Concrete implementation of SongService interface with a simulated database of
 * songs.
 */
class SongServiceImpl implements SongService {
    // Simulated database of songs
    private Map<Integer, Song> songsDatabase;

    public SongServiceImpl() {
        // Initialize the songs database with some example songs
        songsDatabase = new HashMap<>();
        songsDatabase.put(1, new Song("Water", "Tyla", "Album 1", 200));
        songsDatabase.put(2, new Song("The Way I Are", "Timbaland, Keri Hilson, D.O.E.", "Shock Value", 179));
        songsDatabase.put(3,
                new Song("Skate", "Bruno Mars, Anderson .Paak, Silk Sonic", "An Evening With Silk Sonic", 203));
        songsDatabase.put(4, new Song("Crush", "SEVENTEEN", "Attacca", 170));
        songsDatabase.put(5, new Song("Closer", "Ne-Yo", "Year Of The Gentleman", 234));
        songsDatabase.put(6, new Song("Water", "Kehlani", "It Was Good Until It Wasn't", 124));
    }

    @Override
    public Song searchById(Integer songID) {
        try {
            // Simulate delay in fetching song metadata from server
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return songsDatabase.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title) {
        try {
            // Simulate delay in fetching song metadata from server
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Search songs by title in the songs database and return the results
        // (Note: This is a simple linear search for demonstration purposes)
        List<Song> searchResults = new ArrayList<>();
        for (Song song : songsDatabase.values()) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                searchResults.add(song);
            }
        }
        return searchResults;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        try {
            // Simulate delay in fetching song metadata from server
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Search songs by album in the songs database and return the results
        // (Note: This is a simple linear search for demonstration purposes)
        List<Song> searchResults = new ArrayList<>();
        for (Song song : songsDatabase.values()) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                searchResults.add(song);
            }
        }
        return searchResults;
    }
}

/**
 * Proxy implementation of SongService interface for caching song metadata.
 */
class SongServiceProxy implements SongService {
    private SongService songService;
    private Map<Integer, Song> songCache; // Cache to store song metadata

    public SongServiceProxy(SongService songService) {
        this.songService = songService;
        this.songCache = new HashMap<>();
    }

    @Override
    public Song searchById(Integer songID) {
        // Check if song metadata is already cached
        if (songCache.containsKey(songID)) {
            System.out.println("Fetching song metadata from cache for song ID: " + songID);
            return songCache.get(songID);
        } else {
            // Fetch song metadata from server and store in cache
            Song song = songService.searchById(songID);
            if (song != null) {
                System.out.println("Fetching song metadata from server for song ID: " + songID);
                songCache.put(songID, song);
            }
            return song;
        }
    }

    @Override
    public List<Song> searchByTitle(String title) {
        // Fetch song metadata from server
        return songService.searchByTitle(title);
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        // Fetch song metadata from server
        return songService.searchByAlbum(album);
    }
}