package com.matheusfroes.unit.model;

/**
 * Created by Waldson on 06/12/2015.
 */
public class Alternative {
    private String alternative;
    private int betPoints;

    public Alternative(String alternative) {
        this.alternative = alternative;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public int getBetPoints() {
        return betPoints;
    }

    public void setBetPoints(int betPoints) {
        this.betPoints = betPoints;
    }

    public void incrementBetPoints() {
        betPoints++;
    }

    public void decrementBetPoints() {
        betPoints--;
    }

    @Override
    public String toString() {
        return this.alternative;
    }
}
