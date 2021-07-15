package switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa;

import switchtwentytwenty.project.datamodel.domainjpa.CategoryIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

public interface ICategoryDataDomainAssembler extends AssemblerDataDomain<CategoryJPA, Category>{

    FamilyIDJPA toData(FamilyID familyID);

    CategoryID createCategoryID(CategoryJPA categoryJPA);

    CategoryName createCategoryName(CategoryJPA categoryJPA);

    ParentCategoryPath createParentID(CategoryJPA categoryJPA);

    Optional<FamilyID> createFamilyID(CategoryJPA categoryJPA);

    CategoryIDJPA toData(CategoryID id);
}
