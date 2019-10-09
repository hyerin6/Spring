package net.skhu.repository;

import net.skhu.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer>  {
}
