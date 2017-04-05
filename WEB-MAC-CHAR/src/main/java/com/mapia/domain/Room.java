package com.mapia.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Room {
	// TODO @우현 multi thread에서 문제가 발생할 수 있음.
	private Set<User> users = new LinkedHashSet<>();
	private long id;
	private String title;
	// TODO @우현 상수 값은 static final로 구현
	private final int CAPACITY = 8;
	private volatile int userCount;
	private boolean secretMode;

	public Room(long roomId, String title) {
		this.id = roomId;
		this.title = title;
	}

	public void enterRoom(User user){
        user.enterRoom();
		this.users.add(user);
	}
	
	public void outRoom(User user){
	    user.outRoom();
		this.users.remove(user);
	}

	public Set<User> getUsers() {
		return users;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getUserCount() {
		return getCountOfUserInRoom();
	}

	public boolean isSecretMode() {
		return secretMode;
	}

	public void setSecretMode() {
		this.secretMode = true;
	}

	public int getCountOfUserInRoom() {
		return users.size();
	}

	public boolean isFull() {
		return getCountOfUserInRoom() >= CAPACITY;
	}

    @Override
    public String toString() {
        return "Room{" +
                "users=" + users +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", userCount=" + userCount +
                '}';
    }
}
