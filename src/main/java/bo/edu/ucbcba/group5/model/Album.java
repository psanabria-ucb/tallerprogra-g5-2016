
package bo.edu.ucbcba.group5.model;
import javax.persistence.*;

    // Elemento Entity
    @Entity
    public class Album {
        @Id
        private String title;

        @Column(length = 100)
        private String artist;

        @Lob
        @Column(length = 500)
        private String description;
        private int year;
        private int numberTracks;
        private int duration;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getNumberTracks() {
            return numberTracks;
        }

        public void setNumberTracks(int numberTracks) {
            this.numberTracks = numberTracks;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }
    }
