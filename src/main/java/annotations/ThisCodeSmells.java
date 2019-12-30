package annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ThisCodeSmellsList.class)
public @interface ThisCodeSmells {
    String reviewer();
}
