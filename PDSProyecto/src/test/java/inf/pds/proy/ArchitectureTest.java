package inf.pds.proy;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchitectureTest {

    private final JavaClasses clases = new ClassFileImporter()
            .importPackages("inf.pds.proy");

    @Test
    void dominio_no_depende_de_nadie() {
        ArchRule regla = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..application..", "..adapters..");

        regla.check(clases);
    }

    @Test
    void application_no_depende_de_adapters() {
        ArchRule regla = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAPackage("..adapters..");

        regla.check(clases);
    }

    @Test
    void controllers_solo_en_adapters_rest() {
        ArchRule regla = classes()
                .that().areAnnotatedWith(
                    org.springframework.web.bind.annotation.RestController.class)
                .should().resideInAPackage("..adapters.rest..");

        regla.check(clases);
    }

    @Test
    void servicios_solo_en_application() {
        ArchRule regla = classes()
                .that().areAnnotatedWith(
                    org.springframework.stereotype.Service.class)
                .should().resideInAPackage("..application..");

        regla.check(clases);
    }
}
