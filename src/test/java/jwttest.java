import com.zhou.demo.persist.po.User;
import com.zhou.demo.utils.JwtUtils;

public class jwttest {
    public static void main(String[] args) {
//        JwtUtils.createToken("1",);
        boolean b = JwtUtils.checkToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCg5y0g0Ndg1S0lFKrShQsjI0Mzc3NzUwMjPXUSotTi3yTAEqMlSqBQCYEdSDMAAAAA.M7n70xJwT4wDajPqgDK2PA03mjOqxqlBpz-yN--s1QivlNyaik0qrVNjoLt6-U5ntJ8CRnKa-TZGQ_Rt4Z9k2g");
        System.out.println(b);
        System.out.println(JwtUtils.getUserId("eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCg5y0g0Ndg1S0lFKrShQsjI0Mzc3NzUwMjPXUSotTi3yTAEqMlSqBQCYEdSDMAAAAA.M7n70xJwT4wDajPqgDK2PA03mjOqxqlBpz-yN--s1QivlNyaik0qrVNjoLt6-U5ntJ8CRnKa-TZGQ_Rt4Z9k2g"));
    }
}
