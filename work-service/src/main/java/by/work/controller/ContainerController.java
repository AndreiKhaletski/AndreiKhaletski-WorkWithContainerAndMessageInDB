package by.work.controller;

import by.work.core.dto.ContainerDto;
import by.work.core.dto.MessageDto;
import by.work.service.api.IContainerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContainerController {

    private final IContainerService containerService;

    public ContainerController(IContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping(value = "/container")
    public void containerSize(@RequestBody ContainerDto containerDto) {
        containerService.container(containerDto);
    }

    @PostMapping(value = "/message")
    public void addMessage(@RequestBody MessageDto messageDto){
        containerService.add(messageDto);
    }

    @GetMapping(value = "/find")
    public String find(@RequestParam String text){
        return containerService.findMessage(text);
    }

    @GetMapping(value = "/containers/count")
    public long getContainerCount() {
        return containerService.getContainerCount();
    }

    @GetMapping(value = "/records/count")
    public long getRecordCount() {
        return containerService.getMessageCount();
    }
}

