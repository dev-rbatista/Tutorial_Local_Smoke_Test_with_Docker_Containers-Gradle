package switchtwentytwenty.project.datamodel.repositoryjpa;

import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.domainjpa.*;

import java.util.List;
import java.util.Optional;

public interface IAccountRepositoryJPA extends CrudRepository<AccountJPA, Long> {

    List<AccountJPA> findAll();

    Optional<AccountJPA> findById(PersonIDJPA personIDJPA);

    Optional<AccountJPA> findById(FamilyIDJPA personIDJPA);

    Optional<AccountJPA> findByOwnerID(OwnerIDJPA ownerIDJPA);

}
