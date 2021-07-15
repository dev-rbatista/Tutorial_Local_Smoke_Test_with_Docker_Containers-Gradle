package switchtwentytwenty.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import switchtwentytwenty.project.authentication.JWTUserDetailsService;
import switchtwentytwenty.project.authentication.UserDTO;
import switchtwentytwenty.project.dto.accounts.CreateAccountDTO;
import switchtwentytwenty.project.dto.category.CreateCategoryDTO;
import switchtwentytwenty.project.dto.family.AddFamilyAndSetAdminDTO;
import switchtwentytwenty.project.dto.family.ChangeRelationDTO;
import switchtwentytwenty.project.dto.family.CreateRelationDTO;
import switchtwentytwenty.project.dto.person.AddEmailDTO;
import switchtwentytwenty.project.dto.person.AddFamilyMemberDTO;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IAccountRESTController;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.ICategoryRESTController;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IFamilyRESTController;
import switchtwentytwenty.project.interfaceadapters.controller.icontrollers.IPersonRESTController;

import java.math.BigDecimal;


@SpringBootApplication
@TestPropertySource(locations = "classpath:application-test.properties")
public class
FFMSpringBootApplication extends SpringBootServletInitializer {
    private static final String TONY_ZE_EMAIL = "tonyze@latinlover.com";
    private static final String KATIA_VANESSA_EMAIL = "kvanessa@latina.com";
    private static final String RAIMUNDO_EMAIL = "raios_mundo@sapo.pt";
    private static final String RUA = "Rua do Charme";
    private static final String CIDADE = "Cidade do Zexo";
    private static final String CASA = "69 Esquerdo";
    private static final String ZIP = "1234-123";
    private static final String TONYZEFAMILYID = "@tonyze@latinlover.com";

    public static void main(String[] args) {
        SpringApplication.run(FFMSpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IFamilyRESTController familyRESTController, IPersonRESTController personRESTController, IAccountRESTController iAccountRESTController, ICategoryRESTController categoryRESTController, JWTUserDetailsService jwtUserDetailsService) {
        return args -> {

            AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO1 = new AddFamilyAndSetAdminDTO(TONY_ZE_EMAIL, "TonyZe", "12/12/1969", 123456789, 919999999, RUA, CIDADE, CASA, ZIP, "password", "Antunes", null);
            AddFamilyAndSetAdminDTO addFamilyAndSetAdminDTO2 = new AddFamilyAndSetAdminDTO("rifens@ravens.com", "Rifens", "05/05/1920", 987654321, 911111111, "Rua do Sertin", "Zelmin", "223", "1112-111", "password", "Ravens", "01/01/2021");
            AddFamilyMemberDTO addFamilyMemberDTO = new AddFamilyMemberDTO(TONY_ZE_EMAIL, KATIA_VANESSA_EMAIL, "Katia Vanessa", "20/01/1970", 777777777, 921232323, RUA, CIDADE, CASA, ZIP, "password");
            AddFamilyMemberDTO addFamilyMemberDTO2 = new AddFamilyMemberDTO(TONY_ZE_EMAIL, RAIMUNDO_EMAIL, "Raimundo Daniel", "13/05/1998", 111111111, 921332323, RUA, CIDADE, CASA, ZIP, "password");
            CreateAccountDTO vanessaAccount = new CreateAccountDTO("Conta para fugir do TonyZe", BigDecimal.valueOf(1034.54), "EUR", KATIA_VANESSA_EMAIL, "bank");
            CreateAccountDTO tonyaccount = new CreateAccountDTO("Lavagem de dinheiro", BigDecimal.valueOf(12.3), "EUR", "@tonyze@latinlover.com", "cash");
            CreateRelationDTO relationTonyKatia = new CreateRelationDTO(TONY_ZE_EMAIL, KATIA_VANESSA_EMAIL, "Husband");
            CreateRelationDTO relationKatiaRaimundo = new CreateRelationDTO(KATIA_VANESSA_EMAIL, RAIMUNDO_EMAIL, "Mother");
            CreateCategoryDTO categoryCrypto = new CreateCategoryDTO("G3 Crypto", null);
            CreateCategoryDTO categoryBeer = new CreateCategoryDTO("G3 Beer", null);
            ChangeRelationDTO changeRelationDTO = new ChangeRelationDTO("relacaoNova");

            familyRESTController.createFamilyAndSetAdmin(addFamilyAndSetAdminDTO1);
            familyRESTController.createFamilyAndSetAdmin(addFamilyAndSetAdminDTO2);
            personRESTController.addFamilyMember(addFamilyMemberDTO);
            personRESTController.addFamilyMember(addFamilyMemberDTO2);
            personRESTController.addEmail(new AddEmailDTO("KvanessaBackup@yahoo.com"), KATIA_VANESSA_EMAIL);
            personRESTController.addEmail(new AddEmailDTO("tonyzeBackup@yahoo.com"), TONY_ZE_EMAIL);
            personRESTController.addEmail(new AddEmailDTO("tonytony@yahoo.com"), TONY_ZE_EMAIL);
            iAccountRESTController.createAccount(vanessaAccount);
            iAccountRESTController.createAccount(tonyaccount);
            familyRESTController.createRelation(relationTonyKatia, TONYZEFAMILYID);
            familyRESTController.createRelation(relationKatiaRaimundo, TONYZEFAMILYID);

            jwtUserDetailsService.save(new UserDTO("sm", "sm", "systemManager"));
            jwtUserDetailsService.save(new UserDTO("admin", "admin", "familyAdministrator"));
            jwtUserDetailsService.save(new UserDTO("member", "member", "familyMember"));
            //familyRESTController.changeRelation(changeRelationDTO, TONYZEFAMILYID,"-1574017441");


        };
    }

}