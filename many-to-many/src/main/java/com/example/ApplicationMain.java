package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationMain {

    public static void main(String[] args) {

        SessionFactory factory = createHibernateSessionFactory();
        EnployeeEntity employee = getEmployee();
        ProjectEntity project = getProject();
        Transaction transaction = null;

        //First save the entities
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            session.save(project);
            WorksForEntity work = getWorksFor(employee.getEmployeeId(), project.getProjectId());
            work.setEmployee(employee);
            work.setProject(project);
            session.save(work);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        //Check if WorksFor Relationship is saved
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            WorksForEntity work = session.get(WorksForEntity.class, new WorksForEntityId(1L, 1L));
            System.out.println(work.getHours()); //Should print 8
            System.out.println(work.getProject().getName()); //Should print "Hibernate Test" after querying DB because FetchType is LAZY
            System.out.println(work.getEmployee().getName()); //Should print "Yasir" after querying DB because FetchType is LAZY
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static SessionFactory createHibernateSessionFactory() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "org.h2.Driver");
        settings.put("dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.connection.url", "jdbc:h2:mem:snb_offers_db;DB_CLOSE_DELAY=-1");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "password");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(EnployeeEntity.class);
        metadataSources.addAnnotatedClass(ProjectEntity.class);
        metadataSources.addAnnotatedClass(WorksForEntity.class);
        Metadata metadata = metadataSources.buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    private static EnployeeEntity getEmployee() {
        EnployeeEntity employee = new EnployeeEntity();
        employee.setEmployeeId(1L);
        employee.setName("Mohammad Yasir");
        employee.setDob(new Date());
        employee.setDoj(new Date());
        employee.setAddress("Abu Dhabi");
        employee.setSalary(10000);
        employee.setGender("MALE");
        return employee;
    }

    private static ProjectEntity getProject() {
        ProjectEntity project = new ProjectEntity();
        project.setProjectId(1L);
        project.setName("Hibernate Test");
        project.setLocation("Abu Dhabi");
        project.setDepartment("Computer Science");
        return project;
    }

    private static WorksForEntity getWorksFor(Long empId, Long projId) {
        WorksForEntityId id = new WorksForEntityId(empId, projId);
        WorksForEntity entity = new WorksForEntity();
        entity.setPrimaryKey(id);
        entity.setHours(8);
        return entity;
    }
}
