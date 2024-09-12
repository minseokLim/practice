package com.minseoklim.toyproject2024.common.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.env.CompositePropertySource
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.ClassPathResource

abstract class CustomEnvironmentPostProcessor(
    private val propertyName: String
) : EnvironmentPostProcessor {
    private val yamlLoader = YamlPropertySourceLoader()

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val propertySource = load(environment.activeProfiles)
        environment.propertySources.addLast(propertySource)
    }

    private fun load(profiles: Array<String>): PropertySource<Any> {
        return CompositePropertySource(propertyName).apply {
            addFirstPropertySource(checkNotNull(getYmlProperty("$propertyName.yml")))

            profiles.forEach { profile ->
                getYmlProperty("$propertyName-$profile.yml")?.let {
                    addFirstPropertySource(it)
                }
            }
        }
    }

    private fun getYmlProperty(fileName: String): PropertySource<*>? {
        return try {
            yamlLoader.load(fileName, ClassPathResource(fileName))[0]
        } catch (e: IllegalStateException) {
            null
        }
    }
}
