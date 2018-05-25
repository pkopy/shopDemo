package pl.pkopy.shopDemo.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pkopy.shopDemo.models.BarcodeEntity;

@Repository
public interface BarcodeRepository extends CrudRepository<BarcodeEntity, Integer> {
}
