package switchtwentytwenty.project.datamodel.repositoryjpa;

import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;

import java.util.List;
import java.util.Optional;

public interface IFamilyRepositoryJPA extends CrudRepository<FamilyJPA, FamilyIDJPA> {

    List<FamilyJPA> findAll();

    Optional<FamilyJPA> findById(FamilyIDJPA id);

    boolean existsByAdminID(PersonIDJPA personIDJPA);

    boolean existsFamilyJPAById(FamilyIDJPA familyIDJPA);

}