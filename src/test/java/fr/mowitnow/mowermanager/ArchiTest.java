package fr.mowitnow.mowermanager;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "fr.mowitnow.mowermanager", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchiTest {

    /**
     * les classes du domaine peut être accédées par le user side (application) et le server side (infrastructure)
     * @param classes les classes à analyser
     */
    @ArchTest
    void checkDomainAccessRule(JavaClasses classes) {

        ArchRule domainRule = classes()
                .that().resideInAPackage("..domain..")
                .should().onlyBeAccessed().byAnyPackage("..domain..", "..application..", "..infrastructure..");

        domainRule.check(classes);
    }

    /**
     * Le user side (application) ne doit pas être accédé par une autre couche
     * Le server side n'est accédé que par le domaine
     * @param classes classes à analyser
     */
    @ArchTest
    void checkLayersAccess(JavaClasses classes) {

        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Application").definedBy("..application..")
                .layer("Domain").definedBy("..domain..")
                .layer("Infrastructure").definedBy("..infrastructure..")

                .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Domain");
        rule.check(classes);
    }
}
