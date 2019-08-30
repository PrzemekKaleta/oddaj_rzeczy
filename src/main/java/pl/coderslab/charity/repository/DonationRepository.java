package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) from Donation d")
    int allQuantityOfBags();

    @Query("select count (distinct d.institution) from Donation d")
    int allQuantityOfSuportedInstitutions();

}
