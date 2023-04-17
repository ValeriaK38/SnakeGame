package org.example.Model;

public class Score {
     private int scorePoints;

    private static Score score;

    public static Score getInstance() {
        if (score == null) {
            score = new Score();
        }
        return score;
    }

    public Score() {
        this.scorePoints = 0;
    }

    public int getScorePoints() {
        return scorePoints;
    }

    public void setScorePoints(int scorePoints) {
        this.scorePoints = scorePoints;
    }

    public static Score getScore() {
        return score;
    }

    public void addScorePoint(){
        this.scorePoints ++;
    }

    public static void setScore(Score score) {
        Score.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scorePoints=" + scorePoints +
                '}';
    }
}
