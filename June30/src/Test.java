import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @program:June30
 * @description:
 * @author:Juwenchao
 * @date:2020-06-30 19:45:39
 */
public class Test {

    public static void main(String[] args) throws Exception {
       /* UserService userService = new UserService();
        System.out.println(userService);
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Field field = clazz.getDeclaredField("userService");
        String name = field.getName();
        name = "set"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
        clazz.getDeclaredMethod(name,UserService.class).invoke(userController,userService);
        System.out.println(userController.getUserService());
*/

        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation!=null){
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    Object o = type.newInstance();
                    field.set(userController,o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }


}
