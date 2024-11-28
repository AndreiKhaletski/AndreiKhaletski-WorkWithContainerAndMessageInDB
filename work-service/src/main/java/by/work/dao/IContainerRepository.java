package by.work.dao;

import by.work.core.dto.ContainerDto;
import by.work.core.entity.ContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IContainerRepository extends JpaRepository<ContainerEntity, Long> {
}
