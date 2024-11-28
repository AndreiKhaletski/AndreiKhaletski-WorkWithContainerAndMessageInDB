package by.work.core.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "message",schema = "app")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 10)
    private String text;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private ContainerEntity container;

    public MessageEntity() {
    }

    public MessageEntity(Long id, String text, ContainerEntity container) {
        this.id = id;
        this.text = text;
        this.container = container;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ContainerEntity getContainer() {
        return container;
    }

    public void setContainer(ContainerEntity container) {
        this.container = container;
    }
}
