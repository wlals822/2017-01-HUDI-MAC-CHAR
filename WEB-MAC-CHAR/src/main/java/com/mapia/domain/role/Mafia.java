package com.mapia.domain.role;

import com.mapia.domain.Player;

public class Mafia extends Role {

    public Mafia() {
        super(RoleName.Mafia);
    }

    public Player assassination(Player player) {
        return player;
    }

}
