package com.minseoklim.hexagonalarchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    @Test
    void domainLayerDoesNotDependOnApplicationLayer() {
        final JavaClasses classes = new ClassFileImporter().importPackages("com.minseoklim.hexagonalarchitecture..");

        final ClassesShouldConjunction rule = noClasses()
            .that()
            .resideInAPackage("com.minseoklim..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.minseoklim..application..");

        rule.check(classes);
    }
}
