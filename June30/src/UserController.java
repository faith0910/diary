

/**
 * @program:June30
 * @description:
 * @author:Juwenchao
 * @date:2020-06-30 19:44:47
 */
public class UserController {
    @Autowired
    private UserService userService;

     public UserService getUserService() {
        return userService;
    }

    /*   public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}
