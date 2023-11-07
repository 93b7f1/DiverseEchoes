package diverse.java.domain;

import jakarta.persistence.*;

@Entity
public class Echo {
    @Id
    @Column(name = "idEcho")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEcho;

    @Column(name = "urlOptional")
    private String urlOptional;
    @Column(name = "echoName")
    private String echoName;

    @Column(name = "genre")
    private String genre;

    @Column(name = "views")
    private Integer views;

    @Column(name = "type")
    private String type;

    @ManyToOne
    private User user;

    @Column(name = "lyrics", length = 3000)
    private String lyrics;

    @Column(name = "spotify")
    private String spotify;

    @Column(name = "youtube")
    private String youtube;

    @Column(name = "soundCloud")
    private String soundCloud;

    @Column(name = "deezer")
    private String deezer;

    @Column(name = "amazonMusic")
    private String amazonMusic;

    @Column(name = "appleMusic")
    private String appleMusic;

    @Column(name = "pixivEcho")
    private String pixivEcho;

    @Column(name = "spotifyId")
    private String spotifyId;


    @Column(name = "echoImage")
    private String echoImage;

    @Column(name = "stream")
    private Integer stream;

    @Column(name = "redirect")
    private Integer redirect;

    public Integer getIdEcho() {
        return idEcho;
    }

    public void setIdEcho(Integer idEcho) {
        this.idEcho = idEcho;
    }

    public String getUrlOptional() {
        return urlOptional;
    }

    public void setUrlOptional(String urlOptional) {
        this.urlOptional = urlOptional;
    }

    public String getEchoName() {
        return echoName;
    }

    public void setEchoName(String echoName) {
        this.echoName = echoName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getSoundCloud() {
        return soundCloud;
    }

    public void setSoundCloud(String soundCloud) {
        this.soundCloud = soundCloud;
    }

    public String getDeezer() {
        return deezer;
    }

    public void setDeezer(String deezer) {
        this.deezer = deezer;
    }

    public String getAmazonMusic() {
        return amazonMusic;
    }

    public void setAmazonMusic(String amazonMusic) {
        this.amazonMusic = amazonMusic;
    }

    public String getAppleMusic() {
        return appleMusic;
    }

    public void setAppleMusic(String appleMusic) {
        this.appleMusic = appleMusic;
    }

    public String getPixivEcho() {
        return pixivEcho;
    }

    public void setPixivEcho(String pixivEcho) {
        this.pixivEcho = pixivEcho;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getEchoImage() {
        return echoImage;
    }

    public void setEchoImage(String echoImage) {
        this.echoImage = echoImage;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }

    public Integer getRedirect() {
        return redirect;
    }

    public void setRedirect(Integer redirect) {
        this.redirect = redirect;
    }
}
