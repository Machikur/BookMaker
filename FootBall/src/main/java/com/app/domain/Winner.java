package com.app.domain;

public enum Winner {
    HOST_TEAM("Gospodarz"), OPPOSITE_TEAM("Oponent"), DRAW("Remis");
    private String desc;

    Winner(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

}
