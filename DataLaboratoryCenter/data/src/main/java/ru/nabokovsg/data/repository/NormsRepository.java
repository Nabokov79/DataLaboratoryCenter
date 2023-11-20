package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Norm;

public interface NormsRepository extends JpaRepository<Norm, Long> {
}