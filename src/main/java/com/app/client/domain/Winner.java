package com.app.client.domain;

public enum Winner {
    HOST_TEAM("Gospodarz"), DRAW("Remis"), OPPOSITE_TEAM("Oponent");
    private String desc;

    Winner(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

}
