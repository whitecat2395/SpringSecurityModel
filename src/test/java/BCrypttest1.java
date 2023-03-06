import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCrypttest1 {
    public static void main(String[] args) {
        PasswordEncoder bc = new BCryptPasswordEncoder();
        System.out.println(bc.encode("123"));
    }

}
