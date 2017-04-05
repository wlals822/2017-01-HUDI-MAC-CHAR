package com.mapia.web;

import com.mapia.domain.Room;
import com.mapia.domain.User;
import com.mapia.utils.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);
	
	private volatile Map<Long, Room> rooms = new ConcurrentHashMap<>();
	private final AtomicLong roomId = new AtomicLong();

	@GetMapping("")
	public String waitingRoomPage(HttpSession session, Model model) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login";
		}

		User user = HttpSessionUtils.getUserFromSession(session);
		user.enterLobby();
		
		// TODO @우현 rooms.values() 반환 값이 이미 Collection인데 ArrayList에 다시 넣는 이유는?
		model.addAttribute("rooms", new ArrayList<>(rooms.values()));
		return "rooms";
	}

	@GetMapping("/{id}")
	public String enter(@PathVariable long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login";
		}
		User user = HttpSessionUtils.getUserFromSession(session);
		
        Room room = rooms.get(id);
        
        // TODO @우현 이후 로직 구현을 room의 enterRoom(user)에서 모두 구현할 수 없을까?
        // 만약 가능하다면 어떻게 구현하는 것이 좋을까? 이와 같이 구현했을 때의 장점은?
        if (!user.isEntered()) {
            log.info("entered user: {}", user);
            room.outRoom(user);
            return "redirect:/rooms";
        }
        if (room.isFull()) {
            log.info("fulled count of user in room: {}", room.getCountOfUserInRoom());
            return "redirect:/rooms";
        }

        if (room.isSecretMode()) {
            //TODO Add secret mode logic
        }
        log.info("Success to enter room, user: {}, room: {}", user, room);
        
        // TODO @우현 room 데이터를 session에 넣었을 때 발생할 수 있는 이슈는 없을까?
        session.setAttribute("enteredRoom", room);
        room.enterRoom(user);
        return "redirect:/game/" + id;
	}
	
	@PostMapping("")
	public String create(String title) {
        //TODO Add secret mode logic
		Room room = new Room(roomId.incrementAndGet(), title);
		log.info("Created RoomId: {}, title: {}", room.getId(), room.getTitle());
		long roomId = room.getId();
		rooms.put(roomId, room);
		return "redirect:/rooms/" + roomId;
	}
}
