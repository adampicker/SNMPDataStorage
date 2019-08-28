package corp.netizen.datastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "corp.netizen")
@SpringBootApplication
public class DatastoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatastoreApplication.class, args);
	}

}
