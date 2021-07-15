package switchtwentytwenty.project.datamodel.repositoryjpa;

import org.springframework.data.repository.CrudRepository;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;

import java.util.List;

public interface ICategoryRepositoryJPA extends CrudRepository<CategoryJPA, CategoryIDJPA> {

    List<CategoryJPA> findAllByFamilyIDJPA(FamilyIDJPA familyIDJPA);

    List<CategoryJPA> findAllByFamilyIDJPAIsNull();
}
