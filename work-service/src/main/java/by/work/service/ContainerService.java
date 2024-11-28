package by.work.service;

import by.work.core.dto.ContainerDto;
import by.work.core.dto.MessageDto;
import by.work.core.entity.ContainerEntity;
import by.work.core.entity.MessageEntity;
import by.work.dao.IContainerRepository;
import by.work.dao.IMessageRepository;
import by.work.service.api.IContainerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContainerService implements IContainerService {

    private final IContainerRepository containerRepository;
    private final IMessageRepository messageRepository;

    public ContainerService(IContainerRepository containerRepository,
                            IMessageRepository messageRepository) {
        this.containerRepository = containerRepository;
        this.messageRepository = messageRepository;
    }

    private int valueContainer;

    @Transactional
    @Override
    public void container(ContainerDto containerDto) {
        this.valueContainer = containerDto.getSize();
    }

    @Transactional
    @Override
    public void add(MessageDto messageDto) {
        if ((!messageDto.getText().matches("[a-zA-Z0-9]*") || messageDto.getText().length() > 10)) {
            throw new IllegalArgumentException("Ошибка ввода!");
        }


        boolean textInDB = messageRepository.existsByText(messageDto.getText());
        if (textInDB){
            throw new IllegalArgumentException("Ошибка воода!");
        }

        for (ContainerEntity container : containerRepository.findAll()) {
            if (container.getMessages().size() < valueContainer) {
                MessageEntity message = new MessageEntity();
                message.setText(messageDto.getText());
                message.setContainer(container);
                messageRepository.save(message);
                return;
            }
        }

        ContainerEntity newContainer = new ContainerEntity();
        MessageEntity message = new MessageEntity();
        message.setText(messageDto.getText());
        message.setContainer(newContainer);
        newContainer.getMessages().add(message);
        containerRepository.save(newContainer);

    }

    @Override
    public String findMessage(String text) {
        for (MessageEntity message : messageRepository.findAll()) {
            if (message.getText().equals(text)) {
                return "Сообщение в контейнере №" + message.getContainer().getId();
            }
        }
        return "Сообщения нету!";
    }

    @Override
    public long getContainerCount() {
        return containerRepository.count();
    }

    @Override
    public long getMessageCount() {
        return messageRepository.count();
    }
}
