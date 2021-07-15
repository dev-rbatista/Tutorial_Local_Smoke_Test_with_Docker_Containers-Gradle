package switchtwentytwenty.project.authentication;

import java.io.Serializable;
import java.util.Objects;

//Code adapted from https://www.javainuse.com/spring/boot-jwt and https://www.javainuse.com/spring/boot-jwt-mysql

public class JWTResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JWTResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTResponse)) return false;
        JWTResponse that = (JWTResponse) o;
        return Objects.equals(jwttoken, that.jwttoken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwttoken);
    }
}
