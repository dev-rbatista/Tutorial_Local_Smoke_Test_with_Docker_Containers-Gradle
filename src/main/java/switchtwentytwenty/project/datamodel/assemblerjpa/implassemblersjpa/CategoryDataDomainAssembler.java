package switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa;

import org.springframework.stereotype.Component;
import switchtwentytwenty.project.datamodel.assemblerjpa.iassemblersjpa.ICategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.Optional;

@Component
public class CategoryDataDomainAssembler implements ICategoryDataDomainAssembler {

    @Override
    public CategoryJPA toData(Category category) {
        String categoryName = category.getCategoryName().toString();
        Long categoryID = null;
        if (category.id() != null) {
            categoryID = Long.valueOf(category.id().toString());
        }
        String parentPath = category.getParentCategoryPath().toString();
        Optional<FamilyID> familyID = category.getFamilyID();
        FamilyIDJPA familyIDJPA;
        if (familyID.isPresent()) {
            String familyIDString = familyID.get().toString();
            familyIDJPA = new FamilyIDJPA(familyIDString);
        } else {
            familyIDJPA = null;
        }

        CategoryJPA categoryJPA;
        if (categoryID == null) {
            categoryJPA = new CategoryJPA.Builder(categoryName).withParentID(parentPath).withFamilyIDJPA(familyIDJPA).build();
        } else {
            categoryJPA = new CategoryJPA.Builder(categoryName).withParentID(parentPath).withFamilyIDJPA(familyIDJPA).withCategoryIDJPA(categoryID).build();
        }

        return categoryJPA;
    }

    @Override
    public FamilyIDJPA toData(FamilyID familyID) {
        String familyIDString = familyID.toString();
        return new FamilyIDJPA(familyIDString);
    }

    public CategoryID createCategoryID(CategoryJPA categoryJPA) {
        return new CategoryID(categoryJPA.getCategoryIDJPA());
    }

    public CategoryName createCategoryName(CategoryJPA categoryJPA) {
        return new CategoryName(categoryJPA.getCategoryName());
    }

    public ParentCategoryPath createParentID(CategoryJPA categoryJPA) {
        return new ParentCategoryPath(categoryJPA.getParentID());
    }

    public Optional<FamilyID> createFamilyID(CategoryJPA categoryJPA) {
        Optional<FamilyID> familyID;
        if (categoryJPA.getFamilyIDJPA() == null) {
            familyID = Optional.empty();
        } else {
            familyID = Optional.of(new FamilyID(categoryJPA.getFamilyIDJPA().getFamilyID()));
        }
        return familyID;
    }

    @Override
    public CategoryIDJPA toData(CategoryID id) {
        return new CategoryIDJPA(Long.valueOf(id.getId()));

    }
}
