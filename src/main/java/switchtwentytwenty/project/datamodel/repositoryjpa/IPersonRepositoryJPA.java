package switchtwentytwenty.project.datamodel.repositoryjpa;

import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;

import java.util.List;
import java.util.Optional;

public interface IPersonRepositoryJPA extends CrudRepository<PersonJPA, PersonIDJPA> {

    List<PersonJPA> findAll();

    Optional<PersonJPA> findById(PersonIDJPA id);

    List<PersonJPA> findAllByFamilyid(FamilyIDJPA familyIDJPA);

}
