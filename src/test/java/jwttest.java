import com.zhou.demo.persist.po.User;
import com.zhou.demo.utils.JwtUtils;

public class jwttest {
    public static void main(String[] args) {
        String token = JwtUtils.createToken("1");
        System.out.println(token);
        boolean b = JwtUtils.checkToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCg5y0g0Ndg1S0lFKrShQsjI0M7cwMjM1MLDQUSotTi3yTAEqMlSqBQBZk8JwMAAAAA.lhOJYMrw-GMi65Yyn3BvWv8k0obFBao44yDvs5LhknUSIDYDC57s-5aTBnbjg5-p6mPkrvIx_rQDInHsJ8fVHQ");
        System.out.println(b);
    }
}
