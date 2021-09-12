package tw.com.order.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestMain {

	public static void main(String[] args) {
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		String pass="test123";
		String epass="$2a$10$E2UgZ6DkQaBhC0EPGSBNIOr2Y3A3HDgQ/0Z9h6uennxDAf7BZOL/i";
		boolean getpass=encode.matches(pass, epass);
		System.out.println(getpass);
	}

}
