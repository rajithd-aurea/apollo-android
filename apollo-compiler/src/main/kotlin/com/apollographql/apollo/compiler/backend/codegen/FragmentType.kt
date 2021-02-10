package com.apollographql.apollo.compiler.backend.codegen

import com.apollographql.apollo.api.Fragment
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import kotlin.reflect.KClass

internal fun CodeGenerationAst.FragmentType.interfaceTypeSpec(generateAsInternal: Boolean): TypeSpec {
  return TypeSpec
      .interfaceBuilder((this.interfaceType.name).escapeKotlinReservedWord())
      .addAnnotation(suppressWarningsAnnotation)
      .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
      .applyIf(this.description.isNotBlank()) { addKdoc("%L\n", this@interfaceTypeSpec.description) }
      .addProperties(
          this.interfaceType.fields
              .filter {
                it.type is CodeGenerationAst.FieldType.Object ||
                    (it.type is CodeGenerationAst.FieldType.Array && it.type.leafType is CodeGenerationAst.FieldType.Object) ||
                    !it.override
              }
              .map { field -> field.asPropertySpec() }
      )
      .addTypes(
          this.interfaceType.nestedObjects.map { nestedObject ->
            nestedObject.typeSpec()
          }
      )
      .addType(
          TypeSpec.companionObjectBuilder()
              .addProperty(PropertySpec.builder("FRAGMENT_DEFINITION", String::class)
                  .initializer(CodeBlock.of("%S", fragmentDefinition))
                  .build()
              )
              .addFunctions(
                  this@interfaceTypeSpec.interfaceType.fragmentAccessors.map { accessor ->
                    FunSpec
                        .builder(accessor.name.escapeKotlinReservedWord())
                        .receiver(this.interfaceType.typeRef.asTypeName())
                        .returns(accessor.typeRef.asTypeName().copy(nullable = true))
                        .addStatement("return this as? %T", accessor.typeRef.asTypeName())
                        .build()

                  }
              )
              .build()
      )
      .build()
}

internal fun CodeGenerationAst.FragmentType.implementationTypeSpec(generateAsInternal: Boolean): TypeSpec {
  val dataTypeName = this.implementationType.nestedObjects.single().typeRef.asTypeName()
  return this.implementationType
      .typeSpec()
      .toBuilder()
      .apply {
        val dataTypeSpec = typeSpecs.single().addSuperinterface(Fragment.Data::class)
        typeSpecs[0] = dataTypeSpec
      }
      .addSuperinterface(Fragment::class.java.asClassName().parameterizedBy(dataTypeName))
      .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
      .addVariablesIfNeeded(variables, this.implementationType.typeRef.name)
      .addFunction(
          FunSpec.builder("adapter")
              .addModifiers(KModifier.OVERRIDE)
              .returns(ResponseAdapter::class.asTypeName().parameterizedBy(dataTypeName))
              .addCode("return·%T", this.implementationType.typeRef.asAdapterTypeName())
              .build()
      )
      .addFunction(variables.variablesFunSpec())
      .build()
}

private fun TypeSpec.addSuperinterface(superinterface: KClass<*>): TypeSpec {
  return toBuilder().addSuperinterface(superinterface).build()
}
