package diverse.java.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @OneToMany(mappedBy = "user")
    private List<Echo> echo;

    @Getter
    @Column(name = "username")
    private String username;
    @Getter
    @Column(name = "email")
    private String email;
    @Getter
    @Column(name = "password")
    private String password;

    @Getter
    @Column(name = "spotify")
    private String spotify;

    @Getter
    @Column(name = "soundCloud")
    private String soundCloud;

    @Getter
    @Column(name = "twitter")
    private String twitter;

    @Getter
    @Column(name = "youtube")
    private String youtube;

    @Getter
    @Column(name = "pixivUser")
    private String pixivUser;

    @Getter
    @Column(name = "biography")
    private String biography;
    @Getter
    @Column(name = "userImage")
    private String userImage;

    public User() {
    }

    public User(Integer idUser, String username, String email, String password) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public void setSoundCloud(String soundCloud) {
        this.soundCloud = soundCloud;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setPixivUser(String pixivUser) {
        this.pixivUser = pixivUser;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
