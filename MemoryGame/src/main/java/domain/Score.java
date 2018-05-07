package domain;

public class Score {

    private String nickname;
    private double time;

    /**
     * Simple score object with a nickname and time.
     *
     * @param nickname
     * @param time
     */
    public Score(String nickname, double time) {
        if (nickname.length() == 0) {
            this.nickname = "ANON";
        } else if (nickname.length() > 8) {
            this.nickname = nickname.substring(0, 8);
        } else {
            this.nickname = nickname;
        }
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public double getTime() {
        return time;
    }

}
