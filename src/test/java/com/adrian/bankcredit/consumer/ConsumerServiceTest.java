package com.adrian.bankcredit.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ConsumerServiceTest {

	@Autowired
	private ConsumerService consumerService;
	
	@MockBean
	private ConsumerRepository consumerRepository;
	
	@Nested
	class FindByIdTest{
		
		@Test
		public void testFindById() {
			Consumer consumer = new Consumer(1L, "login", "password", "USER", 
										"Richard", "Le", 12345678901L, true);
			doReturn(Optional.of(consumer)).when(consumerRepository).findById(1L);
			
			Optional<Consumer> returnedConsumer = consumerService.findById(1L);
			
			assertTrue(returnedConsumer.isPresent(), "Consumer was not found");
			assertSame(returnedConsumer.get(), consumer, "The consumer returned "
					+ "	was not the same as the mock");
		}
		
		@Test
		public void testFindByIdNotFound() {
			doReturn(Optional.empty()).when(consumerRepository).findById(1L);
			
			Optional<Consumer> returnedConsumer = consumerService.findById(1L);
			
			assertFalse(returnedConsumer.isPresent(), "Consumer should not be found");
		}
	}
	
	@Nested
	public class FindAllTest {
		
		@Test
		public void testFindAll() {
			Consumer consumer1 = new Consumer(1L, "login", "password",
						"USER", "Richard", "Le", 12345678901L, true);
			Consumer consumer2 = new Consumer(2L, "login", "password",
						"USER", "Richard", "Le", 12345678902L, true);
			List<Consumer> consumerList = new ArrayList<>();
			consumerList.add(consumer1);
			consumerList.add(consumer2);
			
			doReturn(consumerList).when(consumerRepository).findAll();
			
			List<Consumer> consumers = consumerService.findAll();
			
			assertEquals(2, consumers.size(),"FindAll should return 2 consumers");
			
		}
	}
	
	@Nested
	public class SaveTest {
		
		@Test
		public void testSave() {
			Consumer consumer = new Consumer(1L, "login", "password", "USER", "Richard", "Le", 12345678901L, true);
			doReturn(consumer).when(consumerRepository).save(any(Consumer.class));
			
			Consumer returnedConsumer = consumerService.save(consumer);
			
			assertNotNull(returnedConsumer, "The Consumer should not be null");
			assertSame(returnedConsumer, consumer, "The consumer returned was not the same as the mock");
		}
	}
	
	@Nested
	public class DeleteTest {
		
		@Test
		public void testDelete() {
			
			consumerService.deleteById(1L);
			
			verify(consumerRepository, times(1)).deleteById(1L);
		}
	}
	
}
