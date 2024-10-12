package com.chatflatform.domain.chat.repository;

import com.chatflatform.domain.auth.repository.entity.User;
import com.chatflatform.domain.chat.repository.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Long> {

    List<Chat> findTop10BySenderOrReceiverOrderByTIDDesc(String sender, String receiover);
}
