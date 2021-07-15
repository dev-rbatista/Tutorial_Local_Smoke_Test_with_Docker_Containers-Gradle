package switchtwentytwenty.project.dto.category;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.CategoryRESTController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class OutputCategoryTreeDTOTest {

    @Test
    void testEquals() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "4", "@family@id.com");
        List<OutputCategoryDTO> outputDtoList = new ArrayList<>();
        outputDtoList.add(output1);
        outputDtoList.add(output2);

        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO(outputDtoList);
        OutputCategoryTreeDTO outputCategoryTreeDTO2 = new OutputCategoryTreeDTO(outputDtoList);

        assertEquals(outputCategoryTreeDTO1, outputCategoryTreeDTO2);

    }

    @Test
    void testEqualsSameObject() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "4", "@family@id.com");
        List<OutputCategoryDTO> outputDtoList = new ArrayList<>();
        outputDtoList.add(output1);
        outputDtoList.add(output2);

        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO(outputDtoList);
        OutputCategoryTreeDTO outputCategoryTreeDTO2 = new OutputCategoryTreeDTO(outputDtoList);

        assertEquals(outputCategoryTreeDTO1, outputCategoryTreeDTO1);

    }

    @Test
    void testEqualsDifferentType() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "4", "@family@id.com");
        List<OutputCategoryDTO> outputDtoList = new ArrayList<>();
        outputDtoList.add(output1);
        outputDtoList.add(output2);

        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO(outputDtoList);
        String notSameObject = "string";
        assertFalse(outputCategoryTreeDTO1.equals(notSameObject));

    }

    @Test
    void testHashCode() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "4", "@family@id.com");
        List<OutputCategoryDTO> outputDtoList = new ArrayList<>();
        outputDtoList.add(output1);
        outputDtoList.add(output2);

        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO(outputDtoList);
        OutputCategoryTreeDTO outputCategoryTreeDTO2 = new OutputCategoryTreeDTO(outputDtoList);

        assertEquals(outputCategoryTreeDTO1.hashCode(), outputCategoryTreeDTO2.hashCode());
        assertNotEquals(0, outputCategoryTreeDTO1.hashCode());
        assertNotEquals(0, outputCategoryTreeDTO2.hashCode());
    }

    @Test
    void addOutputCategoryDTO() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO();
        assertNotNull(outputCategoryTreeDTO1);
        int hash1 = outputCategoryTreeDTO1.hashCode();
        outputCategoryTreeDTO1.addOutputCategoryDTO(output1);
        int hash2 = outputCategoryTreeDTO1.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    void setOutputCategoryDTOList() {
        OutputCategoryDTO output1 = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "4", "@family@id.com");
        List<OutputCategoryDTO> outputDtoList = new ArrayList<>();
        outputDtoList.add(output1);
        outputDtoList.add(output2);

        OutputCategoryTreeDTO outputCategoryTreeDTO1 = new OutputCategoryTreeDTO();

        outputCategoryTreeDTO1.setOutputCategoryDTOList(outputDtoList);

        List<OutputCategoryDTO> result = outputCategoryTreeDTO1.getOutputCategoryDTOList();

        assertEquals(outputDtoList, result);
    }

    @Test
    void testEqualsDifferentLinks() {
        OutputCategoryDTO output = new OutputCategoryDTO("name", "2", "1", "@family@id.com");
        Link link1 = linkTo(methodOn(CategoryRESTController.class).getCategory("1")).withSelfRel();
        Link link2 = linkTo(methodOn(CategoryRESTController.class).getCategory("2")).withSelfRel();

        OutputCategoryTreeDTO list1 = new OutputCategoryTreeDTO();
        OutputCategoryTreeDTO list2 = new OutputCategoryTreeDTO();

        list1.addOutputCategoryDTO(output);
        list1.add(link1);

        list2.addOutputCategoryDTO(output);
        list2.add(link2);

        assertNotEquals(list1,list2);
    }

    @Test
    void testEqualsDifferentList(){
        OutputCategoryDTO output1 = new OutputCategoryDTO("name1", "2", "1", "@family@id.com");
        OutputCategoryDTO output2 = new OutputCategoryDTO("name2", "3", "1", "@family@id.com");

        OutputCategoryTreeDTO list1 = new OutputCategoryTreeDTO();
        OutputCategoryTreeDTO list2 = new OutputCategoryTreeDTO();

        list1.addOutputCategoryDTO(output1);
        list1.addOutputCategoryDTO(output2);

        list2.addOutputCategoryDTO(output1);

        assertNotEquals(list1, list2);
    }
}

