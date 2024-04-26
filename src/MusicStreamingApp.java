// Driver class for testing

import java.util.List;

public class MusicStreamingApp {
    public static void main(String[] args) {
        // Create instance of SongServiceImpl as the real server implementation
        SongService songService = new SongServiceImpl(); // Create proxy object for caching song metadata
        SongServiceProxy songServiceProxy = new SongServiceProxy(songService);

        // Search for songs using the proxy object
        Song song1 = songServiceProxy.searchById(1);
        System.out.println("Song 1: " + song1.getTitle() + " by " + song1.getArtist());

        Song song2 = songServiceProxy.searchById(2);
        System.out.println("Song 2: " + song2.getTitle() + " by " + song2.getArtist());

        Song song3 = songServiceProxy.searchById(3);
        System.out.println("Song 3: " + song3.getTitle() + " by " + song3.getArtist());

        Song song4 = songServiceProxy.searchById(4);
        System.out.println("Song 4: " + song4.getTitle() + " by " + song4.getArtist());

        // Search for songs by title and album using the proxy object
        List<Song> songsByTitle = songServiceProxy.searchByTitle("Water");
        System.out.println("\nSongs with title 'Water': " + songsByTitle.size());

        List<Song> songsByAlbum = songServiceProxy.searchByAlbum("Shock Value");
        System.out.println("Songs in album 'Shock Value': " + songsByAlbum.size());
    }
}