package com.example.kotlin_processor

import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

class BuildProcessor (
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val localFeatureClass = resolver.getClassDeclarationByName("com.example.feature_containter.LocalFeature")
        if (localFeatureClass == null) {
            logger.error("BuildProcessor: LocalFeature class not found")
            return emptyList()
        }

        val localFeatureSubClasses = findLocalFeatureSubClasses(resolver)


        return emptyList()
    }

    private fun findLocalFeatureSubClasses(resolver: Resolver): List<KSAnnotated> {
        val localFeatureSubClasses = mutableListOf<KSClassDeclaration>()
        val allClasses = resolver.getAllFiles().flatMap { it.declarations }
        for (declaration in allClasses) {
            if (declaration is KSClassDeclaration) {
                val superClass = declaration.superTypes.firstOrNull()
                if (superClass != null &&
                    superClass.resolve()?.declaration?.qualifiedName?.asString() == "com.example.feature_containter.LocalFeature") {
                    localFeatureSubClasses.add(declaration)
                }
            }
        }

        return localFeatureSubClasses
    }

    private fun generateCode(localFeatureSubClasses: List<KSClassDeclaration>) {
        val fileSpec = FileSpec.builder("com.example.kotlin_processor", "LocalFeatureCollection.kt")
            .addImport("com.example.feature_containter.LocalFeature")
            .addImport("kotlin.collections.List")
            .addImport("kotlin.reflect.KClass")
            .addType(
                TypeSpec.classBuilder("LocalFeatureCollection")
                    .addModifiers(KModifier.DATA)
                    .addProperty(
                        PropertySpec.builder("localFeatures", ParameterizedTypeName.get(List::class.java, KSClassDeclaration::class))
                            .initializer("mutableListOf()")
                            .mutable(true)
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("addLocalFeature")
                            .addParameter("localFeature", ClassName("kotlin.reflect", "KClass"))
                            .addStatement("localFeatures.add(localFeature)")
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("getLocalFeatures")
                            .returns(ParameterizedTypeName.list(ClassName("kotlin.reflect", "KClass")))
                            .addStatement("return localFeatures")
                            .build()
                    ).build()
            )
    }

    class BuildProcessorProvider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
            return BuildProcessor(environment.codeGenerator, environment.logger)
        }
    }
}
