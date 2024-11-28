package by.work.dao;

import by.work.core.dto.MessageDto;
import by.work.core.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IMessageRepository extends JpaRepository<MessageEntity, Long> {
    boolean existsByText(String text);

}
