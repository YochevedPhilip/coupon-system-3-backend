package app.core.security;

import java.util.UUID;

import app.core.client.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

	private UUID token; 
	private int id; 
	private String email; 
	private String name; 
	private ClientType clientType;
}
