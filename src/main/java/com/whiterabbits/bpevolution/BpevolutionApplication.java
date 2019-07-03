

package com.whiterabbits.bpevolution;

import com.whiterabbits.bpevolution.dao.AccessRightRepository;
import com.whiterabbits.bpevolution.dao.ApplicationRepository;
import com.whiterabbits.bpevolution.dao.BusinessProfileRepository;
import com.whiterabbits.bpevolution.entities.AccessRight;
import com.whiterabbits.bpevolution.entities.Application;
import com.whiterabbits.bpevolution.entities.BusinessProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BpevolutionApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BpevolutionApplication.class, args);
		BusinessProfileRepository businessProfileDao = context.getBean(BusinessProfileRepository.class);
		ApplicationRepository applicationDao = context.getBean(ApplicationRepository.class);
		AccessRightRepository accessDao = context.getBean(AccessRightRepository.class);

	/*	BusinessProfile bp1 = new BusinessProfile("BPLabel1", "BPCode1");
		BusinessProfile bp2 = new BusinessProfile("BPLabel2", "BPCode2");
		BusinessProfile bp3 = new BusinessProfile("BPLabel3", "BPCode3");
		BusinessProfile bp11 = new BusinessProfile("BPLabel11", "BPCode11");
		BusinessProfile bp22 = new BusinessProfile("BPLabel22", "BPCode22");
		BusinessProfile bp33 = new BusinessProfile("BPLabel33", "BPCode33");
		BusinessProfile bp111 = new BusinessProfile("BPLabel111", "BPCode111");
		BusinessProfile bp222 = new BusinessProfile("BPLabel222", "BPCode222");
		BusinessProfile bp333 = new BusinessProfile("BPLabel333", "BPCode333");

		Application app1 = new Application("ApplicationLabel1","APPCODE1");
		Application app2 = new Application("ApplicationLabel2","APPCODE2");
		Application app3 = new Application("ApplicationLabel3","APPCODE3");
		Application app4 = new Application("ApplicationLabel4","APPCODE4");


		Set<BusinessProfile> profileList = new HashSet<>();
		bp1.getApplicationList().add(app1);
		bp2.getApplicationList().add(app2);
		bp3.getApplicationList().add(app3);
		bp11.getApplicationList().add(app1);
		bp11.getApplicationList().add(app2);
		bp22.getApplicationList().add(app1);
		bp22.getApplicationList().add(app3);
		bp33.getApplicationList().add(app2);
		bp33.getApplicationList().add(app3);
		bp111.getApplicationList().add(app1);
		bp111.getApplicationList().add(app2);
		bp111.getApplicationList().add(app3);
		bp222.getApplicationList().add(app1);
		bp222.getApplicationList().add(app2);
		bp222.getApplicationList().add(app3);
		bp333.getApplicationList().add(app1);
		bp333.getApplicationList().add(app2);
		bp333.getApplicationList().add(app3);
		profileList.add(bp1);
		profileList.add(bp2);
		profileList.add(bp3);
		profileList.add(bp11);
		profileList.add(bp22);
		profileList.add(bp33);
		profileList.add(bp111);
		profileList.add(bp222);
		profileList.add(bp333);
		businessProfileDao.saveAll(profileList);

		Set<Application> applicationList = new HashSet<>();
		app1.getBusinessProfile().add(bp1);
		app1.getBusinessProfile().add(bp11);
		app1.getBusinessProfile().add(bp22);
		app1.getBusinessProfile().add(bp111);
		app1.getBusinessProfile().add(bp222);
		app1.getBusinessProfile().add(bp333);
		app2.getBusinessProfile().add(bp2);
		app2.getBusinessProfile().add(bp11);
		app2.getBusinessProfile().add(bp33);
		app2.getBusinessProfile().add(bp111);
		app2.getBusinessProfile().add(bp222);
		app2.getBusinessProfile().add(bp333);
		app3.getBusinessProfile().add(bp3);
		app3.getBusinessProfile().add(bp22);
		app3.getBusinessProfile().add(bp33);
		app3.getBusinessProfile().add(bp111);
		app3.getBusinessProfile().add(bp222);
		app3.getBusinessProfile().add(bp333);
		applicationList.add(app1);
		applicationList.add(app2);
		applicationList.add(app3);
		applicationList.add(app4);
		applicationDao.saveAll(applicationList);*/

		AccessRight ri1;
		Application app;
		BusinessProfile bp;
		Set<Application> application_List = new HashSet<>();
		int cpt = 0;

		for (int j=1;j<=10;j++) {
			bp = new BusinessProfile("BPLabel"+j, "BPCode"+j);
			for (int i = 1; i <= 10; i++) {
				cpt ++;
				app = new Application("ApplicationLabel" + i + cpt, "APPCODE" + i + cpt);
				bp.getApplicationList().add(app);
				app.getBusinessProfile().add(bp);
				//application_List.add(app);

				for (int k = 1; k <= 10; k++) {

					ri1 = new AccessRight("AccessRightLabel" + k + cpt, "ACCESSCODE" + k + cpt);

					app.getAccessRightList().add(ri1);
					ri1.setApplication(app);

					applicationDao.save(app);
					accessDao.save(ri1);

				}
				System.out.println("//////////////////////////////////////////");
			}
			System.out.println(bp.toString());
			businessProfileDao.save(bp);
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
	}

}
