package repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Shop;
@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {

}
