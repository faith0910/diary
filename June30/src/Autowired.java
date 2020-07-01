import java.lang.annotation.*;

/**
 * @author wuchunjie
 * @date 2020/6/30
 */

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.FIELD)
@Documented
public @interface Autowired {
}
