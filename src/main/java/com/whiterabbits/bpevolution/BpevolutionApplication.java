

package com.whiterabbits.bpevolution;

import com.whiterabbits.bpevolution.dao.*;
import com.whiterabbits.bpevolution.entities.*;
import com.whiterabbits.bpevolution.utils.Display;
import com.whiterabbits.bpevolution.utils.Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BpevolutionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BpevolutionApplication.class, args);

		//Creation des users et roles pour tests
		UserRepository userRepository = context.getBean(UserRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);

		User userAdmin = new User("last_name_admin", "first_name_admin", "admin", new BCryptPasswordEncoder().encode("admin"), true);
		User userUser = new User("last_name_user", "first_name_user", "user", new BCryptPasswordEncoder().encode("user"), true);
		Role rolesAdmin = new Role("ADMIN", "Administrator Role");
		Role rolesUser = new Role("USER", "User Role");

		roleRepository.save(rolesAdmin);
		roleRepository.save(rolesUser);

		userAdmin.getRoles().add(rolesAdmin);
		userRepository.save(userAdmin);
		userUser.getRoles().add(rolesUser);
		userRepository.save(userUser);

		//Creation des BP,APP,R pour test
		BusinessProfileRepository businessProfileDao = context.getBean(BusinessProfileRepository.class);
		ApplicationRepository applicationDao = context.getBean(ApplicationRepository.class);
		AccessRightRepository accessDao = context.getBean(AccessRightRepository.class);

		AccessRight ri1;
		Application app;
		BusinessProfile bp;
		Set<Application> application_List = new HashSet<>();
		int cpt = 0;

		for (int j=1;j<=2;j++) {
			bp = new BusinessProfile("BPLabel"+j, "BPCode"+j);
			bp=businessProfileDao.save(bp);
			for (int i = 1; i <= 2; i++) {
				cpt++;
				app = new Application("ApplicationLabel" + cpt, "APPCODE" + cpt);
				app = applicationDao.save(app);
				bp.getApplicationList().add(app);
				app.getBusinessProfile().add(bp);
				//bp = businessProfileDao.save(bp);

				bp = businessProfileDao.save(bp);
				//app = applicationDao.save(app);
				//application_List.add(app);

				for (int k = 1; k <= 2; k++) {

					ri1 = new AccessRight("AccessRightLabel" + k, "ACCESSCODE" + k );
					ri1 = accessDao.save(ri1);

					app.getAccessRightList().add(ri1);
					ri1.setApplication(app);

					app = applicationDao.save(app);
					//app = applicationDao.save(app);
					//ri1 = accessDao.save(ri1);

				}
				//bp = businessProfileDao.save(bp);
				System.out.println("//////////////////////////////////////////");
			}
			System.out.println(bp.toString());

			//applicationDao.saveAll(application_List);
		}

/*		for (Application appCourante : application_List) {
			for (int i = 1; i <= 1; i++) {

				ri1 = new AccessRight("AccessRightLabel" + i, "ACCESSCODE" + i);

				appCourante.getAccessRightList().add(ri1);
				ri1.setApplication(appCourante);

				accessDao.save(ri1);
				applicationDao.save(appCourante);
			}
		}*/

		Display.multiplication(1);
		Reader.readFile("C:\\Users\\BENNJ\\Projets\\Java\\Logs\\FileManagerFx\\app.log");


	}

}
