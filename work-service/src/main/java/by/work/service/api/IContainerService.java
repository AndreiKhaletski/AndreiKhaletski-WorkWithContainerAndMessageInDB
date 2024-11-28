package by.work.service.api;

import by.work.core.dto.ContainerDto;
import by.work.core.dto.MessageDto;

public interface IContainerService {
    void container(ContainerDto containerDto);

    void add(MessageDto messageDto);

    String findMessage(String text);

    long getContainerCount();

    long getMessageCount();

}
