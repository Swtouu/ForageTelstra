package au.com.telstra.simcardactivator.app.repository;

import au.com.telstra.simcardactivator.app.model.SimCardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimActivationRepository extends JpaRepository<SimCardModel, Long> {
}
