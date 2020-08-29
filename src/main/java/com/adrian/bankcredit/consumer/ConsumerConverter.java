package com.adrian.bankcredit.consumer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ConsumerConverter {
	
	public ConsumerDto entityToDto(Consumer consumer) {
		
		ConsumerDto consumerDto = new ConsumerDto();
		
		consumerDto.setId(consumer.getId());
		consumerDto.setName(consumer.getName());
		consumerDto.setLastName(consumer.getLastName());
		
		return consumerDto;
	}
	
	public ConsumerDtoAdvance entityToDtoAdvance(Consumer consumer) {
		
		ConsumerDtoAdvance consumerDtoAdvance = new ConsumerDtoAdvance();
		
		consumerDtoAdvance.setId(consumer.getId());
		consumerDtoAdvance.setName(consumer.getName());
		consumerDtoAdvance.setLastName(consumer.getLastName());
		consumerDtoAdvance.setPesel(consumer.getPesel());
		
		return consumerDtoAdvance;
	}
	
	public List<ConsumerDtoAdvance> entityToDtoAdvance(List<Consumer> consumers){
		
		return consumers.stream()
				.map(x -> entityToDtoAdvance(x))
				.collect(Collectors.toList());
	}
	
	public Consumer dtoToEntity(ConsumerDto consumerDto) {
		
		Consumer consumer = new Consumer();
		
		consumer.setId(consumerDto.getId());
		consumer.setName(consumerDto.getName());
		consumer.setLastName(consumerDto.getLastName());
		
		return consumer;
	}

}
