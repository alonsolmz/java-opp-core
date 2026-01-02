package pe.edu.utp.poojavalmz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.poojavalmz.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
