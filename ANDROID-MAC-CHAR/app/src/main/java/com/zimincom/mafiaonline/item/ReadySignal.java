package com.zimincom.mafiaonline.item;


public class ReadySignal {

    Lobby lobby;
    private String userName;
    private boolean startTimer;

    public ReadySignal() {
    }

    public ReadySignal(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isStartTimer() {
        return startTimer;
    }

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    @Override
    public String toString() {
        return "ReadySignal{" +
                "lobby=" + lobby +
                ", userName='" + userName + '\'' +
                ", startTimer=" + startTimer +
                '}';
    }
}
