package com.app.client.domain;

public enum PlayerPosition {
    GOALKEEPER(1, "Bramkarz"), DEFENDER(20, "Obrońca"), STRIKER(95, "Napastnik"), MIDFIELDER(80, "Pomocnik");

    private final int chanceInPercentagesToShotGoal;
    private final String translate;

    PlayerPosition(int chanceInPercentagesToShotGoal, String translate) {
        this.chanceInPercentagesToShotGoal = chanceInPercentagesToShotGoal;
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }

}
