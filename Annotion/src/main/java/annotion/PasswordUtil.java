package annotion;

/**
 * Created by wl on 2014/12/19.
 */
public class PasswordUtil {
    @UseCase(id=47,description = "password must contain at least on numberic")
    public boolean validate(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id=48)
    public String enPassword(String password){
        return new StringBuffer(password).reverse().toString();
    }

}
