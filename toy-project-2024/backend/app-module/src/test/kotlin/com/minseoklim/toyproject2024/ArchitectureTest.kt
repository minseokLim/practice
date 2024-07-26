package com.minseoklim.toyproject2024

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional

class ArchitectureTest {

    @Test
    fun test() {
        val classes = ClassFileImporter().withImportOption(DoNotIncludeTests()).importPackages(BASE_PACKAGE)
        checkNotEmpty(classes)
        checkPackageDependency(classes)
        checkTransactionalAnnotation(classes)
    }

    private fun checkNotEmpty(classes: JavaClasses) {
        require(classes.isNotEmpty()) { "No classes found" }
    }

    private fun checkPackageDependency(classes: JavaClasses) {
        checkDomainDependency(classes)
        checkApplicationDependency(classes)
    }

    private fun checkTransactionalAnnotation(classes: JavaClasses) {
        val transactionalAnnotationRule = classes()
            .that()
            .haveSimpleNameEndingWith("Service")
            .and()
            .resideInAPackage("..application..")
            .should()
            .beAnnotatedWith(Transactional::class.java)

        transactionalAnnotationRule.check(classes)
    }

    private fun checkDomainDependency(classes: JavaClasses) {
        val domainDependencyRule = noClasses()
            .that()
            .resideInAPackage("..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "$BASE_PACKAGE..annotation..",
                "$BASE_PACKAGE..application..",
                "$BASE_PACKAGE..config..",
                "$BASE_PACKAGE..dto..",
                "$BASE_PACKAGE..filter..",
                "$BASE_PACKAGE..infra..",
                "$BASE_PACKAGE..ui.."
            )

        domainDependencyRule.check(classes)
    }

    private fun checkApplicationDependency(classes: JavaClasses) {
        val applicationDependencyRule = noClasses()
            .that()
            .resideInAPackage("..application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "jakarta.servlet..",
                "$BASE_PACKAGE..config..",
                "$BASE_PACKAGE..filter..",
                "$BASE_PACKAGE..infra..",
                "$BASE_PACKAGE..ui.."
            )

        applicationDependencyRule.check(classes)
    }

    companion object {
        private const val BASE_PACKAGE = "com.minseoklim"
    }
}
