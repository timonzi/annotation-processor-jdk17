package org.example.processor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import org.example.annotations.AnnotationA;
import org.example.annotations.ObjectAnnotation;

@SupportedAnnotationTypes({
        "org.example.annotations.ObjectAnnotation"
})
@SupportedSourceVersion(SourceVersion.RELEASE_17)

@SuppressWarnings("java:S4507")
public class ObjectProcessor extends AbstractProcessor {


    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        final Map<String, String> options = processingEnv.getOptions();
    }


    @Override
    public boolean process(
            final Set<? extends TypeElement> annotations,
            final RoundEnvironment roundEnv
    ) {
        for (final Element annotatedElement : roundEnv.getElementsAnnotatedWith(ObjectAnnotation.class)) {

            try {
                final TypeElement element = (TypeElement) annotatedElement;
                parseBo(element);
            } catch (final RuntimeException re) {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                re.printStackTrace(pw);
                re.printStackTrace();
            }
        }
        return true;
    }


    private void parseBo(final TypeElement boElement) {

        for (final Element element : boElement.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD) {
                final AnnotationA prop = element.getAnnotation(AnnotationA.class);
                prop.b();
            }
        }
    }


}