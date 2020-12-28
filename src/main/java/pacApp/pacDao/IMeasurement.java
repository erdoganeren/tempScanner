package pacApp.pacDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pacApp.pacModel.Measurement;

@Repository
public interface IMeasurement extends JpaRepository<Measurement, Long> {
	List<Measurement> findAllById(long id);
	Measurement findById(long id);
}
