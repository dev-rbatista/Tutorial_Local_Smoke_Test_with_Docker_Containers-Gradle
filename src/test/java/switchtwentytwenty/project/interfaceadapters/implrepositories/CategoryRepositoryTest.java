package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.CategoryDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.CategoryJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.ICategoryRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.category.Category;
import switchtwentytwenty.project.domain.aggregates.category.CategoryFactory;
import switchtwentytwenty.project.domain.aggregates.category.CustomCategory;
import switchtwentytwenty.project.domain.aggregates.category.StandardCategory;
import switchtwentytwenty.project.domain.valueobject.CategoryID;
import switchtwentytwenty.project.domain.valueobject.CategoryName;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.ParentCategoryPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryRepositoryTest {

    @Mock
    ICategoryRepositoryJPA categoryRepositoryJPA;
    @Mock
    CategoryDataDomainAssembler categoryDataDomainAssembler;

    @Mock
    CategoryFactory categoryFactory;

    @InjectMocks
    CategoryRepository categoryRepository;

    @Captor
    ArgumentCaptor<CategoryIDJPA> captor;

    // Category JPA data
    String categoryDescription = "compras"; //"in english B
    CategoryIDJPA cat = new CategoryIDJPA(12L);
    Long parentID = 0L;
    String familyID = null;
    FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID);

    // Category Data
    CategoryName catName = new CategoryName("BuYs");
    CategoryID selfID = new CategoryID(12L);
    CategoryID parentIDCat = new CategoryID(0L);


   /* @Test
    @Disabled
    void getByID() {
        CategoryJPA categoryJPA = new CategoryJPA(categoryDescription, cat, parentID, familyIDJPA);
        StandardCategory category = new StandardCategory(catName, selfID, parentIDCat);
        when(categoryRepositoryJPA.findById(any(CategoryIDJPA.class))).thenReturn(Optional.of(categoryJPA));

        CategoryID categoryID = new CategoryID(12L);
        Category expected = new StandardCategory(catName, selfID, parentIDCat);

        Category result = categoryRepository.getByID(categoryID);

        assertEquals(expected, result);
        assertNotNull(result);
    }*/


    /*@Test
    @Disabled
    void add() {
        StandardCategory category = new StandardCategory(catName, parentIDCat);
        CategoryJPA categoryJPA = new CategoryJPA.Builder(categoryDescription).withParentID(parentID).withFamilyIDJPA(familyIDJPA).build();
        CategoryJPA categoryJPAWithID = new CategoryJPA(categoryDescription, cat, parentID, familyIDJPA);
        when(categoryDataDomainAssembler.toData(category)).thenReturn(categoryJPA);
        when(categoryRepositoryJPA.save(categoryJPA)).thenReturn(categoryJPAWithID);

        Category expected = new StandardCategory(catName, selfID, parentIDCat);
        Category result = categoryRepository.add(category);

        assertEquals(expected, result);
        assertNotNull(result);
    }*/


    @Test
    void testGetByIDSuccess() {
        CategoryID categoryID = new CategoryID(12L);
        CategoryIDJPA categoryIDJPA = new CategoryIDJPA(12);
        Category expected = new StandardCategory(new CategoryName("name"),categoryID,new ParentCategoryPath("11"));
        CategoryJPA categoryJPA = new CategoryJPA("name",12L,"11",null);
        Optional<CategoryJPA> optional = Optional.of(categoryJPA);

        Mockito.when(categoryDataDomainAssembler.toData(categoryID)).thenReturn(categoryIDJPA);
        Mockito.when(categoryRepositoryJPA.findById(categoryIDJPA)).thenReturn(optional);
        Mockito.when(categoryDataDomainAssembler.createCategoryID(categoryJPA)).thenReturn(new CategoryID(12));
        Mockito.when(categoryDataDomainAssembler.createCategoryName(categoryJPA)).thenReturn(new CategoryName("name"));
        Mockito.when(categoryDataDomainAssembler.createParentID(categoryJPA)).thenReturn(new ParentCategoryPath("11"));
        Mockito.when(categoryFactory.createCategory(any(CategoryID.class),any(CategoryName.class),any(ParentCategoryPath.class),any(Optional.class))).thenReturn(expected);
        Category result = categoryRepository.getByID(categoryID);
        assertEquals(expected,result);
    }

    @Test
    void testGetByIDFail() {
        CategoryID categoryID = new CategoryID(12L);
        CategoryIDJPA categoryIDJPA = new CategoryIDJPA(12);
        Optional<CategoryJPA> optional = Optional.empty();

        Mockito.when(categoryDataDomainAssembler.toData(categoryID)).thenReturn(categoryIDJPA);
        Mockito.when(categoryRepositoryJPA.findById(categoryIDJPA)).thenReturn(optional);
        assertThrows(IllegalArgumentException.class, ()->categoryRepository.getByID(categoryID));

    }

    @Test
    void getStandardCategoryList() {
        List<Category> expected = new ArrayList<>();
        Category cat1 = new StandardCategory(new CategoryName("name"),new CategoryID(11L), new ParentCategoryPath("12"));
        expected.add(cat1);

        List<CategoryJPA> returnList = new ArrayList<>();
        CategoryJPA cat1Clone = new CategoryJPA("name",11L,"12",null);

        returnList.add(cat1Clone);
        Mockito.when(categoryRepositoryJPA.findAllByFamilyIDJPAIsNull()).thenReturn(returnList);
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(new CategoryID(2l));
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(new CategoryName("Casa"));
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(new ParentCategoryPath("/casdsa"));
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.empty());


        when(categoryFactory.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(cat1);

        List<Category> result = categoryRepository.getStandardCategoryList();

        assertEquals(expected,result);
    }

    @Test
    void getCustomCategoryListSuccessCase() {
        FamilyID familyID = new FamilyID("@tonyze@latinlover.com");
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.getId());
        Mockito.when(categoryDataDomainAssembler.toData(familyID)).thenReturn(familyIDJPA);
        CategoryName name = new CategoryName("category");
        CategoryID categoryID = new CategoryID(3L);
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("categoryParent");
        Category category = new CustomCategory(categoryID, parentCategoryPath, name, familyID);
        List<Category> expected = new ArrayList<>();
        expected.add(category);

        CategoryJPA categoryJPA = new CategoryJPA(name.toString(), 3L, parentCategoryPath.toString(), familyIDJPA);

        List<CategoryJPA> categoryJPAList = new ArrayList<>();
        categoryJPAList.add(categoryJPA);

        Mockito.when(categoryRepositoryJPA.findAllByFamilyIDJPA(familyIDJPA)).thenReturn(categoryJPAList);
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(categoryID);
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(name);
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(parentCategoryPath);
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.of(familyID));

        when(categoryFactory.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(category);


        List<Category> result = categoryRepository.getCustomCategoryList(familyID);

        assertEquals(expected, result);

    }

    @Test
    void getCustomCategoryListFailureCase() {
        FamilyID familyID = new FamilyID("@tonyze@latinlover.com");
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.getId());
        Mockito.when(categoryDataDomainAssembler.toData(familyID)).thenReturn(familyIDJPA);
        CategoryName name = new CategoryName("category");
        CategoryID categoryID = new CategoryID(3L);
        ParentCategoryPath parentCategoryPath = new ParentCategoryPath("categoryParent");
        Category category = new CustomCategory(categoryID, parentCategoryPath, name, familyID);
        List<Category> expected = new ArrayList<>();

        CategoryJPA categoryJPA = new CategoryJPA(name.toString(), 3L, parentCategoryPath.toString(), familyIDJPA);

        List<CategoryJPA> categoryJPAList = new ArrayList<>();
        categoryJPAList.add(categoryJPA);

        Mockito.when(categoryRepositoryJPA.findAllByFamilyIDJPA(familyIDJPA)).thenReturn(categoryJPAList);
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(categoryID);
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(name);
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(parentCategoryPath);
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.of(familyID));

        when(categoryFactory.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(category);


        List<Category> result = categoryRepository.getCustomCategoryList(familyID);

        assertNotEquals(expected, result);

    }

    @Test
    void addCategoryTestSuccess() {
        Category category = new StandardCategory(new CategoryName("Casa"), new CategoryID(2l), new ParentCategoryPath("/casdsa"));

        when(categoryDataDomainAssembler.toData(any(Category.class))).thenReturn(new CategoryJPA());
        when(categoryRepositoryJPA.save(any(CategoryJPA.class))).thenReturn(new CategoryJPA());
        when(categoryDataDomainAssembler.createCategoryID(any(CategoryJPA.class))).thenReturn(new CategoryID(2l));
        when(categoryDataDomainAssembler.createCategoryName(any(CategoryJPA.class))).thenReturn(new CategoryName("Casa"));
        when(categoryDataDomainAssembler.createParentID(any(CategoryJPA.class))).thenReturn(new ParentCategoryPath("/casdsa"));
        when(categoryDataDomainAssembler.createFamilyID(any(CategoryJPA.class))).thenReturn(Optional.empty());


        when(categoryFactory.createCategory(any(CategoryID.class), any(CategoryName.class), any(ParentCategoryPath.class), any(Optional.class))).thenReturn(category);

        Category expected = new StandardCategory(new CategoryName("Casa"), new CategoryID(2l), new ParentCategoryPath("/casdsa"));

        Category result = categoryRepository.add(new StandardCategory());

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }
}