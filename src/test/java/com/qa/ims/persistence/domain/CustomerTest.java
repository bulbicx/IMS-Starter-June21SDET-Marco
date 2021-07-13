package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void constructor1Test() {
		//String firstName, String surname
		Customer customer = new Customer("Mark", "Kavinski");
		assertTrue(customer instanceof Customer);
	}

	@Test
	public void constructor2Test() {
		//Long id, String firstName, String surname
		Customer customer = new Customer(1L, "Mark", "Kavinski");
		assertTrue(customer instanceof Customer);
	}

	@Test
	public void getIdTest() {
		Long expectedId = 1L;
		Customer customer = new Customer(expectedId, "Alan", "Barber");
		
		Long actualId = customer.getId();
		
		assertEquals(expectedId, actualId);
	}

	@Test
	public void setIdTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		
		Long expectedId = 2L;
		customer.setId(expectedId);
		
		Long actualId = customer.getId();
		assertEquals(expectedId, actualId);
	}

	@Test
	public void getFirstNameTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		
		String actualName = customer.getFirstName();
		
		assertEquals("Bob", actualName);
	}

	@Test
	public void setFirstNameTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		
		customer.setFirstName("Mariley");
		String actualName = customer.getFirstName();
		
		assertEquals("Mariley", actualName);
	}

	@Test
	public void getSurnameTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		
		String actualSurname = customer.getSurname();
		
		assertEquals("Marley", actualSurname);
	}

	@Test
	public void setSurnameTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		
		customer.setSurname("Uler");
		String actualSurname = customer.getSurname();
		
		assertEquals("Uler", actualSurname);
	}

	@Test
	public void toStringTest() {
		Customer customer = new Customer(1L, "Bob", "Marley");
		String expected = "id: 1 | first name: Bob | surname: Marley";
		String actualResult = customer.toString();
		assertEquals(expected, actualResult);
	}

	@Test
	public void HashCodeTest() {
		Customer customer1 = new Customer("Bob", "Marley");
		Customer customer2 = new Customer("Bob", "Marley");
		
		assertTrue(customer1.hashCode() == customer2.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Customer customer1 = new Customer(1L, "Bob", "Marley");
		Customer customer2 = new Customer(1L, "Bob", "Marley");
		Customer customer3 = new Customer(2L, "Alia", "Black");
		assertTrue(customer1.equals(customer2) && customer2.equals(customer1));
		assertFalse(customer1.equals(customer3) && customer3.equals(customer1));
	}


	@Test
	@Ignore
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

}
