package pl.allegro.tech.spunit.annotation

import org.junit.jupiter.api.TestFactory

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@TestFactory
annotation class Spunit
