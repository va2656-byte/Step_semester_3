public class Problem4 {

    interface Playable {
        String play();
        String play(int fromSecond);
        String pause();
    }

    static abstract class MediaFile {
        private final String fileId;
        private static int counter = 1001;

        MediaFile() {
            fileId = "MF-" + counter++;
        }

        public abstract String getFormatInfo();

        public String getFileId() {
            return fileId;
        }
    }

    static class AudioFile extends MediaFile implements Playable {
        private String title;

        AudioFile(String title) {
            this.title = title;
        }

        public String play() {
            return "Playing audio: " + title;
        }

        public String play(int fromSecond) {
            return "Playing audio: " + title + " from "
                    + (fromSecond / 60) + ":"
                    + String.format("%02d", fromSecond % 60);
        }

        public String pause() {
            return "Paused audio: " + title;
        }

        public String getFormatInfo() {
            return "Audio file, ID: " + getFileId();
        }
    }

    static class Podcast implements Playable {
        private String showName;
        private int episodeNumber;

        Podcast(String showName, int episodeNumber) {
            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        public String play() {
            return "Streaming episode " + episodeNumber
                    + " of " + showName;
        }

        public String play(int fromSecond) {
            return "Streaming episode " + episodeNumber
                    + " of " + showName + " from "
                    + fromSecond + " seconds";
        }

        public String pause() {
            return "Paused episode " + episodeNumber
                    + " of " + showName;
        }
    }

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        Podcast p = new Podcast("Tech Talk", 12);

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());
        System.out.println(p.play());

        Playable ref = a;
        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}