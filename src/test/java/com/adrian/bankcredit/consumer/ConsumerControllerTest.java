package com.adrian.bankcredit.consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTest {
	
	@MockBean
    private ConsumerService consumerService;

    @Autowired
    private MockMvc mockMvc;

    
    @Nested
    public class GetConsumerByIdTest{
    	
		@Test
	    public void testGetConsumerById() throws Exception {
	        
			Consumer consumer = new Consumer(1L, "login", "password", "USER",
										"Richard", "Le", 12345678901L, true);
	        doReturn(Optional.of(consumer)).when(consumerService).findById(1l);
	
	        mockMvc.perform(get("/bank/consumer/{id}", 1L))
	        
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.name", is("Richard")))
	                .andExpect(jsonPath("$.lastName", is("Le")))
	                .andExpect(jsonPath("$.pesel", is(12345678901L)));
	    }
		
		@Test
		public void testGetConsumerByIdNotFound() throws Exception {
			
			doReturn(Optional.empty()).when(consumerService).findById(1L);
			
			mockMvc.perform(get("/bank/consumer/{id}", 1L))
					.andExpect(status().isNotFound());
		}
    }

}
