package domain;

public class Score {

    private String nickname;
    private double seconds;

    public Score(String nickname, double seconds) {
        this.nickname = nickname;
        this.seconds = seconds;
    }

    public String getNickname() {
        return nickname;
    }

    public double getSeconds() {
        return seconds;
    }
    
    
    
    
}
