package com.app.domain;

public enum PlayerPosition {
    GOALKEEPER(1), DEFENDER(20), STRIKER(95), MIDFIELDER(80);

    private final int chanceInPercentagesToShotGoal;

    PlayerPosition(int chanceInPercentagesToShotGoal) {
        this.chanceInPercentagesToShotGoal = chanceInPercentagesToShotGoal;
    }

    public double getChanceInPercentagesToShotGoal() {
        return chanceInPercentagesToShotGoal / 100f;
    }
}
