package com.myasir.tableperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.myasir.tableperclass.tables.Car;
import com.myasir.tableperclass.tables.Truck;
import com.myasir.tableperclass.tables.Vehicle;

/**
 * Hello world!
 *
 */
public class App 
{
	private static SessionFactory factory;
	
	/*
	 * Initializing the Hibernate static factory instance.
	 */
	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);
		cfg.addAnnotatedClass(Car.class);
		cfg.addAnnotatedClass(Truck.class);
		ServiceRegistry srv = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		factory = cfg.buildSessionFactory(srv);
	}
	
    public static void main( String[] args )
    {
    	Session session = null;
        try {
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Car car1 = new Car();
			car1.setManufactureYear(2014);
			car1.setNoOfWheels(4);
			car1.setModel("Benz");
			car1.setSeatingCapacity(5);
			
			Car car2 = new Car();
			car2.setManufactureYear(2015);
			car2.setNoOfWheels(4);
			car2.setModel("Ford Aspire");
			car2.setSeatingCapacity(5);
			
			Truck truck1 = new Truck();
			truck1.setManufactureYear(2016);
			truck1.setNoOfWheels(4);
			truck1.setMaxPayload(1500);
			truck1.setContainerized(false);
			
			Truck truck2 = new Truck();
			truck2.setManufactureYear(2017);
			truck2.setNoOfWheels(4);
			truck2.setMaxPayload(1800);
			truck2.setContainerized(true);
			
			session.save(car1);
			session.save(car2);
			session.save(truck1);
			session.save(truck2);
			
			tx.commit();
			
		} finally {
			session.close();
		}
    }
}
