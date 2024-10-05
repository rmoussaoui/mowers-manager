package fr.mowitnow.mowermanager;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "fr.mowitnow.mowermanager")
public class ArchiTest {

    @ArchTest
    void checkApplicationArchitecture(JavaClasses classes) {

        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Application").definedBy("..application..")
                .layer("Modele").definedBy("..model..")
                .layer("Infrastructure").definedBy("..infrastructure..")

                .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Modele");
        rule.check(classes);
    }

}
