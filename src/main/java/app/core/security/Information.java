package app.core.security;

import java.time.LocalDateTime;

import app.core.client.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {
	
	private int id; 
	private String email; 
	private LocalDateTime expiration;
	private ClientType clientType;

}
