package com.hatenablog.techium.annotation.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.hatenablog.techium.annotation.processor.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AnnotationProcessorSample extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        StringBuilder builder = new StringBuilder()
                .append("package com.hatenablog.techium.annotation.processor.generated;\n\n")
                .append("public class GeneratedClass {\n\n")
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");

        builder.append("use annotation : [");
        for (Element element : roundEnvironment.getElementsAnnotatedWith(CustomAnnotation.class)) {
            String objectType = element.getSimpleName().toString();
            builder.append(objectType).append(",");
        }
        builder.append("]\";\n")
                .append("\t}\n")
                .append("}\n");

        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.hatenablog.techium.annotation.processor.generated.GeneratedClass");
            try (Writer writer = source.openWriter()){
                writer.write(builder.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            // 同じファイルが生成されている場合はFilerExceptionがthrowされるためここでprintMessageでErrorを設定するとビルドが通らなくなる
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Not create file : com.hatenablog.techium.annotation.processor.generated.GeneratedClass");
        }

        return true;
    }
}
